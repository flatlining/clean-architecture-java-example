package dev.schertel.cq.data.database.circular.entity;

import com.jparams.verifier.tostring.NameStyle;
import com.jparams.verifier.tostring.ToStringVerifier;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class CircularEntityTest {
    private final Class<CircularEntity> CLAZZ = CircularEntity.class;

    @BeforeEach
    void setUp() {
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
        @Disabled("Persistence id and domain identity should be different things, and persistence id should only exist on data, domain identity is different from database id")
        void testEquals() {
            EqualsVerifier.forClass(CLAZZ)
                    .verify();
        }
    }
}
