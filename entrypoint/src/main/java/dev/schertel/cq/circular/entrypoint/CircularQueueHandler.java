package dev.schertel.cq.circular.entrypoint;

import dev.schertel.cq.circular.dto.CircularQueueRequestDto;
import dev.schertel.cq.circular.dto.CircularQueueResponseDto;
import dev.schertel.cq.circular.entity.CircularQueue;
import dev.schertel.cq.circular.usecase.output.CreateCircleQueue;
import dev.schertel.cq.circular.usecase.output.DeleteCircleQueue;
import dev.schertel.cq.circular.usecase.output.ReadCircleQueue;
import dev.schertel.cq.circular.usecase.output.UpdateCircleQueue;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CircularQueueHandler {
    private final CreateCircleQueue createCircleQueue;
    private final ReadCircleQueue readCircleQueue;
    private final UpdateCircleQueue updateCircleQueue;
    private final DeleteCircleQueue deleteCircleQueue;
    private final CircularQueueMapper mapper;

    public CircularQueueHandler(CreateCircleQueue createCircleQueue, ReadCircleQueue readCircleQueue, UpdateCircleQueue updateCircleQueue, DeleteCircleQueue deleteCircleQueue, CircularQueueMapper mapper) {
        this.createCircleQueue = createCircleQueue;
        this.readCircleQueue = readCircleQueue;
        this.updateCircleQueue = updateCircleQueue;
        this.deleteCircleQueue = deleteCircleQueue;
        this.mapper = mapper;
    }

    public CircularQueueResponseDto create(CircularQueueRequestDto dto) {
        CircularQueue entity = createCircleQueue.create(mapper.convertRequestDtoToEntity(dto));
        return mapper.convertEntityToResponseDto(entity);
    }

    public List<CircularQueueResponseDto> readAll() {
        List<CircularQueue> entities = readCircleQueue.readAll();
        return entities.stream().map(mapper::convertEntityToResponseDto).collect(Collectors.toList());
    }

    public CircularQueueResponseDto read(String id) {
        CircularQueue entity = readCircleQueue.read(id);
        return mapper.convertEntityToResponseDto(entity);
    }

    public void update(String id, CircularQueueRequestDto dto) {
        updateCircleQueue.update(id, CircularQueue.builder().from(mapper.convertRequestDtoToEntity(dto)).withId(id).build());
    }

    public Optional<CircularQueueResponseDto> replaceOrCreate(String id, CircularQueueRequestDto dto) {
        Optional<CircularQueue> entity = updateCircleQueue.replaceOrCreate(id, CircularQueue.builder().from(mapper.convertRequestDtoToEntity(dto)).withId(id).build());
        return entity.map(mapper::convertEntityToResponseDto);
    }

    public void deleteAll() {
        deleteCircleQueue.deleteAll();
    }

    public void delete(String id) {
        deleteCircleQueue.delete(id);
    }
}
