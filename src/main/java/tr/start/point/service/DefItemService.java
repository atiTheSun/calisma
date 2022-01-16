package tr.start.point.service;

import java.util.List;
import java.util.UUID;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tr.start.point.service.dto.DefItemDTO;

/**
 * Service Interface for managing {@link tr.start.point.domain.DefItem}.
 */
public interface DefItemService {
    /**
     * Save a defItem.
     *
     * @param defItemDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<DefItemDTO> save(DefItemDTO defItemDTO);

    /**
     * Partially updates a defItem.
     *
     * @param defItemDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<DefItemDTO> partialUpdate(DefItemDTO defItemDTO);

    /**
     * Get all the defItems.
     *
     * @return the list of entities.
     */
    Flux<DefItemDTO> findAll();

    /**
     * Returns the number of defItems available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" defItem.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<DefItemDTO> findOne(UUID id);

    /**
     * Delete the "id" defItem.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(UUID id);
}
