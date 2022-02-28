package com.atlas.mygoods.controllers;


import com.atlas.mygoods.models.Image;
import com.atlas.mygoods.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/image")
public class ImageController {
    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/save")
    public String addImage(@RequestParam("image") MultipartFile multipartFile) throws IOException {
        imageService.addImage(multipartFile);
        return "done";
    }

    @PostMapping("/saveMultiple")
    public String addImages(@RequestParam("image") List<MultipartFile> multipartFile) throws IOException {
        imageService.addImages(multipartFile);
        return "done";
    }


    @GetMapping
    public List<Image> getAllImage() {
        return imageService.getAllImage();
    }
}

