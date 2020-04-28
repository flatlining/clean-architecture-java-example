package dev.schertel.cq.presenter.rest.circular;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.schertel.cq.core.domain.Circular;
import dev.schertel.cq.core.domain.Identity;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

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
    }

    @Test
    void create(@Random String name, @Random String description, @Random String id) throws Exception {
        // Background
        CreateCircularUseCase.InputValues input = CreateCircularUseCase.InputValues.builder()
                .withName(name)
                .withDescription(description)
                .build();
        CreateCircularUseCase.OutputValues output = CreateCircularUseCase.OutputValues.builder()
                .withCircular(Circular.builder()
                        .withId(Identity.of(id))
                        .withName(name)
                        .withDescription(description)
                        .build()
                ).build();
        doReturn(output).when(createCircularUseCase).execute(eq(input));

        // Given
        CircularRequest content = CircularRequest.builder()
                .withName(name)
                .withDescription(description)
                .build();

        // When
        MockHttpServletRequestBuilder request = post("/circular")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(content));
        RequestBuilder action = asyncDispatch(mockMvc.perform(request)
                .andExpect(request().asyncStarted())
                .andReturn());
        MvcResult actual = mockMvc.perform(action).andReturn();

        // Then
        CircularResponse expected = CircularResponse.builder()
                .withId(id)
                .withName(name)
                .withDescription(description)
                .build();

        assertThat(actual.getResponse().getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(actual.getResponse().getContentType()).isEqualTo(MediaType.APPLICATION_JSON_VALUE);
        assertThat(actual.getResponse().getContentAsString()).isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(expected));
    }

    @TestConfiguration
    @ComponentScan(basePackages = {"dev.schertel.cq.presenter.rest.circular"})
    static class Config {
    }
}