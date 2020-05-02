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

    @BeforeEach
    void setUp() {
        this.builder = Circular.builder();
    }

    @Test
    void getIdentity(@Random Identity identity) {
        // Given
        Circular.Builder toBuild = builder
                .withId(identity);

        // When
        Circular actual = toBuild.build();

        // Then
        assertThat(actual).isNotNull().satisfies(circular -> {
            assertThat(circular.getId()).isEqualTo(identity);
            assertThat(circular.getName()).isNull();
            assertThat(circular.getDescription()).isNull();
        });
    }

    @Test
    void getName(@Random String name) {
        // Given
        Circular.Builder toBuild = builder
                .withName(name);

        // When
        Circular actual = toBuild.build();

        // Then
        assertThat(actual).isNotNull().satisfies(circular -> {
            assertThat(circular.getId()).isNull();
            assertThat(circular.getName()).isEqualTo(name);
            assertThat(circular.getDescription()).isNull();
        });
    }

    @Test
    void getDescription(@Random String description) {
        // Given
        Circular.Builder toBuild = builder
                .withDescription(description);

        // When
        Circular actual = toBuild.build();

        // Then
        assertThat(actual).isNotNull().satisfies(circular -> {
            assertThat(circular.getId()).isNull();
            assertThat(circular.getName()).isNull();
            assertThat(circular.getDescription()).isEqualTo(description);
        });
    }

    @Nested
    class Builder {
        @Test
        void nullObject() {
            // Given
            Circular.Builder toBuild = builder;

            // When
            Circular actual = toBuild.build();

            // Then
            assertThat(actual).isNotNull().satisfies(circular -> {
                assertThat(circular.getId()).isNull();
                assertThat(circular.getName()).isNull();
                assertThat(circular.getDescription()).isNull();
            });
        }

        @Test
        void fullObject(@Random Identity identity, @Random String name, @Random String description) {
            // Given
            Circular.Builder toBuild = builder
                    .withId(identity)
                    .withName(name)
                    .withDescription(description);

            // When
            Circular actual = toBuild.build();

            // Then
            assertThat(actual).isNotNull().satisfies(circular -> {
                assertThat(circular.getId()).isEqualTo(identity);
                assertThat(circular.getName()).isEqualTo(name);
                assertThat(circular.getDescription()).isEqualTo(description);
            });
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
