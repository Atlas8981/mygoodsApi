package com.atlas.mygoods.services.Impl;

import com.atlas.mygoods.models.User.Role;
import com.atlas.mygoods.models.User.User;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface UserService {
    User saveUser(User user);

    User register(User user, String siteUrl) throws UnsupportedEncodingException, MessagingException;

    Role saveRole(Role role);

    void addRoleToUser(Long userId, String roleName);

    User getUser(String username);

    List<User> getUsers();

    boolean verify(String username, String verificationCode);
}
