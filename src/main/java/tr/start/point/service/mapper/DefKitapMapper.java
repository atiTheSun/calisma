package tr.start.point.service.mapper;

import org.mapstruct.*;
import tr.start.point.domain.DefKitap;
import tr.start.point.service.dto.DefKitapDTO;

/**
 * Mapper for the entity {@link DefKitap} and its DTO {@link DefKitapDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DefKitapMapper extends EntityMapper<DefKitapDTO, DefKitap> {}
