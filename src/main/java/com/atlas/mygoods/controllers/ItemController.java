package com.atlas.mygoods.controllers;


import com.atlas.mygoods.models.Image;
import com.atlas.mygoods.models.Item.Item;
import com.atlas.mygoods.services.ImageService;
import com.atlas.mygoods.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public Item addItem(@RequestPart("item") Item item, @RequestPart("images") List<MultipartFile> multipartFiles) throws IOException {
        final List<Image> images = imageService.addImages(multipartFiles);
        item.setImages(images);
        itemService.addItem(item);
        return item;
    }

    @GetMapping
    public List<Item> getAllItem() {
        return itemService.getAllItem();
    }

//    @GetMapping(path = "findItemByCategory")
//    public List<Item> findItemByCategory(
//            @RequestParam String mainCategory,
//            @RequestParam String subCategory) {
//        return itemService.findItemByCategory(
//                new Category(
//                        mainCategory,
//                        subCategory)
//        );
//    }

    @DeleteMapping(path = "{itemId}")
    public void deleteItem(@PathVariable("itemId") Long itemId) {
        itemService.deleteItemById(itemId);
    }

    @PutMapping(path = "{itemId}")
    public void updateItem(@PathVariable("itemId") Long id, @RequestBody Item item) {
        itemService.updateItemByItem(id, item);
    }

//    @GetMapping(path = "myItems/{userId}")
//    public List<Item> getMyItems(@PathVariable("userId")){
//        itemService
//    }

}
