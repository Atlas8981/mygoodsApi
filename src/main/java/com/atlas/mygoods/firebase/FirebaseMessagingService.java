package com.atlas.mygoods.firebase;

import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FirebaseMessagingService {

    public String sendNotification(String note) throws FirebaseMessagingException {

        final FirebaseMessaging firebaseMessaging = FirebaseMessaging.getInstance();

        final String token = "dlKFjMdfQ8-Q-Vppp-pBvo:APA91bEXv7Ite_yXapD3OHhrmKvRUMKdPHZ_9o_s_IxcWo1xXhAc-4cG5QX7tH9sg4sbGZ7BfTnpPTVSkDkuilm4vRQXaOdhOmHB3NsBCba33dip6xdMP6cj6VCBB3-JPEBURwMLLeeL";
        final Notification notification = Notification
                .builder()
                .setTitle(note)
                .setBody("note body")
                .build();

        final Message message = Message
                .builder()
                .setToken(token)
                .setNotification(notification)
                .build();

        return firebaseMessaging.send(message);
    }
}
