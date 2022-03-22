package com.atlas.mygoods;


import com.atlas.mygoods.firebase.Patient;
import com.atlas.mygoods.firebase.PatientService;
import com.atlas.mygoods.models.Image;
import com.atlas.mygoods.models.Item.Category.Category;
import com.atlas.mygoods.models.Item.Item;
import com.atlas.mygoods.models.User.Role;
import com.atlas.mygoods.models.User.User;
import com.atlas.mygoods.passwordless.EmailSender;
import com.atlas.mygoods.passwordless.InMemoryTokenStore;
import com.atlas.mygoods.passwordless.SpringSecurityAuthenticator;
import com.atlas.mygoods.services.CategoryService;
import com.atlas.mygoods.services.ImageService;
import com.atlas.mygoods.services.Impl.UserService;
import com.atlas.mygoods.services.ItemService;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
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
//@ComponentScan(basePackages = {
//        "com.google.firebase.messaging.FirebaseMessaging",
//        "com.atlas.mygoods.services.Impl.UserService"
//}) //to scan
public class MyGoodsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyGoodsApplication.class, args);
    }

    @GetMapping(path = "/")
    public @ResponseBody
    String greeting() {
        return "This is myGood backend";
    }

    @GetMapping(path = "/accessDenied")
    public String accessDenied() {
        return "Access Denied";
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
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    InMemoryTokenStore tokenStore() {
        return new InMemoryTokenStore();
    }

    @Bean
    EmailSender sender(JavaMailSender aJavaMailSender) {
        return new EmailSender(aJavaMailSender);
    }

    @Bean
    SpringSecurityAuthenticator authenticator() {
        return new SpringSecurityAuthenticator(tokenStore());
    }


    @Bean
    CommandLineRunner run(UserService userService,
                          ImageService imageService,
                          CategoryService categoryService,
                          ItemService itemService,
                          PatientService patientService) {
        return args -> {
//            patientService.savePatientDetails(new Patient(
//                    "Somethign",
//                    1,
//                    "City"
//            ));

//            FileInputStream serviceAccount =
//                    new FileInputStream("C:\\Users\\M\\Desktop\\mygoods\\files\\google-admin.json");
//
//            FirebaseOptions options = new FirebaseOptions.Builder()
//                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                    .build();
//
//            FirebaseApp.initializeApp(options);


//            categoryService.addCategory("Electronic", "Phone");
//            categoryService.addCategory("Electronic", "Laptop");
//            categoryService.addCategory("Cars and Vehicle", "Car");
//            saveUser(userService);
        };
    }


    void saveUser(UserService userService) {
        userService.saveRole(new Role(null, "ROLE_USER"));
        userService.saveRole(new Role(null, "ROLE_MANAGER"));
        userService.saveRole(new Role(null, "ROLE_ADMIN"));

        final User user1 = userService.saveUser(new User(
                null,
                "password",
                "Jack",
                "Atlas",
                "atlas599",
                "016409637",
                List.of("016409637", "012409637"),
                "atlas59@example.com",
                List.of(new Image("something1", "imageURl1")),
                "this is my address",
                new ArrayList<>()
        ));
        final User user2 = userService.saveUser(new User(
                null,
                "newPassword",
                "Michael",
                "Orton",
                "Orton899",
                "077585344",
                List.of("077585344", "078585344"),
                "michael59@example.com",
                List.of(new Image("something", "imageURl")),
                "this is my address",
                new ArrayList<>()
        ));
        userService.addRoleToUser(user1.getId(), "ROLE_USER");
        userService.addRoleToUser(user2.getId(), "ROLE_MANAGER");
    }


}
