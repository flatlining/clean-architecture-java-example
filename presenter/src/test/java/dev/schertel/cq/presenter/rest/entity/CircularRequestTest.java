package dev.schertel.cq.presenter.rest.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jparams.verifier.tostring.NameStyle;
import com.jparams.verifier.tostring.ToStringVerifier;
import io.github.glytching.junit.extension.random.Random;
import io.github.glytching.junit.extension.random.RandomBeansExtension;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(RandomBeansExtension.class)
class CircularRequestTest {
    private final Class<CircularRequest> CLAZZ = CircularRequest.class;

    private CircularRequest.Builder builder;
    private CircularRequest cut;

    @BeforeEach
    void setUp() {
        this.builder = CircularRequest.builder();
        this.cut = null;
    }

    @Test
    void getName(@Random String name) {
        // Given
        builder
                .withName(name);

        // When
        CircularRequest actual = builder.build();

        // Then
        assertThat(actual).isNotNull().satisfies(circularRequest -> {
            assertThat(circularRequest.getName()).isEqualTo(name);
            assertThat(circularRequest.getDescription()).isNull();
        });
    }

    @Test
    void getDescription(@Random String description) {
        // Given
        builder
                .withDescription(description);

        // When
        CircularRequest actual = builder.build();

        // Then
        assertThat(actual).isNotNull().satisfies(circularRequest -> {
            assertThat(circularRequest.getName()).isNull();
            assertThat(circularRequest.getDescription()).isEqualTo(description);
        });
    }

    @Nested
    class Builder {
        @Test
        void nullObject() {
            // Given

            // When
            CircularRequest actual = builder.build();

            // Then
            assertThat(actual).isNotNull().satisfies(circularRequest -> {
                assertThat(circularRequest.getName()).isNull();
                assertThat(circularRequest.getDescription()).isNull();
            });
        }

        @Test
        void fullObject(@Random String name, @Random String description) {
            // Given
            builder
                    .withName(name)
                    .withDescription(description);

            // When
            CircularRequest actual = builder.build();

            // Then
            assertThat(actual).isNotNull().satisfies(circularRequest -> {
                assertThat(circularRequest.getName()).isEqualTo(name);
                assertThat(circularRequest.getDescription()).isEqualTo(description);
            });
        }
    }

    @Nested
    class JSON {
        @Test
        void serializationDeserialization(@Random String name, @Random String description) throws JsonProcessingException {
            ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

            String json = String.format("{\n" +
                    "  \"name\":\"%s\",\n" +
                    "  \"description\":\"%s\"\n" +
                    "}", name, description);
            CircularRequest entityFromJson = mapper.readValue(json, CLAZZ);
            String jsonFromJSon = mapper.writeValueAsString(entityFromJson);

            CircularRequest entityFromBuilder = builder.withName(name).withDescription(description).build();
            String jsonFromBuilder = mapper.writeValueAsString(entityFromBuilder);

            assertEquals(jsonFromJSon, jsonFromBuilder);
            assertEquals(mapper.readValue(jsonFromJSon, CLAZZ), mapper.readValue(jsonFromBuilder, CLAZZ));
            assertEquals(mapper.readValue(jsonFromJSon, CLAZZ).toString(), mapper.readValue(jsonFromBuilder, CLAZZ).toString());
        }
    }

    @Nested
    class Override {
        @Test
        void testToString() {
            ToStringVerifier.forClass(CLAZZ)
                    .withClassName(NameStyle.SIMPLE_NAME)
                    .verify();
        }

        @Test
        void testEquals() {
            EqualsVerifier.forClass(CLAZZ)
                    .verify();
        }
    }
}