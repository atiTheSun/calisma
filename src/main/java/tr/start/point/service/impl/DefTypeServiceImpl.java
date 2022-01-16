package tr.start.point.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tr.start.point.domain.DefType;
import tr.start.point.repository.DefTypeRepository;
import tr.start.point.service.DefTypeService;
import tr.start.point.service.dto.DefTypeDTO;
import tr.start.point.service.mapper.DefTypeMapper;

/**
 * Service Implementation for managing {@link DefType}.
 */
@Service
@Transactional
public class DefTypeServiceImpl implements DefTypeService {

    private final Logger log = LoggerFactory.getLogger(DefTypeServiceImpl.class);

    private final DefTypeRepository defTypeRepository;

    private final DefTypeMapper defTypeMapper;

    public DefTypeServiceImpl(DefTypeRepository defTypeRepository, DefTypeMapper defTypeMapper) {
        this.defTypeRepository = defTypeRepository;
        this.defTypeMapper = defTypeMapper;
    }

    @Override
    public Mono<DefTypeDTO> save(DefTypeDTO defTypeDTO) {
        log.debug("Request to save DefType : {}", defTypeDTO);
        return defTypeRepository.save(defTypeMapper.toEntity(defTypeDTO)).map(defTypeMapper::toDto);
    }

    @Override
    public Mono<DefTypeDTO> partialUpdate(DefTypeDTO defTypeDTO) {
        log.debug("Request to partially update DefType : {}", defTypeDTO);

        return defTypeRepository
            .findById(defTypeDTO.getId())
            .map(existingDefType -> {
                defTypeMapper.partialUpdate(existingDefType, defTypeDTO);

                return existingDefType;
            })
            .flatMap(defTypeRepository::save)
            .map(defTypeMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<DefTypeDTO> findAll() {
        log.debug("Request to get all DefTypes");
        return defTypeRepository.findAll().map(defTypeMapper::toDto);
    }

    public Mono<Long> countAll() {
        return defTypeRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<DefTypeDTO> findOne(String id) {
        log.debug("Request to get DefType : {}", id);
        return defTypeRepository.findById(id).map(defTypeMapper::toDto);
    }

    @Override
    public Mono<Void> delete(String id) {
        log.debug("Request to delete DefType : {}", id);
        return defTypeRepository.deleteById(id);
    }
}
