package com.atlas.mygoods.controllers;

import com.atlas.mygoods.entities.ItemEntity;
import com.atlas.mygoods.models.Image;
import com.atlas.mygoods.models.Item;
import com.atlas.mygoods.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/item")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public void addItem(@RequestBody Item item) {
        itemService.addItem(item);
    }

    @GetMapping
    public List<Item> getAllItem() {
        return itemService.getAllItem();
    }
}
