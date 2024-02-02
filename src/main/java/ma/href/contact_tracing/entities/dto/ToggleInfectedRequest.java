package ma.href.contact_tracing.entities.dto;

import lombok.Data;

@Data
public class ToggleInfectedRequest {

    private String udId;
    private boolean infected;
}
