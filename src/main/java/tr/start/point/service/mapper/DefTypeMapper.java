package tr.start.point.service.mapper;

import org.mapstruct.*;
import tr.start.point.domain.DefType;
import tr.start.point.service.dto.DefTypeDTO;

/**
 * Mapper for the entity {@link DefType} and its DTO {@link DefTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DefTypeMapper extends EntityMapper<DefTypeDTO, DefType> {
    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DefTypeDTO toDtoId(DefType defType);
}
