package com.atlas.mygoods.repositories;

import com.atlas.mygoods.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByUsernameOrPrimaryPhoneOrEmail(String username,String phone, String email);
}
