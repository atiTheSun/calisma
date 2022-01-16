package tr.start.point.service;

import java.util.List;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tr.start.point.service.dto.DefTypeDTO;

/**
 * Service Interface for managing {@link tr.start.point.domain.DefType}.
 */
public interface DefTypeService {
    /**
     * Save a defType.
     *
     * @param defTypeDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<DefTypeDTO> save(DefTypeDTO defTypeDTO);

    /**
     * Partially updates a defType.
     *
     * @param defTypeDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<DefTypeDTO> partialUpdate(DefTypeDTO defTypeDTO);

    /**
     * Get all the defTypes.
     *
     * @return the list of entities.
     */
    Flux<DefTypeDTO> findAll();

    /**
     * Returns the number of defTypes available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" defType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<DefTypeDTO> findOne(String id);

    /**
     * Delete the "id" defType.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(String id);
}
