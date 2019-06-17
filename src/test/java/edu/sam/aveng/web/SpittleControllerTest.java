package edu.sam.aveng.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.view.InternalResourceView;

import edu.sam.spittr.dto.SpittleDTO;
import edu.sam.spittr.repository.SpittleRepository;
import edu.sam.spittr.controller.SpittleController;

public class SpittleControllerTest {

    @Test
    public void shouldShowRecentSpittles() throws Exception {

        // Create List of Spittles for testing
        List<SpittleDTO> expectedSpittles = createSpittleList(20);

        // T mock(Class<T> classToMock): Creates mock object of given class or interface.
        // Creat a mock implementation of the SpittleRepository interface.
        SpittleRepository mockRepository = Mockito.mock(SpittleRepository.class);

        // when(mock.someMethod(Type arg, ...)).thenDo(): Enables stubbing methods.
        // when the x method is called then ...
        // Make the mockRepository return a list of 20 SpittleDTO objects from its readSpittles() method.
        Mockito.when(mockRepository.readSpittles(Long.MAX_VALUE, 20)).thenReturn(expectedSpittles);

        // Inject that mockRepository into a new SpittleController instance.
        SpittleController controller = new SpittleController(mockRepository);

        // standaloneSetup(): Loads the instance of SpittleController to MockMvcBuilders.
        // setSingleView(View view): Sets up a single ViewResolver that always returns the provided view instance.
        // build(): Create a MockMvc instance.

        // If default view resolution is left, mockMvc will fail,
        // because the view path will be confused with the controller’s path.
        // In this case, the mock framework don't try to resolve
        // the view name coming from the controller on its own.

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setSingleView(new InternalResourceView("/WEB-INF/views/spittles.jsp"))
                .build();

        // Ask the MockMvc instance to:
        // 1. Perform a GET request for /.
        // 2. Expect "spittles" view name.
        // 3. Expect the model that has an attribute named "spittleList" with the expected contents.


        mockMvc.perform(MockMvcRequestBuilders.get("/spittles"))
                .andExpect(MockMvcResultMatchers.view().name("spittles"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("spittleList"))
                .andExpect(MockMvcResultMatchers.model().attribute("spittleList",
                        hasItems(expectedSpittles.toArray())));
    }

    @Test
    public void oneSpittleShowByIdTest() throws Exception {
        SpittleDTO expectedSpittle = new SpittleDTO.Builder().build();
        SpittleRepository mockRepository = Mockito.mock(SpittleRepository.class);
        Mockito.when(mockRepository.readById(12345)).thenReturn(expectedSpittle);
        SpittleController controller = new SpittleController(mockRepository);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        // Request resource via path
        mockMvc.perform(MockMvcRequestBuilders.get("/spittles/12345"))
                .andExpect(MockMvcResultMatchers.view().name("spittle"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("spittle"))
                .andExpect(MockMvcResultMatchers.model().attribute("spittle", expectedSpittle));
    }

    private List<SpittleDTO> createSpittleList(int count) {
        List<SpittleDTO> spittles = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            spittles.add(new SpittleDTO.Builder().setId(i).build());
        }
        return spittles;
    }
}