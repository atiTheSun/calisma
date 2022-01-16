package tr.start.point.service.mapper;

import org.mapstruct.*;
import tr.start.point.domain.DefItem;
import tr.start.point.service.dto.DefItemDTO;

/**
 * Mapper for the entity {@link DefItem} and its DTO {@link DefItemDTO}.
 */
@Mapper(componentModel = "spring", uses = { DefTypeMapper.class })
public interface DefItemMapper extends EntityMapper<DefItemDTO, DefItem> {
    @Mapping(target = "type", source = "type", qualifiedByName = "id")
    @Mapping(target = "parent", source = "parent", qualifiedByName = "code")
    DefItemDTO toDto(DefItem s);

    @Named("code")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "code", source = "code")
    DefItemDTO toDtoCode(DefItem defItem);
}
