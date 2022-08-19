package spring_notes.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Builder
@Data
public class NoteResponse {
    private UUID noteUUID;
    private String name;
    private String description;
    private Timestamp createdAt;
}
