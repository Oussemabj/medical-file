package tn.iit.medicalfile.mapper;


import org.springframework.util.CollectionUtils;
import tn.iit.medicalfile.dto.IdentifiableDto;
import tn.iit.medicalfile.model.Identifiable;


import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Mapper interface for map from/to entity and DTO.
 *
 * @param <ENTITY> the entity type.
 * @param <DTO>    the DTO type.
 */
public interface Mapper<ENTITY extends Identifiable<?>, DTO extends IdentifiableDto<?>>  {

    String SPRING = "spring";

    /**
     * Convert from ENTITY to DTO.
     *
     * @param entity the object to be converted.
     * @return the entity converted to DTO.
     */
    DTO mapToDto(ENTITY entity);

    /**
     * Convert from DTO to ENTITY.
     *
     * @param dto the object to be converted.
     * @return the dto converted to ENTITY.
     */
    ENTITY mapToEntity(DTO dto);

    /**
     * Convert from List<DTO> to List<ENTITY>.
     *
     * @param dtos the list of DTO's
     * @return the dtos converted to entities.
     */
    default List<ENTITY> mapToEntities(final Collection<DTO> dtos) {
        if (CollectionUtils.isEmpty(dtos)) {
            return Collections.emptyList();
        }
        return dtos.stream()
                .map(this::mapToEntity)
                .collect(toList());
    }

    /**
     * Convert from List<ENTITY> to List<DTO>.
     *
     * @param entities the list of entity.
     * @return the entities converted to dtos.
     */
    default List<DTO> mapToDtos(final Collection<ENTITY> entities) {
        if (CollectionUtils.isEmpty(entities)) {
            return Collections.emptyList();
        }
        return entities.stream()
                .map(this::mapToDto)
                .collect(toList());
    }

}
