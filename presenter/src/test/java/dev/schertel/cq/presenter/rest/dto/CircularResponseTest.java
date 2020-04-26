package dev.schertel.cq.presenter.rest.dto;

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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(RandomBeansExtension.class)
class CircularResponseTest {
    private final Class<CircularResponse> CLAZZ = CircularResponse.class;

    private CircularResponse.Builder builder;
    private CircularResponse cut;

    @BeforeEach
    void setUp() {
        this.builder = CircularResponse.builder();
        this.cut = null;
    }

    @Test
    void getId(@Random String id) {
        cut = builder
                .withId(id)
                .build();

        assertAll(
                () -> assertEquals(id, cut.getId()),
                () -> assertNull(cut.getName()),
                () -> assertNull(cut.getDescription())
        );
    }

    @Test
    void getName(@Random String name) {
        cut = builder
                .withName(name)
                .build();

        assertAll(
                () -> assertNull(cut.getId()),
                () -> assertEquals(name, cut.getName()),
                () -> assertNull(cut.getDescription())
        );
    }

    @Test
    void getDescription(@Random String description) {
        cut = builder
                .withDescription(description)
                .build();

        assertAll(
                () -> assertNull(cut.getId()),
                () -> assertNull(cut.getName()),
                () -> assertEquals(description, cut.getDescription())
        );
    }

    @Nested
    class Builder {
        @Test
        void nullObject() {
            cut = builder.build();

            assertAll(
                    () -> assertNull(cut.getId()),
                    () -> assertNull(cut.getName()),
                    () -> assertNull(cut.getDescription())
            );
        }

        @Test
        void fullObject(@Random String id, @Random String name, @Random String description) {
            cut = builder
                    .withId(id)
                    .withName(name)
                    .withDescription(description)
                    .build();

            assertAll(
                    () -> assertEquals(id, cut.getId()),
                    () -> assertEquals(name, cut.getName()),
                    () -> assertEquals(description, cut.getDescription())
            );
        }
    }

    @Nested
    class JSON {
        @Test
        void serializationDeserialization(@Random String id, @Random String name, @Random String description) throws JsonProcessingException {
            ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

            String json = String.format("{\n" +
                    "  \"id\":\"%s\",\n" +
                    "  \"name\":\"%s\",\n" +
                    "  \"description\":\"%s\"\n" +
                    "}", id, name, description);
            CircularResponse entityFromJson = mapper.readValue(json, CLAZZ);
            String jsonFromJSon = mapper.writeValueAsString(entityFromJson);

            CircularResponse entityFromBuilder = builder.withId(id).withName(name).withDescription(description).build();
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