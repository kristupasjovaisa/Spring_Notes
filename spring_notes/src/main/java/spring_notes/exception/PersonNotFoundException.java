package spring_notes.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class PersonNotFoundException extends RuntimeException {

    private final UUID personUUID;
}
