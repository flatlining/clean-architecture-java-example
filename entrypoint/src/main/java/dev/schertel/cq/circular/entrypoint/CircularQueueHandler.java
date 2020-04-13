package dev.schertel.cq.circular.entrypoint;

import dev.schertel.cq.circular.dto.CircularQueueDto;
import dev.schertel.cq.circular.entity.CircularQueue;
import dev.schertel.cq.circular.usecase.ICircularQueueUseCase;
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

    public CircularQueueDto create(CircularQueueDto dto) {
        CircularQueue entity = convertToEntity(dto);
        return convertToDto(useCase.create(entity));
    }

    public List<CircularQueueDto> readAll() {
        return useCase.readAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public CircularQueueDto read(UUID id) {
        return convertToDto(useCase.read(id));
    }

    public void update(UUID id, CircularQueueDto dto) {
        CircularQueue entity = convertToEntity(dto);
        entity.setId(id);
        useCase.update(entity);

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
