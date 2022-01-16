package tr.start.point.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tr.start.point.domain.DefItem;
import tr.start.point.repository.DefItemRepository;
import tr.start.point.service.DefItemService;
import tr.start.point.service.dto.DefItemDTO;
import tr.start.point.service.mapper.DefItemMapper;

/**
 * Service Implementation for managing {@link DefItem}.
 */
@Service
@Transactional
public class DefItemServiceImpl implements DefItemService {

    private final Logger log = LoggerFactory.getLogger(DefItemServiceImpl.class);

    private final DefItemRepository defItemRepository;

    private final DefItemMapper defItemMapper;

    public DefItemServiceImpl(DefItemRepository defItemRepository, DefItemMapper defItemMapper) {
        this.defItemRepository = defItemRepository;
        this.defItemMapper = defItemMapper;
    }

    @Override
    public Mono<DefItemDTO> save(DefItemDTO defItemDTO) {
        log.debug("Request to save DefItem : {}", defItemDTO);
        return defItemRepository.save(defItemMapper.toEntity(defItemDTO)).map(defItemMapper::toDto);
    }

    @Override
    public Mono<DefItemDTO> partialUpdate(DefItemDTO defItemDTO) {
        log.debug("Request to partially update DefItem : {}", defItemDTO);

        return defItemRepository
            .findById(defItemDTO.getId())
            .map(existingDefItem -> {
                defItemMapper.partialUpdate(existingDefItem, defItemDTO);

                return existingDefItem;
            })
            .flatMap(defItemRepository::save)
            .map(defItemMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<DefItemDTO> findAll() {
        log.debug("Request to get all DefItems");
        return defItemRepository.findAll().map(defItemMapper::toDto);
    }

    public Mono<Long> countAll() {
        return defItemRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<DefItemDTO> findOne(UUID id) {
        log.debug("Request to get DefItem : {}", id);
        return defItemRepository.findById(id).map(defItemMapper::toDto);
    }

    @Override
    public Mono<Void> delete(UUID id) {
        log.debug("Request to delete DefItem : {}", id);
        return defItemRepository.deleteById(id);
    }
}
