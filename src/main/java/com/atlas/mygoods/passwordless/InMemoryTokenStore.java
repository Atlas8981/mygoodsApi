package com.atlas.mygoods.passwordless;

import org.springframework.security.crypto.codec.Hex;
import org.springframework.util.Assert;

import java.security.SecureRandom;
import java.util.Map;

public class InMemoryTokenStore {

    private static final long FIFTEEN_MINS = 15 * 60 * 1000;

    private final Map<String, String> store = new SelfExpiringHashMap<>(FIFTEEN_MINS);

    private final SecureRandom random = new SecureRandom();

    private final int TOKEN_BYTE_SIZE = 16;


    public String create(String email) {
        Assert.notNull(email, "user id can't be null");
        byte[] bytes = new byte[TOKEN_BYTE_SIZE];
        random.nextBytes(bytes);
        String token = String.valueOf(Hex.encode(bytes));
        store.put(email, token);
        System.out.println("Token: " + token);
        System.out.println(store.size());
        return token;
    }

    public String get(String email) {
        Assert.notNull(email, "user id can't be null");
        final String token = store.get(email);
        return token;
    }

}
