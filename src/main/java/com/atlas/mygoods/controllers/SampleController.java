package com.atlas.mygoods.controllers;

import com.atlas.mygoods.passwordless.EmailSender;
import com.atlas.mygoods.passwordless.InMemoryTokenStore;
import com.atlas.mygoods.passwordless.SpringSecurityAuthenticator;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping(path = "passwordless")
public class SampleController {
    private final InMemoryTokenStore tokenStore;
    private final EmailSender sender;
    private final SpringSecurityAuthenticator authenticator;

    public SampleController(InMemoryTokenStore tokenStore, EmailSender sender, SpringSecurityAuthenticator authenticator) {
        this.tokenStore = tokenStore;
        this.sender = sender;
        this.authenticator = authenticator;
    }

    @GetMapping(path = "/sample")
    public String showForm(Authentication authentication, Principal principal) {
        System.out.println(authentication.getName());
        return "sample";
    }


    @PostMapping(path = "/signin")
    public String signin(@RequestParam("email") String aEmail) {

        // verify that the user is in the database.
        // ...

        // send sign-in email
        String token = tokenStore.create(aEmail);
        sender.send(aEmail, token);

        return "login_link_sent";
    }

    @GetMapping(path = "/signin/{token}")
    public String signin(@RequestParam("uid") String email, @PathVariable("token") String aToken) {
        System.out.println("aUid: " + email);
        try {
            authenticator.authenticate(email, aToken);
            return "redirect:/";
        } catch (BadCredentialsException aBadCredentialsException) {
            System.out.println(aBadCredentialsException.getMessage());
            return "invalid_login_link";
        }
    }
}
