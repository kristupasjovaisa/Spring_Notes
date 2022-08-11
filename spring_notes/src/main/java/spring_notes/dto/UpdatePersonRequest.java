package spring_notes.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class UpdatePersonRequest {
    private UUID personUUID;
    private String name;
    private String lastName;
    private String email;
}
