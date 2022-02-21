package com.atlas.mygoods.services;

import com.atlas.mygoods.models.Role;
import com.atlas.mygoods.models.User;
import com.atlas.mygoods.repositories.ImageRepository;
import com.atlas.mygoods.repositories.RoleRepository;
import com.atlas.mygoods.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final ImageRepository imageRepository;

    private final PasswordEncoder passwordEncoder;

    //    Find user in database to check login
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        System.out.println("UserServiceImpl loadUserByUsername: " + username);
        User user = userRepo.findByUsernameOrPrimaryPhoneOrEmail(username, username, username);
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

    public User saveUser(User user) {
        log.info("Saved User");
        User tempUser = userRepo.findByUsername(user.getUsername());
        if (tempUser != null) {
            throw new IllegalStateException("Username Taken");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        imageRepository.saveAll(user.getImage());
        return userRepo.save(user);
    }

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public User register(User user, String siteUrl) throws UnsupportedEncodingException, MessagingException {
        log.info("Saved User");
        User tempUser = userRepo.findByUsername(user.getUsername());
        if (tempUser != null) {
            throw new IllegalStateException("Username Taken");
        }

        String randomCode = RandomString.make(64);
        user.setVerificationCode(randomCode);
        user.setEnabled(false);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        imageRepository.saveAll(user.getImage());
        final User savedUser = userRepo.save(user);

        sendVerificationEmail(user, siteUrl);

        return savedUser;
    }

    private void sendVerificationEmail(User user, String siteUrl) throws MessagingException, UnsupportedEncodingException {
        String toAddress = user.getEmail();
        String fromAddress = "adampeterson8981@gmail.com";
        String senderName = "MyGood";
        String subject = "Please verify your registration";
        String content = "Dear [[name]],<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3><br>"
                + "Code: [[code]]<br>"
                + "Thank you,<br>"
                + "MyGood.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);


        content = content.replace("[[name]]", user.getFullName());
        String verifyURL = siteUrl + "/api/verify?code=" + user.getVerificationCode();

        content = content.replace("[[URL]]", verifyURL);
        content = content.replace("[[code]]", user.getVerificationCode());

        helper.setText(content, true);

        mailSender.send(message);
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

    @Override
    public boolean verify(String verificationCode) {
        User user = userRepo.findByVerificationCode(verificationCode);

        if (user == null || user.isEnabled()) {
            return false;
        } else {
            user.setVerificationCode(null);
            user.setEnabled(true);
            userRepo.save(user);

            return true;
        }

    }
}
