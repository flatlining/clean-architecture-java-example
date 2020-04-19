package dev.schertel.cq.circular.entrypoint;

import dev.schertel.cq.circular.dto.CircularQueueRequestDto;
import dev.schertel.cq.circular.dto.CircularQueueResponseDto;
import dev.schertel.cq.circular.entity.CircularQueue;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.NameTransformers;
import org.modelmapper.convention.NamingConventions;
import org.springframework.stereotype.Service;

@Service
public class CircularQueueMapper {
    private ModelMapper modelMapper;

    public CircularQueueMapper(ModelMapper modelMapper) {
        Configuration builderConfiguration = modelMapper.getConfiguration().copy()
                .setDestinationNameTransformer(NameTransformers.builder("with"))
                .setDestinationNamingConvention(NamingConventions.builder("with"));
        modelMapper.createTypeMap(CircularQueue.class, CircularQueueResponseDto.Builder.class, builderConfiguration);
        this.modelMapper = modelMapper;
    }

    public CircularQueue convertRequestDtoToEntity(CircularQueueRequestDto dto) {
        return modelMapper.map(dto, CircularQueue.class);
    }

    public CircularQueueResponseDto convertEntityToResponseDto(CircularQueue entity) {
        return modelMapper.map(entity, CircularQueueResponseDto.Builder.class).build();
    }
}
