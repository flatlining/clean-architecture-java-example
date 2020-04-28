package dev.schertel.cq.presenter.rest.circular;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.schertel.cq.core.domain.Circular;
import dev.schertel.cq.core.usecase.circular.*;
import dev.schertel.cq.presenter.rest.entity.CircularRequest;
import dev.schertel.cq.presenter.rest.entity.CircularResponse;
import dev.schertel.cq.presenter.usecase.UseCaseExecutorImpl;
import io.github.glytching.junit.extension.random.Random;
import io.github.glytching.junit.extension.random.RandomBeansExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(RandomBeansExtension.class)
@WebMvcTest(controllers = CircularController.class)
class CircularControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @SpyBean
    private CircularMapper circularMapper;
    @SpyBean
    private UseCaseExecutorImpl useCaseExecutor;

    @MockBean
    private CreateCircularUseCase createCircularUseCase;
    @MockBean
    private ReadAllCircularUseCase readAllCircularUseCase;
    @MockBean
    private ReadCircularUseCase readCircularUseCase;
    @MockBean
    private DeleteAllCircularUseCase deleteAllCircularUseCase;
    @MockBean
    private DeleteCircularUseCase deleteCircularUseCase;

    @BeforeEach
    void setUp() {
        reset(
                createCircularUseCase,
                readAllCircularUseCase,
                readCircularUseCase,
                deleteAllCircularUseCase,
                deleteCircularUseCase
        );
    }

    @Test
    void postCreateSuccessfully(@Random Circular circular) throws Exception {
        // Background
        CreateCircularUseCase.InputValues input = CreateCircularUseCase.InputValues.builder()
                .withName(circular.getName())
                .withDescription(circular.getDescription())
                .build();
        CreateCircularUseCase.OutputValues output = CreateCircularUseCase.OutputValues.builder()
                .withCircular(Circular.builder()
                        .withId(circular.getId())
                        .withName(circular.getName())
                        .withDescription(circular.getDescription())
                        .build())
                .build();
        doReturn(output).when(createCircularUseCase).execute(eq(input));

        // Given
        CircularRequest content = CircularRequest.builder()
                .withName(circular.getName())
                .withDescription(circular.getDescription())
                .build();
        RequestBuilder request = post("/circular")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(content));

        // When
        MvcResult result = mockMvc.perform(request)
                .andExpect(request().asyncStarted())
                .andReturn();

        // Then
        CircularResponse expected = CircularResponse.builder()
                .withId(circular.getId().getId())
                .withName(circular.getName())
                .withDescription(circular.getDescription())
                .build();

        mockMvc.perform(asyncDispatch(result))
                .andExpect(status().isCreated())
                .andExpect(actual -> {
                    assertThat(actual.getResponse().getContentType()).isEqualTo(MediaType.APPLICATION_JSON_VALUE);
                    assertThat(actual.getResponse().getContentAsString()).isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(expected));
                });
    }

    @Test
    void getReadAllSuccessfully(@Random(size = 5, type = Circular.class) List<Circular> circulars) throws Exception {
        // Background
        ReadAllCircularUseCase.InputValues input = ReadAllCircularUseCase.InputValues.builder().build();
        ReadAllCircularUseCase.OutputValues output = ReadAllCircularUseCase.OutputValues.builder()
                .withCircular(circulars)
                .build();
        doReturn(output).when(readAllCircularUseCase).execute(eq(null));

        // Given
        RequestBuilder request = get("/circular");

        // When
        MvcResult result = mockMvc.perform(request)
                .andExpect(request().asyncStarted())
                .andReturn();

        // Then
        List<CircularResponse> expected = circulars.stream().map(c -> CircularResponse.builder()
                .withId(c.getId().getId())
                .withName(c.getName())
                .withDescription(c.getDescription()).build()).collect(Collectors.toList());

        mockMvc.perform(asyncDispatch(result))
                .andExpect(status().isOk())
                .andExpect(actual -> {
                    assertThat(actual.getResponse().getContentType()).isEqualTo(MediaType.APPLICATION_JSON_VALUE);
                    assertThat(actual.getResponse().getContentAsString()).isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(expected));
                });
    }

    @Test
    void getReadSuccessfully(@Random Circular circular) throws Exception {
        // Background
        ReadCircularUseCase.InputValues input = ReadCircularUseCase.InputValues.builder()
                .withIdentity(circular.getId())
                .build();
        ReadCircularUseCase.OutputValues output = ReadCircularUseCase.OutputValues.builder()
                .withCircular(circular)
                .build();
        doReturn(output).when(readCircularUseCase).execute(eq(input));

        // Given
        RequestBuilder request = get("/circular/{id}", circular.getId().getId());

        // When
        MvcResult result = mockMvc.perform(request)
                .andExpect(request().asyncStarted())
                .andReturn();

        // Then
        CircularResponse expected = CircularResponse.builder()
                .withId(circular.getId().getId())
                .withName(circular.getName())
                .withDescription(circular.getDescription())
                .build();

        mockMvc.perform(asyncDispatch(result))
                .andExpect(status().isOk())
                .andExpect(actual -> {
                    assertThat(actual.getResponse().getContentType()).isEqualTo(MediaType.APPLICATION_JSON_VALUE);
                    assertThat(actual.getResponse().getContentAsString()).isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(expected));
                });
    }

    @TestConfiguration
    @ComponentScan(basePackages = {"dev.schertel.cq.presenter.rest.circular"})
    static class Config {
    }
}