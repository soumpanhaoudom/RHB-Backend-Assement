package com.rhb.backendassessment.application.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rhb.backendassessment._shared.constant.movie.MovieCategory;
import com.rhb.backendassessment.application.form.MovieCreateRequest;
import com.rhb.backendassessment.application.form.MovieUpdateRequest;
import com.rhb.backendassessment.application.mapper.MovieApplicationMapper;
import com.rhb.backendassessment.application.view.MovieView;
import com.rhb.backendassessment.model.MovieModel;
import com.rhb.backendassessment.service.MovieService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebMvcTest(MovieController.class)
@AutoConfigureMockMvc
public class MovieControllerTest {

    @MockBean
    private MovieService movieService;

    @MockBean
    private MovieApplicationMapper movieApplicationMapper;

    @Autowired
    MockMvc mockMvc;

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testList() throws Exception{
        List<MovieModel> movieModelList = new ArrayList<>();
        movieModelList.add(createSampleModel());

        List<MovieView> movieViewList = new ArrayList<>();
        movieViewList.add(createSampleView());

        Mockito.when(movieApplicationMapper.from(movieModelList)).thenReturn(movieViewList);
        Mockito.when(movieService.list(20, 0)).thenReturn(movieModelList);

        mockMvc.perform(get("/v1/movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", Matchers.is("SUCCESS")))
                .andExpect(jsonPath("$.total", Matchers.is(1)))
                .andExpect(jsonPath("$.limit", Matchers.is(20)))
                .andExpect(jsonPath("$.offset", Matchers.is(0)))
                .andExpect(jsonPath("$.message", Matchers.is("")))
                .andExpect(jsonPath("$.data[0].id", Matchers.is(1)))
                .andExpect(jsonPath("$.data[0].title", Matchers.is("FAST FIVE")))
                .andExpect(jsonPath("$.data[0].category", Matchers.is("ACTION")))
                .andExpect(jsonPath("$.data[0].starRating", Matchers.is(5.0)))
                .andExpect(jsonPath("$.data[0].createdAt", Matchers.is("2022-02-21T11:57:11")))
                .andExpect(jsonPath("$.data[0].updatedAt", Matchers.is("2022-02-21T11:57:11")));
    }

    @Test
    public void testGet() throws Exception {
        MovieModel movieModel = createSampleModel();
        MovieView movieView = createSampleView();

        Mockito.when(movieApplicationMapper.from(movieModel)).thenReturn(movieView);
        Mockito.when(movieService.get(1l)).thenReturn(movieModel);

        mockMvc.perform(get("/v1/movies/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", Matchers.is("SUCCESS")))
                .andExpect(jsonPath("$.message", Matchers.is("")))
                .andExpect(jsonPath("$.data.id", Matchers.is(1)))
                .andExpect(jsonPath("$.data.title", Matchers.is("FAST FIVE")))
                .andExpect(jsonPath("$.data.category", Matchers.is("ACTION")))
                .andExpect(jsonPath("$.data.starRating", Matchers.is(5.0)))
                .andExpect(jsonPath("$.data.createdAt", Matchers.is("2022-02-21T11:57:11")))
                .andExpect(jsonPath("$.data.updatedAt", Matchers.is("2022-02-21T11:57:11")));
    }

    @Test
    public void testGetNotFound() throws Exception {
        MovieModel movieModel = createSampleModel();
        MovieView movieView = createSampleView();

        Mockito.when(movieApplicationMapper.from(movieModel)).thenReturn(movieView);
        Mockito.when(movieService.get(1111l)).thenThrow(EntityNotFoundException.class);

        mockMvc.perform(get("/v1/movies/1111"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDelete() throws Exception{
        mockMvc.perform(delete("/v1/movies/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testCreate() throws Exception{
        MovieModel movieModel = createSampleModel();
        MovieView movieView = createSampleView();

        MovieCreateRequest createRequest = new MovieCreateRequest("FAST FIVE", MovieCategory.ACTION, 5.0);

        Mockito.when(movieApplicationMapper.from(movieModel)).thenReturn(movieView);
        Mockito.when(movieService.create(createRequest)).thenReturn(movieModel);

        String jsonString =
            "{\n"
                + "    \"title\":\"FAST FIVE\",\n"
                + "    \"category\":\"ACTION\",\n"
                + "    \"starRating\": 5.0\n"
                + "}";

        mockMvc.perform(post("/v1/movies").contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonString))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.code", Matchers.is("SUCCESS")))
                .andExpect(jsonPath("$.message", Matchers.is("")))
                .andExpect(jsonPath("$.data.id", Matchers.is(1)))
                .andExpect(jsonPath("$.data.title", Matchers.is("FAST FIVE")))
                .andExpect(jsonPath("$.data.category", Matchers.is("ACTION")))
                .andExpect(jsonPath("$.data.starRating", Matchers.is(5.0)))
                .andExpect(jsonPath("$.data.createdAt", Matchers.is("2022-02-21T11:57:11")))
                .andExpect(jsonPath("$.data.updatedAt", Matchers.is("2022-02-21T11:57:11")));
    }

    @Test
    public void testCreateFailed() throws Exception{
        MovieModel movieModel = createSampleModel();
        MovieView movieView = createSampleView();

        MovieCreateRequest createRequest = new MovieCreateRequest("FAST FIVE", MovieCategory.ACTION, 5.0);

        Mockito.when(movieApplicationMapper.from(movieModel)).thenReturn(movieView);
        Mockito.when(movieService.create(createRequest)).thenReturn(movieModel);

        String jsonString =
                "{\n"
                        + "    \"title\":\"\",\n"
                        + "    \"category\":\"ACTIONHello\",\n"
                        + "    \"starRating\": 6.0\n"
                        + "}";

        mockMvc.perform(post("/v1/movies").contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonString))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testUpdate() throws Exception{
        MovieModel movieModel = createSampleModel();
        MovieView movieView = createSampleView();

        MovieUpdateRequest updateRequest = new MovieUpdateRequest(Optional.of("FAST FIVE"), Optional.of(MovieCategory.ACTION), Optional.of(5.0));

        Mockito.when(movieApplicationMapper.from(movieModel)).thenReturn(movieView);
        Mockito.when(movieService.update(updateRequest, 1l)).thenReturn(movieModel);

        String jsonString =
                "{\n"
                        + "    \"title\":\"FAST FIVE\",\n"
                        + "    \"category\":\"ACTION\",\n"
                        + "    \"starRating\": 5.0\n"
                        + "}";

        mockMvc.perform(patch("/v1/movies/1").contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonString))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", Matchers.is("SUCCESS")))
                .andExpect(jsonPath("$.message", Matchers.is("")))
                .andExpect(jsonPath("$.data.id", Matchers.is(1)))
                .andExpect(jsonPath("$.data.title", Matchers.is("FAST FIVE")))
                .andExpect(jsonPath("$.data.category", Matchers.is("ACTION")))
                .andExpect(jsonPath("$.data.starRating", Matchers.is(5.0)))
                .andExpect(jsonPath("$.data.createdAt", Matchers.is("2022-02-21T11:57:11")))
                .andExpect(jsonPath("$.data.updatedAt", Matchers.is("2022-02-21T11:57:11")));
    }

    @Test
    public void testUpdateFailed() throws Exception{
        MovieModel movieModel = createSampleModel();
        MovieView movieView = createSampleView();

        MovieUpdateRequest updateRequest = new MovieUpdateRequest(Optional.of("FAST FIVE"), Optional.of(MovieCategory.ACTION), Optional.of(5.0));

        Mockito.when(movieApplicationMapper.from(movieModel)).thenReturn(movieView);
        Mockito.when(movieService.update(updateRequest, 1l)).thenReturn(movieModel);

        String jsonString =
                "{\n"
                        + "    \"title\":\"\",\n"
                        + "    \"category\":\"ACTIONHello\",\n"
                        + "    \"starRating\": 6.0\n"
                        + "}";

        mockMvc.perform(patch("/v1/movies/1").contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonString))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testUpdateNotFound() throws Exception {
        MovieModel movieModel = createSampleModel();
        MovieView movieView = createSampleView();

        MovieUpdateRequest updateRequest = new MovieUpdateRequest(Optional.of("FAST FIVE"), Optional.of(MovieCategory.ACTION), Optional.of(5.0));

        Mockito.when(movieApplicationMapper.from(movieModel)).thenReturn(movieView);
        Mockito.when(movieService.update(updateRequest, 1111l)).thenThrow(EntityNotFoundException.class);

        String jsonString =
                "{\n"
                        + "    \"title\":\"FAST FIVE\",\n"
                        + "    \"category\":\"ACTION\",\n"
                        + "    \"starRating\": 5.0\n"
                        + "}";

        mockMvc.perform(patch("/v1/movies/1111").contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonString))
                .andExpect(status().isNotFound());
    }

    /**
     * create sample MovieView
     * @return
     * @throws JsonProcessingException
     */
    public static MovieView createSampleView() throws JsonProcessingException {
        String jsonString = "{\n" +
                "            \"id\": 1,\n" +
                "            \"title\": \"FAST FIVE\",\n" +
                "            \"category\": \"ACTION\",\n" +
                "            \"starRating\": 5.0,\n" +
                "            \"createdAt\": \"2022-02-21T11:57:11\",\n" +
                "            \"updatedAt\": \"2022-02-21T11:57:11\"\n" +
                "        }";
        return objectMapper.readValue(jsonString, MovieView.class);
    }

  /**
   * create sample MovieModel
   *
   * @return
   * @throws JsonProcessingException
   */
  public static MovieModel createSampleModel() {
    return MovieModel.of(
        1l, "FAST FIVE", MovieCategory.ACTION, 5.0, LocalDateTime.now(), LocalDateTime.now());
  }
}
