package ma.href.contact_tracing.controllers;


import com.google.firebase.messaging.FirebaseMessagingException;
import ma.href.contact_tracing.entities.Notif;
import ma.href.contact_tracing.services.FirebaseMessagingService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {


    private final FirebaseMessagingService firebaseMessagingService;

    public NotificationController(FirebaseMessagingService firebaseMessagingService) {
        this.firebaseMessagingService = firebaseMessagingService;
    }

    @GetMapping("/test")
    public String test() {
        return "Hello World";
    }


//    @GetMapping
//    public String sendNotification(@RequestParam String token) throws FirebaseMessagingException {
//        Notif notif = new Notif();
//        notif.setSubject("Hi");
//        notif.setContent("انا لله وانا اليه راجعون");
//
//        return firebaseMessagingService.sendNotification(notif, token);
//    }
}
