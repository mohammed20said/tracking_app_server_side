package ma.href.contact_tracing.repositories;

import ma.href.contact_tracing.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    List<Contact> findByUdId1AndUdId2(String udId1, String udId2);
}
