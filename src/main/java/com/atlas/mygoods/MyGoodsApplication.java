package com.atlas.mygoods;


import com.atlas.mygoods.models.Image;
import com.atlas.mygoods.models.User.Role;
import com.atlas.mygoods.models.User.User;
import com.atlas.mygoods.services.CategoryService;
import com.atlas.mygoods.services.ImageService;
import com.atlas.mygoods.services.Impl.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
@Configuration
public class MyGoodsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyGoodsApplication.class, args);
    }

    @GetMapping(path = "/")
    public @ResponseBody
    String greeting() {
        return "This is myGood backend";
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowUrlEncodedSlash(true);
        firewall.setAllowUrlEncodedDoubleSlash(true);
        return firewall;
    }


    @Bean
    CommandLineRunner run(UserService userService, ImageService imageService, CategoryService categoryService) {
        return args -> {
//            final MainCategory mainCategory = new MainCategory("Electronic");
//            final MainCategory mainCategory1 = new MainCategory("Car & Vehicle");
//
//            final SubCategory category = new SubCategory("Phone");
//            final SubCategory category1 = new SubCategory(mainCategory, "Laptop");
//            final SubCategory category2 = new SubCategory(mainCategory1, "Cars");
//
//            categoryService.addCategory(new Category(mainCategory, category));
//            categoryService.addCategory(category1);
//            categoryService.addCategory(category2);

//            userService.saveRole(new Role(null, "ROLE_USER"));
//            userService.saveRole(new Role(null, "ROLE_MANAGER"));
//            userService.saveRole(new Role(null, "ROLE_ADMIN"));
//
////            imageService.addImage(new Image(1L, "something", "imageURl", null));
//
//            final User user1 = userService.saveUser(new User(
//                    null,
//                    "password",
//                    "Jack",
//                    "Atlas",
//                    "atlas599",
//                    "016409637",
//                    List.of("016409637", "012409637"),
//                    "atlas59@example.com",
//                    List.of(new Image("something1", "imageURl1")),
//                    "this is my address",
//                    new ArrayList<>()
//            ));
//            final User user2 = userService.saveUser(new User(
//                    null,
//                    "newPassword",
//                    "Michael",
//                    "Orton",
//                    "Orton899",
//                    "077585344",
//                    List.of("077585344", "078585344"),
//                    "michael59@example.com",
//                    List.of(new Image("something", "imageURl")),
//                    "this is my address",
//                    new ArrayList<>()
//            ));
//            userService.addRoleToUser(user1.getId(), "ROLE_USER");
//            userService.addRoleToUser(user2.getId(), "ROLE_MANAGER");
        };
    }


}
