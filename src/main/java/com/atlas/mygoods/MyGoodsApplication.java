package com.atlas.mygoods;

import com.atlas.mygoods.models.Image;
import com.atlas.mygoods.models.Role;
import com.atlas.mygoods.models.User;
import com.atlas.mygoods.services.ImageService;
import com.atlas.mygoods.services.UserServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@SpringBootApplication
@RestController
public class MyGoodsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyGoodsApplication.class, args);
    }

    @GetMapping(path = "/")
    public String greeting() {
        return "This is myGood backend";
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(UserServiceImpl userService, ImageService imageService) {
        return args -> {
            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_MANAGER"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));

//            imageService.addImage(new Image(1L, "something", "imageURl", null));

            final User user1 = userService.saveUser(new User(
                    null,
                    "password",
                    "Jack",
                    "Atlas",
                    "atlas599",
                    "016409637",
                    "atlas59@example.com",
                    new Image(null, "something1", "imageURl", null),
                    "this is my address",
                    new ArrayList<>()
            ));
            final User user2 = userService.saveUser(new User(
                    null,
                    "newPassword",
                    "Michael",
                    "Orton",
                    "Orton899",
                    "016409637",
                    "atlas59@example.com",
                    new Image(null, "something", "imageURl", null),
                    "this is my address",
                    new ArrayList<>()
            ));
            userService.addRoleToUser(user1.getId(), "ROLE_USER");
            userService.addRoleToUser(user2.getId(), "ROLE_MANAGER");
        };
    }


}
