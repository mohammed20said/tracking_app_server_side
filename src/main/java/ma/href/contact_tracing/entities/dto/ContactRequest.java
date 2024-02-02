package ma.href.contact_tracing.entities.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactRequest {

    private String token;
    private String timestamp;

    public ContactRequest(String jsonString) {
        // Deserialize fields from the provided JSON string
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ContactRequest temp = objectMapper.readValue(jsonString, ContactRequest.class);
            // Copy values from 'temp' to this instance
            // For example: this.token = temp.getToken();
        } catch (IOException e) {
            e.printStackTrace(); // Handle exception appropriately
        }
    }

}
