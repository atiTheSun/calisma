package tr.start.point.service;

import java.util.List;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tr.start.point.service.dto.DefKitapDTO;

/**
 * Service Interface for managing {@link tr.start.point.domain.DefKitap}.
 */
public interface DefKitapService {
    /**
     * Save a defKitap.
     *
     * @param defKitapDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<DefKitapDTO> save(DefKitapDTO defKitapDTO);

    /**
     * Partially updates a defKitap.
     *
     * @param defKitapDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<DefKitapDTO> partialUpdate(DefKitapDTO defKitapDTO);

    /**
     * Get all the defKitaps.
     *
     * @return the list of entities.
     */
    Flux<DefKitapDTO> findAll();

    /**
     * Returns the number of defKitaps available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" defKitap.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<DefKitapDTO> findOne(Long id);

    /**
     * Delete the "id" defKitap.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
