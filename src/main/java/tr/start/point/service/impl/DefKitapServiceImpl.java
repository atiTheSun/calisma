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
import tr.start.point.domain.DefKitap;
import tr.start.point.repository.DefKitapRepository;
import tr.start.point.service.DefKitapService;
import tr.start.point.service.dto.DefKitapDTO;
import tr.start.point.service.mapper.DefKitapMapper;

/**
 * Service Implementation for managing {@link DefKitap}.
 */
@Service
@Transactional
public class DefKitapServiceImpl implements DefKitapService {

    private final Logger log = LoggerFactory.getLogger(DefKitapServiceImpl.class);

    private final DefKitapRepository defKitapRepository;

    private final DefKitapMapper defKitapMapper;

    public DefKitapServiceImpl(DefKitapRepository defKitapRepository, DefKitapMapper defKitapMapper) {
        this.defKitapRepository = defKitapRepository;
        this.defKitapMapper = defKitapMapper;
    }

    @Override
    public Mono<DefKitapDTO> save(DefKitapDTO defKitapDTO) {
        log.debug("Request to save DefKitap : {}", defKitapDTO);
        return defKitapRepository.save(defKitapMapper.toEntity(defKitapDTO)).map(defKitapMapper::toDto);
    }

    @Override
    public Mono<DefKitapDTO> partialUpdate(DefKitapDTO defKitapDTO) {
        log.debug("Request to partially update DefKitap : {}", defKitapDTO);

        return defKitapRepository
            .findById(defKitapDTO.getId())
            .map(existingDefKitap -> {
                defKitapMapper.partialUpdate(existingDefKitap, defKitapDTO);

                return existingDefKitap;
            })
            .flatMap(defKitapRepository::save)
            .map(defKitapMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<DefKitapDTO> findAll() {
        log.debug("Request to get all DefKitaps");
        return defKitapRepository.findAll().map(defKitapMapper::toDto);
    }

    public Mono<Long> countAll() {
        return defKitapRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<DefKitapDTO> findOne(Long id) {
        log.debug("Request to get DefKitap : {}", id);
        return defKitapRepository.findById(id).map(defKitapMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete DefKitap : {}", id);
        return defKitapRepository.deleteById(id);
    }
}
