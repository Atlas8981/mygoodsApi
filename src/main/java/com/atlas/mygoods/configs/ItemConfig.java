package com.atlas.mygoods.configs;


import com.atlas.mygoods.repositories.ImageRepository;
import com.atlas.mygoods.repositories.ItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ItemConfig {
    @Bean
    CommandLineRunner commandLineRunner(ItemRepository repository, ImageRepository imageRepository) {
        return args -> {
//            Image image = new Image("imageUrl", "ImageName");
//            imageRepository.save(image);
//
//            Item item = new Item();
//            item.setItemid(1L);
//            item.setName("Iphone 13 Pro Max");
//            item.setAddress("Something is address");
//            item.setSubCategory("Phone");
//            item.setMainCategory("Electronic");
//            item.setDescription("this is description");
//            item.setUserid("thisIsUserId");
//            item.setPhone("012345678");
//
//            item.setImages(List.of(image));
//            item.setAmount(2);
//            item.setPrice(123);
//            item.setViewers(List.of("user1", "user2"));
//            item.setViews(2);
//            item.setDate(new Date());
//
//            repository.save(item);
        };
    }


}
