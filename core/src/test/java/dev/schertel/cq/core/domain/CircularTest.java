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
class CircularTest {
    private Circular.Builder builder;
    private Circular cut;

    @BeforeEach
    void setUp() {
        this.builder = Circular.builder();
        this.cut = null;
    }

    @Test
    void getIdentity(@Random Identity identity) {
        cut = builder
                .withIdentity(identity)
                .build();

        assertAll(
                () -> assertEquals(identity, cut.getIdentity()),
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
                () -> assertNull(cut.getIdentity()),
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
                () -> assertNull(cut.getIdentity()),
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
                    () -> assertNull(cut.getIdentity()),
                    () -> assertNull(cut.getName()),
                    () -> assertNull(cut.getDescription())
            );
        }

        @Test
        void fullObject(@Random Identity identity, @Random String name, @Random String description) {
            cut = builder
                    .withIdentity(identity)
                    .withName(name)
                    .withDescription(description)
                    .build();

            assertAll(
                    () -> assertEquals(identity, cut.getIdentity()),
                    () -> assertEquals(name, cut.getName()),
                    () -> assertEquals(description, cut.getDescription())
            );
        }
    }

    @Nested
    class Override {
        private final Class<?> CLAZZ = Circular.class;

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
