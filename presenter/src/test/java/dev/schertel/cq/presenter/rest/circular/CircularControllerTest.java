package dev.schertel.cq.presenter.rest.circular;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.schertel.cq.core.domain.Circular;
import dev.schertel.cq.core.domain.Identity;
import dev.schertel.cq.core.usecase.circular.*;
import dev.schertel.cq.presenter.rest.entity.CircularRequest;
import dev.schertel.cq.presenter.rest.entity.CircularResponse;
import dev.schertel.cq.presenter.usecase.UseCaseExecutorImpl;
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
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    }

    @Test
    void create() throws Exception {
        CircularRequest request = CircularRequest.builder()
                .withName("a name")
                .withDescription("the description")
                .build();
        CircularResponse expected = CircularResponse.builder()
                .withId("1")
                .withName("a name")
                .withDescription("the description")
                .build();

        CreateCircularUseCase.InputValues in = CreateCircularUseCase.InputValues.builder()
                .withName("a name")
                .withDescription("the description")
                .build();
        CreateCircularUseCase.OutputValues out = CreateCircularUseCase.OutputValues.builder()
                .withCircular(Circular.builder()
                        .withId(Identity.of("1"))
                        .withName("a name")
                        .withDescription("the description")
                        .build()
                ).build();
        doReturn(out)
                .when(createCircularUseCase)
                .execute(in);

        MockHttpServletRequestBuilder content = post("/circular")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request));

        RequestBuilder payload = asyncDispatch(mockMvc.perform(content)
                .andExpect(request().asyncStarted())
                .andReturn());

        mockMvc.perform(payload)
                .andExpect(status().isCreated())
                .andExpect(mvcResult -> {
                    String actual = mvcResult.getResponse().getContentAsString();

                    assertThat(objectMapper.writeValueAsString(expected))
                            .isEqualToIgnoringWhitespace(actual);
                });
    }

    @TestConfiguration
    @ComponentScan(basePackages = {"dev.schertel.cq.presenter.rest.circular"})
    static class Config {
    }
}