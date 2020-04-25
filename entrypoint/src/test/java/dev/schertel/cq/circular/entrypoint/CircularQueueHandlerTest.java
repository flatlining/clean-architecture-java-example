package dev.schertel.cq.circular.entrypoint;

import dev.schertel.cq.circular.dto.CircularQueueRequestDto;
import dev.schertel.cq.circular.dto.CircularQueueResponseDto;
import dev.schertel.cq.circular.entity.CircularQueue;
import dev.schertel.cq.circular.exception.CircularQueueNotFoundException;
import dev.schertel.cq.circular.usecase.output.CreateCircleQueue;
import dev.schertel.cq.circular.usecase.output.DeleteCircleQueue;
import dev.schertel.cq.circular.usecase.output.ReadCircleQueue;
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
        void createNewEntityNameAndDescription(@Random String id, @Random String name, @Random String description) {
            when(mock.create(CircularQueue.builder().withName(name).withDescription(description).build()))
                    .thenReturn(CircularQueue.builder().withId(id).withName(name).withDescription(description).build());

            CircularQueueRequestDto request = CircularQueueRequestDto.builder().withName(name).withDescription(description).build();
            cut = new CircularQueueHandler(mock, null, null, mapper);
            CircularQueueResponseDto response = cut.create(request);

            CircularQueueResponseDto expected = CircularQueueResponseDto.builder().withId(id).withName(name).withDescription(description).build();
            assertEquals(expected, response);
        }

        @Test
        void createNewEntityName(@Random String id, @Random String name) {
            when(mock.create(CircularQueue.builder().withName(name).build()))
                    .thenReturn(CircularQueue.builder().withId(id).withName(name).build());

            CircularQueueRequestDto request = CircularQueueRequestDto.builder().withName(name).build();
            cut = new CircularQueueHandler(mock, null, null, mapper);
            CircularQueueResponseDto response = cut.create(request);

            CircularQueueResponseDto expected = CircularQueueResponseDto.builder().withId(id).withName(name).build();
            assertEquals(expected, response);
        }

        @Test
        void createNewEntityDescription(@Random String id, @Random String description) {
            when(mock.create(CircularQueue.builder().withDescription(description).build()))
                    .thenReturn(CircularQueue.builder().withId(id).withDescription(description).build());

            CircularQueueRequestDto request = CircularQueueRequestDto.builder().withDescription(description).build();
            cut = new CircularQueueHandler(mock, null, null, mapper);
            CircularQueueResponseDto response = cut.create(request);

            CircularQueueResponseDto expected = CircularQueueResponseDto.builder().withId(id).withDescription(description).build();
            assertEquals(expected, response);
        }

        @Test
        void createNewEntityWithNothing(@Random String id) {
            when(mock.create(CircularQueue.builder().build()))
                    .thenReturn(CircularQueue.builder().withId(id).build());

            CircularQueueRequestDto request = CircularQueueRequestDto.builder().build();
            cut = new CircularQueueHandler(mock, null, null, mapper);
            CircularQueueResponseDto response = cut.create(request);

            CircularQueueResponseDto expected = CircularQueueResponseDto.builder().withId(id).build();
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

            cut = new CircularQueueHandler(null, mock, null, mapper);
            CircularQueueResponseDto response = cut.read(id);

            CircularQueueResponseDto expected = CircularQueueResponseDto.builder().withId(id).withName(name).withDescription(description).build();
            assertEquals(expected, response);
        }

        @Test
        void readNonExistingEntity(@Random String id, @Random String name, @Random String description) {
            when(mock.read(id)).thenThrow(new CircularQueueNotFoundException(id));

            cut = new CircularQueueHandler(null, mock, null, mapper);

            CircularQueueNotFoundException exception = assertThrows(CircularQueueNotFoundException.class, () -> {
                cut.read(id);
            });

            assertEquals(id, exception.getId());
        }

        @Test
        void readAllEntities(@Random(size = 5, type = CircularQueue.class) List<CircularQueue> entities) {
            when(mock.readAll()).thenReturn(entities);

            cut = new CircularQueueHandler(null, mock, null, mapper);

            List<CircularQueueResponseDto> actual = cut.readAll();

            assertNotNull(actual);
            assertEquals(5, actual.size());
        }

        @Test
        void readAllNonEntities() {
            when(mock.readAll()).thenReturn(Collections.<CircularQueue>emptyList());

            cut = new CircularQueueHandler(null, mock, null, mapper);

            List<CircularQueueResponseDto> actual = cut.readAll();

            assertNotNull(actual);
            assertEquals(0, actual.size());
        }
    }

    @Nested
    class Delete {
        @Mock
        DeleteCircleQueue mock;

        @BeforeEach
        void setUp() {
            reset(mock);
        }

        @Test
        void deleteExistingEntity(@Random String id) {
            cut = new CircularQueueHandler(null, null, mock, mapper);

            cut.delete(id);

            verify(mock, times(1)).delete(id);
        }

        @Test
        void deleteNonExistingEntity(@Random String id) {
            doThrow(new CircularQueueNotFoundException(id))
                    .when(mock)
                    .delete(id);

            cut = new CircularQueueHandler(null, null, mock, mapper);

            CircularQueueNotFoundException exception = assertThrows(CircularQueueNotFoundException.class, () -> {
                cut.delete(id);
            });

            assertEquals(id, exception.getId());
        }

        @Test
        void deleteAll() {
            cut = new CircularQueueHandler(null, null, mock, mapper);

            cut.deleteAll();

            verify(mock, times(1)).deleteAll();
        }
    }
}