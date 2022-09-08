package spring_notes.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "persons")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID personUUID;
    @Column(name = "name", nullable = false, length = 20)
    private String name;
    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;
    @Column(name = "email", nullable = false, length = 20)
    private String email;

    @OneToMany(mappedBy = "owner")
    private Set<Note> createdNotes;
}
