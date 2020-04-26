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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(RandomBeansExtension.class)
class IdentityTest {
    private Identity.Builder builder;
    private Identity cut;

    @BeforeEach
    void setUp() {
        this.builder = Identity.builder();
        this.cut = null;
    }

    @Test
    void of(@Random String id) {
        cut = Identity.of(id);

        assertAll(
                () -> assertEquals(id, cut.getId())
        );
    }

    @Test
    void getId(@Random String id) {
        cut = builder
                .withId(id)
                .build();

        assertAll(
                () -> assertEquals(id, cut.getId())
        );
    }

    @Nested
    class Builder {
        private final Class<?> CLAZZ = Identity.Builder.class;

        @Test
        void nullObject() {
            cut = builder.build();

            assertAll(
                    () -> assertNull(cut.getId())
            );
        }

        @Test
        void fullObject(@Random String id) {
            cut = builder
                    .withId(id)
                    .build();

            assertAll(
                    () -> assertEquals(id, cut.getId())
            );
        }

        @Test
        void testToString() {
            ToStringVerifier.forClass(CLAZZ)
                    .verify();
        }
    }

    @Nested
    class Override {
        private final Class<?> CLAZZ = Identity.class;

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
