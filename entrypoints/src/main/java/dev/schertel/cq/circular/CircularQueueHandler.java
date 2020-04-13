package dev.schertel.cq.circular;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CircularQueueHandler {
    @Autowired
    ICircularQueueUseCase useCase;
    @Autowired
    ModelMapper modelMapper;

    public List<CircularQueueDto> getAll() {
        return useCase.getAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public CircularQueueDto create(CircularQueueDto dto) {
        CircularQueue entity = convertToEntity(dto);
        return convertToDto(useCase.create(entity));
    }

    public CircularQueueDto getById(UUID id) {
        return convertToDto(useCase.getById(id));
    }

    private CircularQueueDto convertToDto(CircularQueue entity) {
        CircularQueueDto dto = modelMapper.map(entity, CircularQueueDto.class);
        return dto;
    }

    private CircularQueue convertToEntity(CircularQueueDto dto) {
        CircularQueue entity = modelMapper.map(dto, CircularQueue.class);
        return entity;
    }
}
