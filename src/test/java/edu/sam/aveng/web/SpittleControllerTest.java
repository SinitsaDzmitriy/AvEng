package edu.sam.aveng.web;

import static org.hamcrest.Matchers.*;

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

import edu.sam.spittr.Spittle;
import edu.sam.spittr.data.SpittleRepository;
import edu.sam.spittr.web.SpittleController;

public class SpittleControllerTest {

    @Test
    public void shouldShowRecentSpittles() throws Exception {

        // ?
        List<Spittle> expectedSpittles = createSpittleList(20);

        // T mock(Class<T> classToMock): Creates mock object of given class or interface.
        // Creat a mock implementation of the SpittleRepository interface.
        SpittleRepository mockRepository = Mockito.mock(SpittleRepository.class);

        // when(mock.someMethod(Type arg, ...)).thenDo(): Enables stubbing methods.
        // when the x method is called then ...
        // Make the mockRepository return a list of 20 Spittle objects from its findSpittles() method.
        Mockito.when(mockRepository.findSpittles(Long.MAX_VALUE, 20)).thenReturn(expectedSpittles);

        // Inject that mockRepository into a new SpittleController instance.
        SpittleController controller = new SpittleController(mockRepository);

        // standaloneSetup(): Loads the instance of SpittleController to MockMvcBuilders.
        // setSingleView(): Sets up a single ViewResolver that always returns the provided view instance.
        // build(): Create a MockMvc instance.

        // If default view resolution is left, mockMvc will fail,
        // because the view path will be confused with the controller’s path.

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setSingleView(new InternalResourceView("/WEB-INF/views/spittles.jsp"))
                .build();


        mockMvc.perform(MockMvcRequestBuilders.get("/spittles"))
                .andExpect(MockMvcResultMatchers.view().name("spittles"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("spittleList"))
                .andExpect(MockMvcResultMatchers.model().attribute("spittleList",
                        hasItems(expectedSpittles.toArray())));
    }

    @Test
    public void shouldShowPagedSpittles() throws Exception {
        List<Spittle> expectedSpittles = createSpittleList(50);
        SpittleRepository mockRepository = mock(SpittleRepository.class);
        when(mockRepository.findSpittles(238900, 50))
                .thenReturn(expectedSpittles);

        SpittleController controller = new SpittleController(mockRepository);
        MockMvc mockMvc = standaloneSetup(controller)
                .setSingleView(new InternalResourceView("/WEB-INF/views/spittles.jsp"))
                .build();

        mockMvc.perform(get("/spittles?max=238900&count=50"))
                .andExpect(view().name("spittles"))
                .andExpect(model().attributeExists("spittleList"))
                .andExpect(model().attribute("spittleList",
                        hasItems(expectedSpittles.toArray())));
    }

    @Test
    public void testSpittle() throws Exception {
        Spittle expectedSpittle = new Spittle("Hello", new Date());
        SpittleRepository mockRepository = mock(SpittleRepository.class);
        when(mockRepository.findOne(12345)).thenReturn(expectedSpittle);

        SpittleController controller = new SpittleController(mockRepository);
        MockMvc mockMvc = standaloneSetup(controller).build();

        mockMvc.perform(get("/spittles/12345"))
                .andExpect(view().name("spittle"))
                .andExpect(model().attributeExists("spittle"))
                .andExpect(model().attribute("spittle", expectedSpittle));
    }

    @Test
    public void saveSpittle() throws Exception {
        SpittleRepository mockRepository = mock(SpittleRepository.class);
        SpittleController controller = new SpittleController(mockRepository);
        MockMvc mockMvc = standaloneSetup(controller).build();

        mockMvc.perform(post("/spittles")
                .param("message", "Hello World") // this works, but isn't really testing what really happens
                .param("longitude", "-81.5811668")
                .param("latitude", "28.4159649")
        )
                .andExpect(redirectedUrl("/spittles"));

        verify(mockRepository, atLeastOnce()).save(new Spittle(null, "Hello World", new Date(), -81.5811668, 28.4159649));
    }

    private List<Spittle> createSpittleList(int count) {
        List<Spittle> spittles = new ArrayList<Spittle>();
        for (int i = 0; i < count; i++) {
            spittles.add(new Spittle("Spittle " + i, new Date()));
        }
        return spittles;
    }
}