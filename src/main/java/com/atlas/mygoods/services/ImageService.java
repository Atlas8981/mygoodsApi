package com.atlas.mygoods.services;

import com.atlas.mygoods.models.Image;
import com.atlas.mygoods.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class ImageService {
    private final ImageRepository imageRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public List<Image> getAllImage() {
        return imageRepository.findAll();
    }

    public Image addImage(MultipartFile file) throws IOException {
        if (file == null) {
            return null;
        }
        if (file.getOriginalFilename() == null) {
            return null;
        }
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String imageBase64 = Base64.getEncoder().encodeToString(file.getBytes());
        final Image image = new Image(imageBase64, fileName);

        imageRepository.save(image);
        return image;
    }

    public List<Image> addImages(List<MultipartFile> files) throws IOException {
        final List<Image> images = new ArrayList<>();
        if (files == null || files.size() == 0) {
            return null;
        }
        for (MultipartFile file : files) {
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
        imageRepository.saveAll(images);
        return images;
    }
}
