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
class IdentityTest {
    private final Class<Identity> CLAZZ = Identity.class;

    private Identity.Builder cut;

    @BeforeEach
    void setUp() {
        this.cut = Identity.builder();
    }

    @Test
    void of(@Random String id) {
        // Given

        // When
        Identity actual = Identity.of(id);

        // Then
        assertThat(actual.getId()).isEqualTo(id);
    }

    @Test
    void getId(@Random String id) {
        // Given
        cut
                .withId(id);

        // When
        Identity actual = cut.build();

        // Then
        assertThat(actual.getId()).isEqualTo(id);
    }

    @Nested
    class Builder {
        @Test
        void nullObject() {
            // Given

            // When
            Identity actual = cut.build();

            // Then
            assertThat(actual.getId()).isNull();
        }

        @Test
        void fullObject(@Random String id) {
            // Given
            cut
                    .withId(id);

            // When
            Identity actual = cut.build();

            // Then
            assertThat(actual.getId()).isEqualTo(id);
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
