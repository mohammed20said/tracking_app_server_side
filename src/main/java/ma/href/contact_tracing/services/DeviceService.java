package ma.href.contact_tracing.services;

import ma.href.contact_tracing.entities.Device;
import ma.href.contact_tracing.entities.dto.ToggleInfectedRequest;
import ma.href.contact_tracing.exceptions.ResourceNotFoundException;
import ma.href.contact_tracing.repositories.DeviceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public List<Device> findAllDevices() {
        return deviceRepository.findAll();
    }

    public Device findDeviceByUdId(String udId) {
        return deviceRepository.findByUdId(udId).orElseThrow(() -> new ResourceNotFoundException("Device not found"));
    }

    public void saveDevice(Device device) {
        deviceRepository.save(device);
    }

    public void deleteByUdId(String udId) {
        deviceRepository.deleteByUdId(udId);
    }

    public void toggleInfected(ToggleInfectedRequest request){
        Device device = findDeviceByUdId(request.getUdId());
        device.setInfected(request.isInfected());
        deviceRepository.save(device);
        if (request.isInfected()){
            /* todo: schedule the notifs to possible infected devices */
        }
    }
}
