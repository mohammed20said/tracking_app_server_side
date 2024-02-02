package ma.href.contact_tracing.services;


import ma.href.contact_tracing.entities.Contact;
import ma.href.contact_tracing.repositories.ContactRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public void save(Contact contact){
        contactRepository.save(contact);
    }


    public LocalDateTime parseDateTimeString(String dateTimeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

        return LocalDateTime.parse(dateTimeString, formatter);
    }

    public boolean isWithinLast15Days(LocalDateTime dateToCheck) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime fifteenDaysAgo = now.minus(15, ChronoUnit.DAYS);

        return !dateToCheck.isBefore(fifteenDaysAgo) && !dateToCheck.isAfter(now);
    }


}
