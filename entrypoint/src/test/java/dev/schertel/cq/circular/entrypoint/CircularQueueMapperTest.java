package dev.schertel.cq.circular.entrypoint;

import dev.schertel.cq.circular.dto.CircularQueueRequestDto;
import dev.schertel.cq.circular.dto.CircularQueueResponseDto;
import dev.schertel.cq.circular.entity.CircularQueue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;

class CircularQueueMapperTest {
    private CircularQueueMapper cut;

    @BeforeEach
    void setUp() {
        cut = new CircularQueueMapper(new ModelMapper());
    }

    @Test
    void convertRequestDtoToEntity() {
        CircularQueueRequestDto dto = CircularQueueRequestDto.builder()
                .withName("name")
                .withDescription("description")
                .build();
        CircularQueue entity = cut.convertRequestDtoToEntity(dto);

        assertAll(
                () -> assertNull(entity.getId()),
                () -> assertEquals(dto.getName(), entity.getName()),
                () -> assertEquals(dto.getDescription(), entity.getDescription())
        );
    }

    @Test
    void convertEntityToResponseDto() {
        CircularQueue entity = new CircularQueue("id", "name", "description");
        CircularQueueResponseDto dto = cut.convertEntityToResponseDto(entity);

        assertAll(
                () -> assertEquals(entity.getId(), dto.getId()),
                () -> assertEquals(entity.getName(), dto.getName()),
                () -> assertEquals(entity.getDescription(), dto.getDescription())
        );
    }
}