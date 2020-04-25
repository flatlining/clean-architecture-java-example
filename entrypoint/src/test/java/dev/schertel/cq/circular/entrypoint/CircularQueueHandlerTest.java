package dev.schertel.cq.circular.entrypoint;

import dev.schertel.cq.circular.dto.CircularQueueRequestDto;
import dev.schertel.cq.circular.dto.CircularQueueResponseDto;
import dev.schertel.cq.circular.entity.CircularQueue;
import dev.schertel.cq.circular.usecase.output.CreateCircleQueue;
import io.github.glytching.junit.extension.random.Random;
import io.github.glytching.junit.extension.random.RandomBeansExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class, RandomBeansExtension.class})
class CircularQueueHandlerTest {
    private CircularQueueMapper mapper;
    private CircularQueueHandler cut;

    @BeforeEach
    void setUp() {
        mapper = new CircularQueueMapper(new ModelMapper());
        cut = null;
    }

    @Test
    void createNewEntity(@Random String id, @Random String name, @Random String description, @Mock CreateCircleQueue createCircleQueue) {
        when(createCircleQueue.create(any(CircularQueue.class))).thenReturn(new CircularQueue(id, name, description));

        CircularQueueRequestDto request = CircularQueueRequestDto.builder().withName(name).withDescription(description).build();
        cut = new CircularQueueHandler(createCircleQueue, null, null, null, mapper);
        CircularQueueResponseDto response = cut.create(request);

        CircularQueueResponseDto expected = CircularQueueResponseDto.builder().withId(id).withName(name).withDescription(description).build();
        assertEquals(expected, response);
    }
}