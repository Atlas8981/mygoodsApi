package com.atlas.mygoods.passwordless;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Principal;


public class SpringSecurityAuthenticator {

    private final InMemoryTokenStore tokenStore;
    @Autowired
    AuthenticationManager authenticationManager;

    public SpringSecurityAuthenticator(InMemoryTokenStore tokenStore) {
        this.tokenStore = tokenStore;
    }

    public Principal authenticate(String aUserId, String aToken) {
        String token = tokenStore.get(aUserId);
        System.out.println("authenticate: " + token);
        System.out.println("aToken: " + aToken);
        if (aToken.equals(token)) {
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(
                            aUserId,
                            null,
                            AuthorityUtils.createAuthorityList("ROLE_USER")
                    );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            authenticationManager.authenticate(authentication);
            return authentication;
        } else {
            throw new BadCredentialsException("Invalid auth token for user: " + aUserId);
        }
    }

}
