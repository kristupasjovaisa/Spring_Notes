package spring_notes.controllers;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import spring_notes.dto.PersonResponse;
import spring_notes.services.PersonService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;


@WebMvcTest(controllers = PersonApiController.class)
class PersonApiControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    @InjectMocks
    private PersonApiController personApiController;

    @BeforeEach
    public void setUp(WebApplicationContext context) {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void getAllPersons() throws Exception {
        List<PersonResponse> persons = new ArrayList<>();
        persons.add(PersonResponse.builder().build());

        when(personService.getAllPersons()).thenReturn(persons);

        mockMvc.perform(MockMvcRequestBuilders.get("/persons").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(1)));
    }

    @Test
    void addPerson() {
    }

    @Test
    void updatePerson() {
    }

    @Test
    void deletePerson() {
    }
}