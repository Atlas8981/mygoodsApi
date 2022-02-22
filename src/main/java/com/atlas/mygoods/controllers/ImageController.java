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
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String randomCode = RandomString.make(64);
        String uploadDir = "user-photos/";
        imageService.addImage(new Image(uploadDir + fileName, fileName));
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        return "done";
    }

    @GetMapping(path = "/get-image", value = "/get-image")
    public @ResponseBody
    byte[] getImage() throws IOException {
        InputStream in = getClass().getResourceAsStream("C:\\Users\\M\\Desktop\\mygoods\\user-photos\\3 light.jpg");
        return IOUtils.toByteArray(in);
    }

    @GetMapping
    public List<Image> getAllImage() {
        return imageService.getAllImage();
    }
}

