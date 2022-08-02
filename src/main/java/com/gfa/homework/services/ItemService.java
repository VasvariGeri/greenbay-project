package com.gfa.homework.services;

import com.gfa.homework.dtos.*;
import com.gfa.homework.exceptions.InvalidItemRequestException;
import com.gfa.homework.exceptions.ItemNotFoundException;
import com.gfa.homework.models.Bid;
import com.gfa.homework.models.Item;
import com.gfa.homework.models.User;
import com.gfa.homework.repositories.ItemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

  private ItemRepository itemRepository;
  private UserService userService;
  ModelMapper modelMapper = new ModelMapper();


  @Autowired
  public ItemService(ItemRepository itemRepository, UserService userService) {
    this.itemRepository = itemRepository;
    this.userService = userService;
  }

  private void validateItemRequest(ItemCreationRequestDTO itemCreationRequestDTO) {
    if (itemCreationRequestDTO.getName().isBlank() || itemCreationRequestDTO.getName() == null) {
      throw new InvalidItemRequestException("Invalid item name.");
    }
    if (itemCreationRequestDTO.getDescription().isBlank()
        || itemCreationRequestDTO.getDescription() == null) {
      throw new InvalidItemRequestException("Invalid item description.");
    }
    if (itemCreationRequestDTO.getUrl().isBlank()
        || itemCreationRequestDTO.getUrl() == null
        || !itemCreationRequestDTO.getUrl().startsWith("https://")) {
      throw new InvalidItemRequestException("Invalid item url.");
    }
    if (itemCreationRequestDTO.getStartingPrice() == null
        || itemCreationRequestDTO.getStartingPrice() <= 0) {
      throw new InvalidItemRequestException("Invalid item starting price.");
    }
    if (itemCreationRequestDTO.getPurchasePrice() == null
        || itemCreationRequestDTO.getPurchasePrice() <= 0) {
      throw new InvalidItemRequestException("Invalid item purchase price.");
    }
  }

  public Item createItem(ItemCreationRequestDTO itemCreationRequestDTO, String header){
    validateItemRequest(itemCreationRequestDTO);
    Item item = modelMapper.map(itemCreationRequestDTO, Item.class);
    User user = userService.getUserFromToken(header);
    item.setUser(user);
    user.getItems().add(item);
    itemRepository.save(item);
    return item;
  }

  public List<ItemForListingDTO> createItemDTOList(Integer page){
    List<ItemForListingDTO> itemForListingDTOList = new ArrayList<>();
    for (Item item : itemRepository.findNthTwentyItems(page)) {
        ItemForListingDTO itemForListingDTO = modelMapper.map(item, ItemForListingDTO.class);
        itemForListingDTO.setLastBid(item.getBids().get(item.getBids().size() - 1).getPrice());
        itemForListingDTOList.add(itemForListingDTO);
    }
    return itemForListingDTOList;
  }

  private Item findById(Long itemId){
    Optional<Item> item = itemRepository.findById(itemId);
    if (item.isPresent()){
      return item.get();
    } else {
      throw new ItemNotFoundException("Item is not found with given id.");
    }
  }

  private List<BidDTO> createBidDTOList(Item item){
    List<BidDTO> bidDTOList = new ArrayList<>();
    for (Bid bid : item.getBids()) {
        BidDTO bidDTO = modelMapper.map(bid, BidDTO.class);
        bidDTO.setUsername(bid.getUser().getUsername());
        bidDTOList.add(bidDTO);
    }
    return bidDTOList;
  }

  public ItemDTO createItemDTO(Long itemId){
    Item item = findById(itemId);
    if (item.isSold()){
      SoldItemDTO soldItemDTO = modelMapper.map(item, SoldItemDTO.class);
      soldItemDTO.setBids(createBidDTOList(item));
      soldItemDTO.setSeller(item.getUser().getUsername());
      soldItemDTO.setBuyer(item.getBids().get(item.getBids().size() - 1).getUser().getUsername());
      return soldItemDTO;
    } else {
      ItemDTO itemDTO = modelMapper.map(item, ItemDTO.class);
      itemDTO.setBids(createBidDTOList(item));
      itemDTO.setSeller(item.getUser().getUsername());
      return itemDTO;
    }
  }
}
