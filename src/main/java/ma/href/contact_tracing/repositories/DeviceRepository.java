package ma.href.contact_tracing.repositories;

import ma.href.contact_tracing.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeviceRepository extends JpaRepository<Device, Long> {

    Optional<Device> findByUdId(String udid);

    void deleteByUdId(String udId);
}
