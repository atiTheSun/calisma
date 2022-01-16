package tr.start.point.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.reactive.ResponseUtil;
import tr.start.point.repository.DefItemRepository;
import tr.start.point.service.DefItemService;
import tr.start.point.service.dto.DefItemDTO;
import tr.start.point.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link tr.start.point.domain.DefItem}.
 */
@RestController
@RequestMapping("/api")
public class DefItemResource {

    private final Logger log = LoggerFactory.getLogger(DefItemResource.class);

    private static final String ENTITY_NAME = "defItem";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DefItemService defItemService;

    private final DefItemRepository defItemRepository;

    public DefItemResource(DefItemService defItemService, DefItemRepository defItemRepository) {
        this.defItemService = defItemService;
        this.defItemRepository = defItemRepository;
    }

    /**
     * {@code POST  /def-items} : Create a new defItem.
     *
     * @param defItemDTO the defItemDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new defItemDTO, or with status {@code 400 (Bad Request)} if the defItem has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/def-items")
    public Mono<ResponseEntity<DefItemDTO>> createDefItem(@Valid @RequestBody DefItemDTO defItemDTO) throws URISyntaxException {
        log.debug("REST request to save DefItem : {}", defItemDTO);
        if (defItemDTO.getId() != null) {
            throw new BadRequestAlertException("A new defItem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return defItemService
            .save(defItemDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/def-items/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /def-items/:id} : Updates an existing defItem.
     *
     * @param id the id of the defItemDTO to save.
     * @param defItemDTO the defItemDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated defItemDTO,
     * or with status {@code 400 (Bad Request)} if the defItemDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the defItemDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/def-items/{id}")
    public Mono<ResponseEntity<DefItemDTO>> updateDefItem(
        @PathVariable(value = "id", required = false) final UUID id,
        @Valid @RequestBody DefItemDTO defItemDTO
    ) throws URISyntaxException {
        log.debug("REST request to update DefItem : {}, {}", id, defItemDTO);
        if (defItemDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, defItemDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return defItemRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return defItemService
                    .save(defItemDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /def-items/:id} : Partial updates given fields of an existing defItem, field will ignore if it is null
     *
     * @param id the id of the defItemDTO to save.
     * @param defItemDTO the defItemDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated defItemDTO,
     * or with status {@code 400 (Bad Request)} if the defItemDTO is not valid,
     * or with status {@code 404 (Not Found)} if the defItemDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the defItemDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/def-items/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<DefItemDTO>> partialUpdateDefItem(
        @PathVariable(value = "id", required = false) final UUID id,
        @NotNull @RequestBody DefItemDTO defItemDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update DefItem partially : {}, {}", id, defItemDTO);
        if (defItemDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, defItemDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return defItemRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<DefItemDTO> result = defItemService.partialUpdate(defItemDTO);

                return result
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(res ->
                        ResponseEntity
                            .ok()
                            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, res.getId().toString()))
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /def-items} : get all the defItems.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of defItems in body.
     */
    @GetMapping("/def-items")
    public Mono<List<DefItemDTO>> getAllDefItems() {
        log.debug("REST request to get all DefItems");
        return defItemService.findAll().collectList();
    }

    /**
     * {@code GET  /def-items} : get all the defItems as a stream.
     * @return the {@link Flux} of defItems.
     */
    @GetMapping(value = "/def-items", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<DefItemDTO> getAllDefItemsAsStream() {
        log.debug("REST request to get all DefItems as a stream");
        return defItemService.findAll();
    }

    /**
     * {@code GET  /def-items/:id} : get the "id" defItem.
     *
     * @param id the id of the defItemDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the defItemDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/def-items/{id}")
    public Mono<ResponseEntity<DefItemDTO>> getDefItem(@PathVariable UUID id) {
        log.debug("REST request to get DefItem : {}", id);
        Mono<DefItemDTO> defItemDTO = defItemService.findOne(id);
        return ResponseUtil.wrapOrNotFound(defItemDTO);
    }

    /**
     * {@code DELETE  /def-items/:id} : delete the "id" defItem.
     *
     * @param id the id of the defItemDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/def-items/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity<Void>> deleteDefItem(@PathVariable UUID id) {
        log.debug("REST request to delete DefItem : {}", id);
        return defItemService
            .delete(id)
            .map(result ->
                ResponseEntity
                    .noContent()
                    .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
                    .build()
            );
    }
}
