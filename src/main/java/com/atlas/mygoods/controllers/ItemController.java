package com.atlas.mygoods.controllers;

import com.atlas.mygoods.models.Category;
import com.atlas.mygoods.models.Image;
import com.atlas.mygoods.models.Item;
import com.atlas.mygoods.services.ImageService;
import com.atlas.mygoods.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
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
    public Item addItem(@RequestPart("item") Item item, @RequestPart("image") List<MultipartFile> multipartFiles) throws IOException {
        final List<Image> images = new ArrayList<>();
        if (multipartFiles == null || multipartFiles.size() == 0) {
            return null;
        }
        for (MultipartFile file : multipartFiles) {
            if (file == null) {
                return null;
            }
            if (file.getOriginalFilename() == null) {
                return null;
            }
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            String imageBase64 = Base64.getEncoder().encodeToString(file.getBytes());
            final Image image = new Image(imageBase64, fileName);
            images.add(image);
        }


//        System.out.println(item.toString());
        item.setImages(images);
        imageService.addImages(images);
        itemService.addItem(item);
        return item;
    }

    @GetMapping
    public List<Item> getAllItem() {
        return itemService.getAllItem();
    }

    @GetMapping(path = "findItemByCategory")
    public List<Item> findItemByCategory(
            @RequestParam String mainCategory,
            @RequestParam String subCategory) {
        return itemService.findItemByCategory(
                new Category(
                        mainCategory,
                        subCategory
                )
        );
    }

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
