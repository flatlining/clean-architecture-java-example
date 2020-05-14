package dev.schertel.cq.data.database.circular.entity;

import com.jparams.verifier.tostring.NameStyle;
import com.jparams.verifier.tostring.ToStringVerifier;
import io.github.glytching.junit.extension.random.Random;
import io.github.glytching.junit.extension.random.RandomBeansExtension;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(RandomBeansExtension.class)
class CircularEntityTest {
    private final Class<CircularEntity> CLAZZ = CircularEntity.class;

    private CircularEntity.Builder cut;

    @BeforeEach
    void setUp() {
        this.cut = CircularEntity.builder();
    }

    @Test
    void getId(@Random String id) {
        // Given
        CircularEntity actual = new CircularEntity();

        // When
        actual.setId(id);

        // Then
        assertThat(actual).isNotNull().satisfies(circularEntity -> {
            assertThat(circularEntity.getId()).isEqualTo(id);
            assertThat(circularEntity.getName()).isNull();
            assertThat(circularEntity.getDescription()).isNull();
        });
    }

    @Test
    void getName(@Random String name) {
        // Given
        CircularEntity actual = new CircularEntity();

        // When
        actual.setName(name);

        // Then
        assertThat(actual).isNotNull().satisfies(circularEntity -> {
            assertThat(circularEntity.getId()).isNull();
            assertThat(circularEntity.getName()).isEqualTo(name);
            assertThat(circularEntity.getDescription()).isNull();
        });
    }

    @Test
    void getDescription(@Random String description) {
        // Given
        CircularEntity actual = new CircularEntity();

        // When
        actual.setDescription(description);

        // Then
        assertThat(actual).isNotNull().satisfies(circularEntity -> {
            assertThat(circularEntity.getId()).isNull();
            assertThat(circularEntity.getName()).isNull();
            assertThat(circularEntity.getDescription()).isEqualTo(description);
        });
    }

    @Nested
    class Builder {
        @Test
        void nullObject() {
            // Given

            // When
            CircularEntity actual = cut.build();

            // Then
            assertThat(actual).isNotNull().satisfies(circularEntity -> {
                assertThat(circularEntity.getId()).isNull();
                assertThat(circularEntity.getName()).isNull();
                assertThat(circularEntity.getDescription()).isNull();
            });
        }

        @Test
        void fullObject(@Random String id, @Random String name, @Random String description) {
            // Given
            cut
                    .withId(id)
                    .withName(name)
                    .withDescription(description);

            // When
            CircularEntity actual = cut.build();

            // Then
            assertThat(actual).isNotNull().satisfies(circularEntity -> {
                assertThat(circularEntity.getId()).isEqualTo(id);
                assertThat(circularEntity.getName()).isEqualTo(name);
                assertThat(circularEntity.getDescription()).isEqualTo(description);
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
                    .suppress(Warning.SURROGATE_KEY)
                    .verify();
        }
    }
}
