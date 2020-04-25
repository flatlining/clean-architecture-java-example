package dev.schertel.cq.circular.entrypoint;

import dev.schertel.cq.circular.dto.CircularQueueRequestDto;
import dev.schertel.cq.circular.dto.CircularQueueResponseDto;
import dev.schertel.cq.circular.entity.CircularQueue;
import dev.schertel.cq.circular.exception.CircularQueueNotFoundException;
import dev.schertel.cq.circular.usecase.output.CreateCircleQueue;
import dev.schertel.cq.circular.usecase.output.ReadCircleQueue;
import dev.schertel.cq.circular.usecase.output.UpdateCircleQueue;
import io.github.glytching.junit.extension.random.Random;
import io.github.glytching.junit.extension.random.RandomBeansExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class, RandomBeansExtension.class})
class CircularQueueHandlerTest {
    private CircularQueueMapper mapper;
    private CircularQueueHandler cut;

    @BeforeEach
    void setUp() {
        mapper = new CircularQueueMapper(new ModelMapper());
        cut = null;
    }

    @Nested
    class Create {
        @Mock
        CreateCircleQueue mock;

        @BeforeEach
        void setUp() {
            reset(mock);
        }

        @Test
        void createNewEntity(@Random String id, @Random String name, @Random String description) {
            when(mock.create(CircularQueue.builder().withName(name).withDescription(description).build()))
                    .thenReturn(CircularQueue.builder().withId(id).withName(name).withDescription(description).build());

            CircularQueueRequestDto request = CircularQueueRequestDto.builder().withName(name).withDescription(description).build();
            cut = new CircularQueueHandler(mock, null, null, null, mapper);
            CircularQueueResponseDto response = cut.create(request);

            CircularQueueResponseDto expected = CircularQueueResponseDto.builder().withId(id).withName(name).withDescription(description).build();
            assertEquals(expected, response);
        }
    }

    @Nested
    class Read {
        @Mock
        ReadCircleQueue mock;

        @BeforeEach
        void setUp() {
            reset(mock);
        }

        @Test
        void readExistingEntity(@Random String id, @Random String name, @Random String description) {
            when(mock.read(id))
                    .thenReturn(CircularQueue.builder().withId(id).withName(name).withDescription(description).build());

            cut = new CircularQueueHandler(null, mock, null, null, mapper);
            CircularQueueResponseDto response = cut.read(id);

            CircularQueueResponseDto expected = CircularQueueResponseDto.builder().withId(id).withName(name).withDescription(description).build();
            assertEquals(expected, response);
        }

        @Test
        void readNonExistingEntity(@Random String id, @Random String name, @Random String description) {
            when(mock.read(id)).thenThrow(new CircularQueueNotFoundException(id));

            cut = new CircularQueueHandler(null, mock, null, null, mapper);

            CircularQueueNotFoundException exception = assertThrows(CircularQueueNotFoundException.class, () -> {
                cut.read(id);
            });

            assertEquals(id, exception.getId());
        }

        @Test
        void readAllEntities(@Random(size = 5, type = CircularQueue.class) List<CircularQueue> entities) {
            when(mock.readAll()).thenReturn(entities);

            cut = new CircularQueueHandler(null, mock, null, null, mapper);

            List<CircularQueueResponseDto> actual = cut.readAll();

            assertNotNull(actual);
            assertEquals(5, actual.size());
        }

        @Test
        void readAllNonEntities() {
            when(mock.readAll()).thenReturn(Collections.<CircularQueue>emptyList());

            cut = new CircularQueueHandler(null, mock, null, null, mapper);

            List<CircularQueueResponseDto> actual = cut.readAll();

            assertNotNull(actual);
            assertEquals(0, actual.size());
        }
    }

    @Nested
    class Update {
        @Mock
        UpdateCircleQueue mock;

        @BeforeEach
        void setUp() {
            reset(mock);
        }

        @Test
        void updateExistingEntity(@Random String id, @Random String name, @Random String description) {
            cut = new CircularQueueHandler(null, null, mock, null, mapper);

            cut.update(id, CircularQueueRequestDto.builder().withName(name).withDescription(description).build());

            verify(mock).update(id, CircularQueue.builder().withId(id).withName(name).withDescription(description).build());
        }
    }

    @Nested
    class Delete {

    }
}