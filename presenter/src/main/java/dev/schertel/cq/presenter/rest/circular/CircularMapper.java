package dev.schertel.cq.presenter.rest.circular;

import dev.schertel.cq.core.domain.Circular;
import dev.schertel.cq.presenter.rest.dto.CircularResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.NameTransformers;
import org.modelmapper.convention.NamingConventions;
import org.springframework.stereotype.Component;

@Component
public class CircularMapper {
    private final ModelMapper modelMapper;

    public CircularMapper() {
        ModelMapper modelMapper = new ModelMapper();
        Configuration builderConfiguration = modelMapper.getConfiguration().copy()
                .setDestinationNameTransformer(NameTransformers.builder("with"))
                .setDestinationNamingConvention(NamingConventions.builder("with"));
        modelMapper.createTypeMap(Circular.class, CircularResponse.Builder.class, builderConfiguration);
        this.modelMapper = modelMapper;
    }

    public CircularResponse convertEntityToResponse(Circular entity) {
        return modelMapper.map(entity, CircularResponse.Builder.class).build();
    }
}
