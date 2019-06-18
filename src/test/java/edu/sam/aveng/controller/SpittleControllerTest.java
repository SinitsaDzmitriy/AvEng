package edu.sam.aveng.controller;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
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
import edu.sam.spittr.controller.Constants;

public class SpittleControllerTest {

    // Testing of a Spittle creation:

    @Test
    public void displaySpittleCreationFormTest() throws Exception {
        SpittleRepository mockRepository = Mockito.mock(SpittleRepository.class);
        SpittleController controller = new SpittleController(mockRepository);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/spittles/create"))
                .andExpect(MockMvcResultMatchers.view().name("spittleCreationForm"))
                .andExpect(MockMvcResultMatchers.model().attributeExists(Constants.Model.SPITTLE_ENTITY_KEY));
    }

    @Test
    public void spittleCreationTest() throws Exception {
        SpittleRepository mockRepository = Mockito.mock(SpittleRepository.class);

        SpittleDTO unsaved = new SpittleDTO.Builder()
                .setMessage("Test")
                .setTime(LocalTime.MIN)
                .setLongitude(0D)
                .setLatitude(0D)
                .build();

        SpittleController controller = new SpittleController(mockRepository);
        Mockito.when(mockRepository.create(unsaved)).thenReturn(0L);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(MockMvcRequestBuilders.post("/spittles/create")
                .param("message", "Test")
                .param("time", "00:00")
                .param("longitude", "0")
                .param("latitude", "0"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/spittles"));

        Mockito.verify(mockRepository, Mockito.atLeastOnce()).create(unsaved);
    }

    // Testing of a Spittle reading:

    @Test
    public void displaySpittleListTest() throws Exception {

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
            .setSingleView(new InternalResourceView("/WEB-INF/views/allSpittles.jsp"))
            .build();

        // Ask the MockMvc instance to:
        // 1. Perform a GET request for /.
        // 2. Expect "spittles" view name.
        // 3. Expect the model that has an attribute named "spittleList" with the expected contents.


        mockMvc.perform(MockMvcRequestBuilders.get("/spittles"))
            .andExpect(MockMvcResultMatchers.view().name("spittles"))
            .andExpect(MockMvcResultMatchers.model().attributeExists("spittleList"))
            .andExpect(MockMvcResultMatchers.model().attribute("spittleList",
                    Matchers.hasItems(expectedSpittles.toArray())));
    }

    @Test
    public void displaySpittleByIdTest() throws Exception {
        SpittleDTO expectedSpittle = new SpittleDTO.Builder().build();
        SpittleRepository mockRepository = Mockito.mock(SpittleRepository.class);
        Mockito.when(mockRepository.readById(12345)).thenReturn(expectedSpittle);
        SpittleController controller = new SpittleController(mockRepository);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .build();

        // Request resource via path
        mockMvc.perform(MockMvcRequestBuilders.get("/spittles/12345"))
            .andExpect(MockMvcResultMatchers.view().name("spittle"))
            .andExpect(MockMvcResultMatchers.model().attributeExists("spittleDTO"))
            .andExpect(MockMvcResultMatchers.model().attribute("spittleDTO", expectedSpittle));
    }

    // Testing of a Spittle update:

    @Test
    public void displaySpittleUpdateFormTest() throws Exception {
        SpittleRepository mockRepository = Mockito.mock(SpittleRepository.class);

        SpittleDTO read = new SpittleDTO.Builder()
                .setId(0L)
                .setMessage("Test")
                .setTime(LocalTime.MIN)
                .setLongitude(0D)
                .setLatitude(0D)
                .build();

        Mockito.when(mockRepository.readById(0L)).thenReturn(read);

        SpittleController controller = new SpittleController(mockRepository);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/spittles/update/0"))
                .andExpect(MockMvcResultMatchers.view().name("spittleUpdateForm"))
                .andExpect(MockMvcResultMatchers.model().attributeExists(Constants.Model.SPITTLE_ENTITY_KEY))
                .andExpect(MockMvcResultMatchers.model().attribute(Constants.Model.SPITTLE_ENTITY_KEY, read));
    }

    @Test
    public void spittleUpdateTest() throws Exception {
        SpittleRepository mockRepository = Mockito.mock(SpittleRepository.class);
        SpittleDTO updated = new SpittleDTO.Builder()
                .setId(1L)
                .setMessage("Test")
                .setTime(LocalTime.MIN)
                .setLongitude(0.0)
                .setLatitude(0.0)
                .build();

        SpittleController controller = new SpittleController(mockRepository);
        Mockito.when(mockRepository.update(1L, updated)).thenReturn(1L);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(MockMvcRequestBuilders.post("/spittles/update/1")
                .param("message", "Test")
                .param("time", "00:00")
                .param("longitude", "0.0")
                .param("latitude", "0.0"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/spittles"));

        Mockito.verify(mockRepository, Mockito.atLeastOnce()).update(1L, updated);
    }

    // Testing of a Spittle deletion:

    @Test
    public void spittleDeletionTest() throws Exception{
        SpittleRepository mockRepository = Mockito.mock(SpittleRepository.class);
        Mockito.when(mockRepository.delete(0L)).thenReturn(0L);
        SpittleController controller = new SpittleController(mockRepository);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/spittles/delete/0"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/spittles"));

        Mockito.verify(mockRepository, Mockito.atLeastOnce()).delete(0L);
    }

    // Auxiliary functions:

    private List<SpittleDTO> createSpittleList(int count) {
        List<SpittleDTO> spittles = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            spittles.add(new SpittleDTO.Builder().setId(i).build());
        }
        return spittles;
    }
}