package dev.schertel.cq.circular.entrypoint;

import dev.schertel.cq.circular.dto.CircularQueueRequestDto;
import dev.schertel.cq.circular.dto.CircularQueueResponseDto;
import dev.schertel.cq.circular.entity.CircularQueue;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CircularQueueMapper {
    private ModelMapper modelMapper;

    public CircularQueueMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CircularQueue convertRequestDtoToEntity(CircularQueueRequestDto dto) {
        return modelMapper.map(dto, CircularQueue.class);
    }

    public CircularQueueResponseDto convertEntityToResponseDto(CircularQueue entity) {
        return modelMapper.map(entity, CircularQueueResponseDto.class);
    }
}
