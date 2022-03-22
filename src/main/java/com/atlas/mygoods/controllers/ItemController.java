package com.atlas.mygoods.controllers;


import com.atlas.mygoods.models.Item.Item;
import com.atlas.mygoods.models.Item.ItemDto;
import com.atlas.mygoods.services.ImageService;
import com.atlas.mygoods.services.ItemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
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

    @PostMapping(path = "testLogin")
    public void signInPasswordLess() {
//        authenticator.aut
    }

    //    For testing adding item without image
    @PostMapping(path = "test")
    public Item addItem(@RequestBody ItemDto itemDto) {
        final Item item = covertToEntity(itemDto);
        return itemService.addItem(item);
    }

    @Autowired
    ModelMapper modelMapper;

    private Item covertToEntity(ItemDto itemDto) {
        return modelMapper.map(itemDto, Item.class);
    }

    @PostMapping
    public String addItem(@RequestPart("item") String item, @RequestPart("images") List<MultipartFile> multipartFiles) throws IOException {

        final ObjectMapper objMapper = new ObjectMapper();
        final ItemDto request = objMapper.readValue(item, ItemDto.class);

        final Item response = itemService.addItem(request, multipartFiles);
        if (response == null) {
            return "null";
        } else {
            return response.toString();
        }
//        return item;
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
