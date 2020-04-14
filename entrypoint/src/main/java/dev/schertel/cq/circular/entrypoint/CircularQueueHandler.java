package dev.schertel.cq.circular.entrypoint;

import dev.schertel.cq.circular.dto.CircularQueueDto;
import dev.schertel.cq.circular.entity.CircularQueue;
import dev.schertel.cq.circular.usecase.output.ICircularQueueUseCase;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    public Optional<CircularQueueDto> read(String id) {
        return convertToDto(useCase.read(id));
    }

    private CircularQueueDto convertToDto(CircularQueue entity) {
        return modelMapper.map(entity, CircularQueueDto.class);
    }

    private Optional<CircularQueueDto> convertToDto(Optional<CircularQueue> entity) {
        return entity.map(circularQueue -> modelMapper.map(circularQueue, CircularQueueDto.class));
    }

    private CircularQueue convertToEntity(CircularQueueDto dto) {
        return modelMapper.map(dto, CircularQueue.class);
    }
}
