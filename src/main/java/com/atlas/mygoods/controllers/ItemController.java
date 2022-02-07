package com.atlas.mygoods.controllers;

import com.atlas.mygoods.models.Category;
import com.atlas.mygoods.models.Item;
import com.atlas.mygoods.services.ImageService;
import com.atlas.mygoods.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/item")
public class ItemController {

    private final ItemService itemService;

    private final ImageService imageService;

    @Autowired
    public ItemController(ItemService itemService, ImageService imageService) {
        this.itemService = itemService;
        this.imageService = imageService;
    }

    @PostMapping
    public void addItem(@RequestBody Item item) {
        imageService.addImages(item.getImages());
        itemService.addItem(item);
    }

    @GetMapping
    public List<Item> getAllItem() {
        return itemService.getAllItem();
    }

    @GetMapping(path = "findItemByCategory")
    public List<Item> findItemByCategory(
            @RequestParam(required = true) String mainCategory,
            @RequestParam(required = true) String subCategory) {
        return itemService.findItemByCategory(new Category(mainCategory, subCategory));
    }
}
