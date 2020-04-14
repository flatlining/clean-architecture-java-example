package dev.schertel.cq.circular.entrypoint;

import dev.schertel.cq.circular.dto.CircularQueueRequestDto;
import dev.schertel.cq.circular.dto.CircularQueueResponseDto;
import dev.schertel.cq.circular.entity.CircularQueue;
import dev.schertel.cq.circular.usecase.output.CreateCircleQueue;
import dev.schertel.cq.circular.usecase.output.ReadCircleQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CircularQueueHandler {
    @Autowired
    CreateCircleQueue createCircleQueue;
    @Autowired
    ReadCircleQueue readCircleQueue;

    @Autowired
    CircularQueueMapper mapper;

    public CircularQueueResponseDto create(CircularQueueRequestDto dto) {
        CircularQueue entity = createCircleQueue.create(mapper.convertRequestDtoToEntity(dto));
        return mapper.convertEntityToResponseDto(entity);
    }

    public List<CircularQueueResponseDto> readAll() {
        List<CircularQueue> entities = readCircleQueue.readAll();
        return entities.stream().map(mapper::convertEntityToResponseDto).collect(Collectors.toList());
    }

    public Optional<CircularQueueResponseDto> read(String id) {
        Optional<CircularQueue> entity = readCircleQueue.read(id);
        return entity.map(mapper::convertEntityToResponseDto);
    }
}
