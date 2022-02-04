package com.atlas.mygoods.configs;

import com.atlas.mygoods.models.Image;
import com.atlas.mygoods.models.Item;
import com.atlas.mygoods.repositories.ItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Configuration
public class ItemConfig {
    @Bean
    CommandLineRunner commandLineRunner(ItemRepository repository) {
        return args -> {
            Item item = new Item();
            item.setItemid(1L);
            item.setName("Iphone 13 Pro Max");
            item.setAddress("Something is address");
            item.setSubCategory("Phone");
            item.setMainCategory("Electronic");
            item.setDescription("this is description");
            item.setUserid("thisIsUserId");
            item.setPhone("012345678");

//            item.setImages(List.of(new Image("urlImage", "imageName")));
            item.setAmount(2);
            item.setPrice(123);
            item.setViewers(List.of("user1", "user2"));
            item.setViews(2);
            item.setDate(new Date());

            repository.save(item);
        };
    }

    ;

}
