package com.atlas.mygoods.firebase;

import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "firebase")
public class FirebaseMessagingController {

    private final FirebaseMessagingService firebaseMessagingService;

    @Autowired
    public FirebaseMessagingController(FirebaseMessagingService firebaseMessagingService) {
        this.firebaseMessagingService = firebaseMessagingService;
    }


    @GetMapping
    public void sendNotification() throws FirebaseMessagingException {
        firebaseMessagingService.sendNotification("SomethingNote");
    }

}
