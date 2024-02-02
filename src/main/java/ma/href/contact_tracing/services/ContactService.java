package ma.href.contact_tracing.services;


import ma.href.contact_tracing.entities.Contact;
import ma.href.contact_tracing.repositories.ContactRepository;
import org.springframework.stereotype.Service;

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

    public List<Contact> findByUdIds(String udId1, String udId2){
        return contactRepository.findByUdId1AndUdId2(udId1, udId2);
    }


}
