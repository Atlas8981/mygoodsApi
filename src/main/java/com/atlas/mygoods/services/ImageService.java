package com.atlas.mygoods.services;

import com.atlas.mygoods.models.Image;
import com.atlas.mygoods.models.Item;
import com.atlas.mygoods.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {
    private final ImageRepository imageRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public List<Image> getAllImage(){
        return imageRepository.findAll();
    }

    public void addImage(Image image){
        imageRepository.save(image);
    }
    public void addImages(List<Image> images){
        imageRepository.saveAll(images);
    }
}
