package spring_notes.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Builder
@Data
public class NoteResponse {
    private String name;
    private String description;
    private Timestamp createdAt;
}
