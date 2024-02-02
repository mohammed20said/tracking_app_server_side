package ma.href.contact_tracing.controllers;

import ma.href.contact_tracing.entities.Device;
import ma.href.contact_tracing.entities.dto.ToggleInfectedRequest;
import ma.href.contact_tracing.services.DeviceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping
    public ResponseEntity<List<Device>> getAllDevices() {
        return ResponseEntity.ok(deviceService.findAllDevices());
    }

    @GetMapping("/{udId}")
    public ResponseEntity<Device> findDeviceByUdId(@PathVariable("udId") String udId) {
        return ResponseEntity.ok(deviceService.findDeviceByUdId(udId));
    }

    @PostMapping
    public void saveDevice(@RequestBody Device device) {
     deviceService.saveDevice(device);
    }

    @DeleteMapping("/{udId}")
    public void deleteByUdId(@PathVariable("udId") String udId) {
        deviceService.deleteByUdId(udId);
    }

    @PatchMapping("/toggle-infected")
    public void setInfected(@RequestBody ToggleInfectedRequest request){
        this.deviceService.toggleInfected(request);
    }
}
