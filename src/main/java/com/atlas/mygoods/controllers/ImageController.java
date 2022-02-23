package com.atlas.mygoods.controllers;

import com.atlas.mygoods.models.Image;
import com.atlas.mygoods.services.ImageService;
import com.atlas.mygoods.utils.FileUploadUtil;
import net.bytebuddy.utility.RandomString;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
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
        if (multipartFile.isEmpty()) {
            return "failed: image is empty";
        }
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String imageBase64 = Base64.getEncoder().encodeToString(multipartFile.getBytes());
        imageService.addImage(new Image(imageBase64, fileName));
//        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        return "done";
    }

    @GetMapping(value = "/image", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody
    byte[] getImage() throws IOException {

        InputStream in = getClass()
                .getResourceAsStream("/user-photos/picture.jpg");
        System.out.println("getImage" + in);
        assert in != null;
        return IOUtils.toByteArray(in);
    }


    @GetMapping
    public List<Image> getAllImage() {
        return imageService.getAllImage();
    }
}

