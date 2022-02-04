package com.atlas.mygoods;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MyGoodsApplication{

//    @Autowired
//    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(MyGoodsApplication.class, args);
    }

    @GetMapping(path = "/")
    public String greeting() {
        return "Something";
    }

//    @Override
//    public void run(String... args) throws Exception {
//        String sql = "INSERT INTO user (fullname, email, password) VALUES (?, ?, ?)";
//        int result = jdbcTemplate.update(sql, "Jack Atlas", "jack_atlas@example.com", "atlas59");
//
//        if (result > 0) {
//            System.out.println("A new row has been inserted");
//        }
//    }
}
