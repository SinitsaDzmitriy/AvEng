package edu.sam.aveng.web;

import edu.sam.spittr.data.SpitterRepository;
import edu.sam.spittr.entities.Spitter;
import edu.sam.spittr.web.SpitterController;

import org.junit.Test;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import org.mockito.Mockito;

public class SpitterControllerTest {
    @Test
    public void shouldShowRegistration() throws Exception {
        SpitterRepository mockRepository = Mockito.mock(SpitterRepository.class);
        SpitterController controller = new SpitterController(mockRepository);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/spitter/register"))
                .andExpect(MockMvcResultMatchers.view().name("registerForm"));
    }

    @Test
    public void registrationProcessTest() throws Exception {
        SpitterRepository mockRepository = Mockito.mock(SpitterRepository.class);
        Spitter unsaved = new Spitter("jbauer", "24hours", "Jack", "Bauer");
        Spitter saved = new Spitter(24L, "jbauer", "24hours", "Jack", "Bauer");
        Mockito.when(mockRepository.save(unsaved)).thenReturn(saved);
        SpitterController controller = new SpitterController(mockRepository);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        // As part of that POST request, user information is passed as
        // parameters on the request to simulate a form being submitted.

        // Best practice: Send a redirect after the POST has completed processing
        // so that a browser refresh won’t accidentally submit the form a 2nd time.

        mockMvc.perform(MockMvcRequestBuilders.post("/spitter/register")
                .param("firstN" +
                        "ame", "Jack")
                .param("lastName", "Bauer")
                .param("username", "jbauer")
                .param("password", "24hours"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/spitter/jbauer"));

        // Verify that the mocked SpitterRepository was actually
        // used to save the data coming in on the form.

        // ToDo: Form deeper understanding on what is going on here.
        Mockito.verify(mockRepository, Mockito.atLeastOnce()).save(unsaved);
    }
}
