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

@ExtendWith(RandomBeansExtension.class)
class CircularRequestTest {
    private final Class<CircularRequest> CLAZZ = CircularRequest.class;

    private CircularRequest.Builder cut;

    @BeforeEach
    void setUp() {
        this.cut = CircularRequest.builder();
    }

    @Test
    void getName(@Random String name) {
        // Given
        cut
                .withName(name);

        // When
        CircularRequest actual = cut.build();

        // Then
        assertThat(actual).isNotNull().satisfies(circularRequest -> {
            assertThat(circularRequest.getName()).isEqualTo(name);
            assertThat(circularRequest.getDescription()).isNull();
        });
    }

    @Test
    void getDescription(@Random String description) {
        // Given
        cut
                .withDescription(description);

        // When
        CircularRequest actual = cut.build();

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
            CircularRequest actual = cut.build();

            // Then
            assertThat(actual).isNotNull().satisfies(circularRequest -> {
                assertThat(circularRequest.getName()).isNull();
                assertThat(circularRequest.getDescription()).isNull();
            });
        }

        @Test
        void fullObject(@Random String name, @Random String description) {
            // Given
            cut
                    .withName(name)
                    .withDescription(description);

            // When
            CircularRequest actual = cut.build();

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
            // Background
            ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

            // Given
            String json = String.format("{\n" +
                    "  \"name\":\"%s\",\n" +
                    "  \"description\":\"%s\"\n" +
                    "}", name, description);

            // When
            CircularRequest entityFromBuilder = cut.withName(name).withDescription(description).build();
            String actualJson = mapper.writeValueAsString(entityFromBuilder);

            // Then
            CircularRequest entityFromJson = mapper.readValue(json, CLAZZ);
            String expectedJson = mapper.writeValueAsString(entityFromJson);

            assertThat(actualJson).isEqualTo(expectedJson);
            assertThat(mapper.readValue(actualJson, CLAZZ))
                    .isEqualTo(mapper.readValue(expectedJson, CLAZZ));
            assertThat(mapper.readValue(actualJson, CLAZZ).toString())
                    .isEqualTo(mapper.readValue(expectedJson, CLAZZ).toString());
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