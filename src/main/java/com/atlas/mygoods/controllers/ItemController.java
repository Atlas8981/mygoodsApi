package com.atlas.mygoods.controllers;


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

    @PostMapping(path = "test")
    public Item addItem(@RequestBody Item item) {
        return itemService.addItem(item);
    }

    @PostMapping
    public Item addItem(@RequestPart("item") Item item, @RequestPart("images") List<MultipartFile> multipartFiles) throws IOException {
        itemService.addItem(item, multipartFiles);
        return item;
    }

    @GetMapping
    @ResponseBody
    public List<Item> getItemByCategory(
            @RequestParam(name = "page") int page,
            @RequestParam(name = "sortBy") String sort,
            @RequestParam(name = "catId") Long catId
//            @RequestParam(name = "mainCat") String mainCat,
//            @RequestParam(name = "subCat") String subCat
    ) {
        return itemService.getItemByCategoryWithPaginationAndSort(page, sort, catId);
    }

    @GetMapping(path = "all")
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
