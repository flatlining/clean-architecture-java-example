package dev.schertel.cq.core.domain;

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
class CircularTest {
    private final Class<Circular> CLAZZ = Circular.class;

    private Circular.Builder builder;
    private Circular cut;

    @BeforeEach
    void setUp() {
        this.builder = Circular.builder();
        this.cut = null;
    }

    @Test
    void getIdentity(@Random Identity identity) {
        // Given

        // When
        Circular actual = builder
                .withId(identity)
                .build();

        // Then
        assertThat(actual.getId()).isEqualTo(identity);
        assertThat(actual.getName()).isNull();
        assertThat(actual.getDescription()).isNull();
    }

    @Test
    void getName(@Random String name) {
        // Given

        // When
        Circular actual = builder
                .withName(name)
                .build();

        // Then
        assertThat(actual.getId()).isNull();
        assertThat(actual.getName()).isEqualTo(name);
        assertThat(actual.getDescription()).isNull();
    }

    @Test
    void getDescription(@Random String description) {
        // Given

        // When
        Circular actual = builder
                .withDescription(description)
                .build();

        // Then
        assertThat(actual.getId()).isNull();
        assertThat(actual.getName()).isNull();
        assertThat(actual.getDescription()).isEqualTo(description);
    }

    @Nested
    class Builder {
        @Test
        void nullObject() {
            // Givem

            // When
            Circular actual = builder.build();

            // Then
            assertThat(actual.getId()).isNull();
            assertThat(actual.getName()).isNull();
            assertThat(actual.getDescription()).isNull();
        }

        @Test
        void fullObject(@Random Identity identity, @Random String name, @Random String description) {
            // Given

            // When
            Circular actual = builder
                    .withId(identity)
                    .withName(name)
                    .withDescription(description)
                    .build();

            // Then
            assertThat(actual.getId()).isEqualTo(identity);
            assertThat(actual.getName()).isEqualTo(name);
            assertThat(actual.getDescription()).isEqualTo(description);
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
