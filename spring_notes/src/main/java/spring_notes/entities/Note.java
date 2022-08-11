package spring_notes.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID noteUUID;
    private String name;
    private String description;
    private Timestamp createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private Person owner;
}