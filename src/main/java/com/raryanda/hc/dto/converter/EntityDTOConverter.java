package com.raryanda.hc.dto.converter;

import java.util.List;
import java.util.stream.Collectors;

public interface EntityDTOConverter<Entity, DTO> {
    DTO convertToDTO(Entity entity);

    default List<DTO> convertToListDTO(List<Entity> entities) {
        return entities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}
