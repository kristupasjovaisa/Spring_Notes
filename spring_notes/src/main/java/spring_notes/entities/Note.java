package spring_notes.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Table(name = "notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID noteUUID;
    @Column(name = "name", nullable = false, length = 20)
    private String name;
    @Column(name = "description", nullable = false, length = 20)
    private String description;
    @Column(name = "createdAt",nullable = false)
    private Timestamp createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private Person owner;
}