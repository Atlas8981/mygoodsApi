package com.atlas.mygoods.services;

import com.atlas.mygoods.models.Role;
import com.atlas.mygoods.models.User;
import com.atlas.mygoods.repositories.ImageRepository;
import com.atlas.mygoods.repositories.RoleRepository;
import com.atlas.mygoods.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j //for logging
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final ImageRepository imageRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user == null) {
            log.error("user not found");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database: {}", username);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach((role -> authorities.add(new SimpleGrantedAuthority(role.getName()))));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public User saveUser(User user) {
        log.info("Saved User");
        User tempUser = userRepo.findByUsername(user.getUsername());
        if (tempUser != null) {
            throw new IllegalStateException("Username Taken");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        imageRepository.save(user.getImage());
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saved Role");
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(Long userId, String roleName) {
        User user = userRepo.findById(userId).orElseThrow();
        Role role = roleRepo.findByName(roleName);
        user.getRoles().add(role);
        log.info("Add Role {} to User {}", roleName, user.getUsername());
    }

    @Override
    public User getUser(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        return userRepo.findAll();
    }
}
