package spring_notes.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UpdatePersonRequest {
    private String name;
    private String lastName;
    private String email;
}
