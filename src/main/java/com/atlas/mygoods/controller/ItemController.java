package com.atlas.mygoods.controller;

import com.atlas.mygoods.model.Item;
import com.atlas.mygoods.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/item")
public class ItemController {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @PostMapping(path = "/add")
    public String addItem(@RequestParam Item item) {
        itemRepository.save(item);
        return "Item saved";
    }

    @GetMapping
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }
}
