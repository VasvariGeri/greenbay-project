package com.gfa.homework.services;

import com.gfa.homework.dtos.ItemCreationRequestDTO;
import com.gfa.homework.exceptions.InvalidItemRequestException;
import com.gfa.homework.models.Item;
import com.gfa.homework.models.User;
import com.gfa.homework.repositories.ItemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

  private ItemRepository itemRepository;
  private UserService userService;

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
    ModelMapper modelMapper = new ModelMapper();
    Item item = modelMapper.map(itemCreationRequestDTO, Item.class);
    User user = userService.getUserFromToken(header);
    item.setUser(user);
    user.getItems().add(item);
    itemRepository.save(item);
    return item;
  }
}
