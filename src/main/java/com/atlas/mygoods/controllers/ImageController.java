package com.atlas.mygoods.controllers;

import com.atlas.mygoods.models.Image;
import com.atlas.mygoods.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "api/v1/image")
public class ImageController {
    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping
    public void addImage(@RequestBody Image image) {
        imageService.addImage(image);
    }

    @GetMapping
    public List<Image> getAllImage() {
        return imageService.getAllImage();
    }
}
