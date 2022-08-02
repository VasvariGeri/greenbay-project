package com.gfa.homework.controllers;

import com.gfa.homework.dtos.ItemCreationRequestDTO;
import com.gfa.homework.security.AuthenticationRequest;
import com.gfa.homework.services.ItemService;
import com.gfa.homework.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class APIController {

  private UserService userService;
  private ItemService itemService;

  @Autowired
  public APIController(UserService userService, ItemService itemService) {
    this.userService = userService;
    this.itemService = itemService;
  }

  @PostMapping("/authenticate")
  public ResponseEntity<?> createAuthenticationToken(
      @RequestBody AuthenticationRequest authenticationRequest) throws Exception {
    return ResponseEntity.ok(userService.authenticate(authenticationRequest));
  }

  @PostMapping("/new-item")
  public ResponseEntity<?> createNewItem(
      @RequestBody ItemCreationRequestDTO itemCreationRequestDTO,
      @RequestHeader(value = "Authorization") String header) {
    return ResponseEntity.ok(itemService.createItem(itemCreationRequestDTO, header));
  }

  @GetMapping("/list-items/page/{n}")
  public ResponseEntity<?> listNthTwentyItems(@PathVariable Integer n){
    return ResponseEntity.ok(itemService.createItemDTOList(n));
  }

  @GetMapping("/item/{id}")
  public ResponseEntity<?> getItemInfo(@PathVariable Long id){
    return ResponseEntity.ok(itemService.createItemDTO(id));
  }
}
