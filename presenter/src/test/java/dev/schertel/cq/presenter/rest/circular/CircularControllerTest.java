package dev.schertel.cq.presenter.rest.circular;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.schertel.cq.core.domain.Circular;
import dev.schertel.cq.core.domain.Identity;
import dev.schertel.cq.core.domain.NotFoundException;
import dev.schertel.cq.core.usecase.circular.*;
import dev.schertel.cq.presenter.rest.entity.ApiResponse;
import dev.schertel.cq.presenter.rest.entity.CircularRequest;
import dev.schertel.cq.presenter.rest.entity.CircularResponse;
import dev.schertel.cq.presenter.usecase.UseCaseExecutorImpl;
import io.github.glytching.junit.extension.random.Random;
import io.github.glytching.junit.extension.random.RandomBeansExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
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
import org.springframework.test.web.servlet.ResultActions;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

    private MvcResult makeAsyncRequest(RequestBuilder request) throws Exception {
        return mockMvc.perform(request)
                .andExpect(request().asyncStarted())
                .andReturn();
    }

    private ResultActions getAsyncResponse(MvcResult result) throws Exception {
        return mockMvc.perform(asyncDispatch(result));
    }

    @TestConfiguration
    @ComponentScan(basePackages = {"dev.schertel.cq.presenter.rest.circular"})
    static class Config {
    }

    @Nested
    class Create {
        @Test
        void successWithAllProperties(@Random Circular circular) throws Exception {
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
            MvcResult result = makeAsyncRequest(request);

            // Then
            CircularResponse expected = CircularResponse.builder()
                    .withId(circular.getId().getId())
                    .withName(circular.getName())
                    .withDescription(circular.getDescription())
                    .build();

            getAsyncResponse(result)
                    .andExpect(status().isCreated())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(actual -> {
                        assertThatJson(actual.getResponse().getContentAsString()).isEqualTo(objectMapper.writeValueAsString(expected));
                    });
        }

        @Test
        void successWithNoProperties(@Random Circular forFields) throws Exception {
            Circular circular = Circular.builder()
                    .withId(forFields.getId())
                    .build();

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
            MvcResult result = makeAsyncRequest(request);

            // Then
            CircularResponse expected = CircularResponse.builder()
                    .withId(circular.getId().getId())
                    .withName(circular.getName())
                    .withDescription(circular.getDescription())
                    .build();

            getAsyncResponse(result)
                    .andExpect(status().isCreated())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(actual -> {
                        assertThatJson(actual.getResponse().getContentAsString()).isEqualTo(objectMapper.writeValueAsString(expected));
                    });
        }

        @Test
        void successWithOnlyName(@Random Circular forFields) throws Exception {
            Circular circular = Circular.builder()
                    .withId(forFields.getId())
                    .withName(forFields.getName())
                    .build();

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
            MvcResult result = makeAsyncRequest(request);

            // Then
            CircularResponse expected = CircularResponse.builder()
                    .withId(circular.getId().getId())
                    .withName(circular.getName())
                    .withDescription(circular.getDescription())
                    .build();

            getAsyncResponse(result)
                    .andExpect(status().isCreated())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(actual -> {
                        assertThatJson(actual.getResponse().getContentAsString()).isEqualTo(objectMapper.writeValueAsString(expected));
                    });
        }

        @Test
        void successWithOnlyDescription(@Random Circular forFields) throws Exception {
            Circular circular = Circular.builder()
                    .withId(forFields.getId())
                    .withDescription(forFields.getDescription())
                    .build();

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
            MvcResult result = makeAsyncRequest(request);

            // Then
            CircularResponse expected = CircularResponse.builder()
                    .withId(circular.getId().getId())
                    .withName(circular.getName())
                    .withDescription(circular.getDescription())
                    .build();

            getAsyncResponse(result)
                    .andExpect(status().isCreated())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(actual -> {
                        assertThatJson(actual.getResponse().getContentAsString()).isEqualTo(objectMapper.writeValueAsString(expected));
                    });
        }

        @Test
        void errorUnsupportedMediaType(@Random Circular circular) throws Exception {
            // Given
            CircularRequest content = CircularRequest.builder()
                    .withName(circular.getName())
                    .withDescription(circular.getDescription())
                    .build();
            RequestBuilder request = post("/circular")
                    .contentType(MediaType.TEXT_PLAIN_VALUE)
                    .content(objectMapper.writeValueAsString(content));

            // When
            mockMvc.perform(request)
                    // Then
                    .andExpect(status().isUnsupportedMediaType())
                    .andExpect(actual -> {
                        assertEquals(0, actual.getResponse().getContentLength());
                    })
                    .andReturn();
        }

        @Test
        void errorBadRequestNoBody() throws Exception {
            // Given
            RequestBuilder request = post("/circular")
                    .contentType(MediaType.APPLICATION_JSON_VALUE);

            // When
            mockMvc.perform(request)
                    // Then
                    .andExpect(status().isBadRequest())
                    .andExpect(actual -> {
                        assertEquals(0, actual.getResponse().getContentLength());
                    })
                    .andReturn();
        }
    }

    @Nested
    class ReadAll {
        @Test
        void successWithItems(@Random(size = 5, type = Circular.class) List<Circular> circulars) throws Exception {
            // Background
            ReadAllCircularUseCase.InputValues input = ReadAllCircularUseCase.InputValues.builder().build();
            ReadAllCircularUseCase.OutputValues output = ReadAllCircularUseCase.OutputValues.builder()
                    .withCircular(circulars)
                    .build();
            doReturn(output).when(readAllCircularUseCase).execute(eq(null));

            // Given
            RequestBuilder request = get("/circular");

            // When
            MvcResult result = makeAsyncRequest(request);

            // Then
            List<CircularResponse> expected = circulars.stream().map(c -> CircularResponse.builder()
                    .withId(c.getId().getId())
                    .withName(c.getName())
                    .withDescription(c.getDescription()).build()).collect(Collectors.toList());

            getAsyncResponse(result)
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(actual -> {
                        assertThatJson(actual.getResponse().getContentAsString()).isEqualTo(objectMapper.writeValueAsString(expected));
                    });
        }

        @Test
        void successWithNoItems() throws Exception {
            // Background
            ReadAllCircularUseCase.InputValues input = ReadAllCircularUseCase.InputValues.builder().build();
            ReadAllCircularUseCase.OutputValues output = ReadAllCircularUseCase.OutputValues.builder()
                    .withCircular(Collections.emptyList())
                    .build();
            doReturn(output).when(readAllCircularUseCase).execute(eq(null));

            // Given
            RequestBuilder request = get("/circular");

            // When
            MvcResult result = makeAsyncRequest(request);

            // Then
            List<CircularResponse> expected = Collections.emptyList();

            getAsyncResponse(result)
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(actual -> {
                        assertThatJson(actual.getResponse().getContentAsString()).isEqualTo(objectMapper.writeValueAsString(expected));
                    });
        }
    }

    @Nested
    class Read {
        @Test
        void successWithAllProperties(@Random Circular circular) throws Exception {
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
            MvcResult result = makeAsyncRequest(request);

            // Then
            CircularResponse expected = CircularResponse.builder()
                    .withId(circular.getId().getId())
                    .withName(circular.getName())
                    .withDescription(circular.getDescription())
                    .build();

            getAsyncResponse(result)
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(actual -> {
                        assertThatJson(actual.getResponse().getContentAsString()).isEqualTo(objectMapper.writeValueAsString(expected));
                    });
        }

        @Test
        void errorNotFound(@Random String id) throws Exception {
            // Background
            ReadCircularUseCase.InputValues input = ReadCircularUseCase.InputValues.builder()
                    .withIdentity(Identity.of(id))
                    .build();
            doThrow(NotFoundException.of(id)).when(readCircularUseCase).execute(eq(input));

            // Given
            RequestBuilder request = get("/circular/{id}", id);

            // When
            MvcResult result = makeAsyncRequest(request);

            // Then
            HttpStatus httpStatus = HttpStatus.NOT_FOUND;
            ApiResponse expected = ApiResponse.builder()
                    .withTimestamp(null)
                    .withStatus(httpStatus.value())
                    .withReason(httpStatus.getReasonPhrase())
                    .withMessage(id)
                    .build();

            getAsyncResponse(result)
                    .andExpect(status().isNotFound())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(actual -> {
                        assertThatJson(actual.getResponse().getContentAsString()).whenIgnoringPaths("timestamp").isEqualTo(objectMapper.writeValueAsString(expected));
                    });
        }
    }

    @Nested
    class DeleteAll {
        @Test
        void success() throws Exception {
            // Background
            DeleteAllCircularUseCase.InputValues input = DeleteAllCircularUseCase.InputValues.builder().build();
            DeleteAllCircularUseCase.OutputValues output = DeleteAllCircularUseCase.OutputValues.builder().build();
            doReturn(output).when(deleteAllCircularUseCase).execute(eq(null));

            // Given
            RequestBuilder request = delete("/circular");

            // When
            MvcResult result = makeAsyncRequest(request);

            // Then
            getAsyncResponse(result)
                    .andExpect(status().isNoContent());
        }
    }
}
