package com.atlas.mygoods.services;

import com.atlas.mygoods.models.Role;
import com.atlas.mygoods.models.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    Role saveRole(Role role);

    void addRoleToUser(Long userId, String roleName);

    User getUser(String username);

    List<User> getUsers();
}
