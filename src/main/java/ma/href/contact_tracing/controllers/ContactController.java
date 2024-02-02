package ma.href.contact_tracing.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ma.href.contact_tracing.entities.Notif;
import ma.href.contact_tracing.entities.dto.ContactRequest;
import ma.href.contact_tracing.services.ContactService;
import ma.href.contact_tracing.services.FirebaseMessagingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
@RequestMapping("api/contact")
public class ContactController {

    private final FirebaseMessagingService firebaseMessagingService;
    private final ContactService contactService;

    public ContactController(FirebaseMessagingService firebaseMessagingService, ContactService contactService) {
        this.firebaseMessagingService = firebaseMessagingService;
        this.contactService = contactService;
    }

    @PostMapping
    public void contact(@RequestBody String contactRequests){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<String> jsonStrings = objectMapper.readValue(contactRequests, new TypeReference<List<String>>() {});

            for (String json : jsonStrings) {
                ContactRequest contactRequest = objectMapper.readValue(json, ContactRequest.class);

                LocalDateTime receivedDateTime = contactService.parseDateTimeString(contactRequest.getTimestamp());

                if (contactService.isWithinLast15Days(receivedDateTime)) {
                    Notif notif = new Notif();
                    notif.setSubject("Alert");
                    notif.setContent("You've met someone who is infected!");

                    firebaseMessagingService.sendNotification(notif, contactRequest.getToken());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
