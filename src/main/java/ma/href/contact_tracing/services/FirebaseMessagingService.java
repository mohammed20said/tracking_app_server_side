package ma.href.contact_tracing.services;


import com.google.firebase.messaging.*;
import ma.href.contact_tracing.entities.Notif;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FirebaseMessagingService {

    private final FirebaseMessaging firebaseMessaging;

    private static int count = 0;

    public FirebaseMessagingService(FirebaseMessaging firebaseMessaging) {
        this.firebaseMessaging = firebaseMessaging;
    }

    public String sendNotification(Notif notif, String token) {

        Notification notification = Notification
                .builder()
                .setTitle(notif.getSubject())
                .setBody(notif.getContent())
                .build();

        System.out.println(notif.getContent());

        Message message = Message
                .builder()
                .setToken(token)
                .setNotification(notification)
                .build();

        try {
            String response = firebaseMessaging.send(message);
            System.out.println("Successfully sent message: " + count++ + response);
            return response;
        } catch (FirebaseMessagingException e) {
            return e.getMessage();
        }
    }

    public BatchResponse sendNotificationToMultipleDevices(Notif note, List<String> tokens) throws FirebaseMessagingException {

        Notification notification= Notification
                .builder()
                .setTitle(note.getSubject())
                .setBody(note.getContent())
                .build();

        MulticastMessage message = MulticastMessage
                .builder()
                .addAllTokens(tokens)
                .setNotification(notification)
                .build();
        return firebaseMessaging.sendMulticast(message);
    }

}
