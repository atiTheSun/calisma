package tr.start.point.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
import tr.start.point.repository.DefTypeRepository;
import tr.start.point.service.DefTypeService;
import tr.start.point.service.dto.DefTypeDTO;
import tr.start.point.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link tr.start.point.domain.DefType}.
 */
@RestController
@RequestMapping("/api")
public class DefTypeResource {

    private final Logger log = LoggerFactory.getLogger(DefTypeResource.class);

    private static final String ENTITY_NAME = "defType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DefTypeService defTypeService;

    private final DefTypeRepository defTypeRepository;

    public DefTypeResource(DefTypeService defTypeService, DefTypeRepository defTypeRepository) {
        this.defTypeService = defTypeService;
        this.defTypeRepository = defTypeRepository;
    }

    /**
     * {@code POST  /def-types} : Create a new defType.
     *
     * @param defTypeDTO the defTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new defTypeDTO, or with status {@code 400 (Bad Request)} if the defType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/def-types")
    public Mono<ResponseEntity<DefTypeDTO>> createDefType(@Valid @RequestBody DefTypeDTO defTypeDTO) throws URISyntaxException {
        log.debug("REST request to save DefType : {}", defTypeDTO);
        if (defTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new defType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return defTypeService
            .save(defTypeDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/def-types/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /def-types/:id} : Updates an existing defType.
     *
     * @param id the id of the defTypeDTO to save.
     * @param defTypeDTO the defTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated defTypeDTO,
     * or with status {@code 400 (Bad Request)} if the defTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the defTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/def-types/{id}")
    public Mono<ResponseEntity<DefTypeDTO>> updateDefType(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody DefTypeDTO defTypeDTO
    ) throws URISyntaxException {
        log.debug("REST request to update DefType : {}, {}", id, defTypeDTO);
        if (defTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, defTypeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return defTypeRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return defTypeService
                    .save(defTypeDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, result.getId()))
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /def-types/:id} : Partial updates given fields of an existing defType, field will ignore if it is null
     *
     * @param id the id of the defTypeDTO to save.
     * @param defTypeDTO the defTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated defTypeDTO,
     * or with status {@code 400 (Bad Request)} if the defTypeDTO is not valid,
     * or with status {@code 404 (Not Found)} if the defTypeDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the defTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/def-types/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<DefTypeDTO>> partialUpdateDefType(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody DefTypeDTO defTypeDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update DefType partially : {}, {}", id, defTypeDTO);
        if (defTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, defTypeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return defTypeRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<DefTypeDTO> result = defTypeService.partialUpdate(defTypeDTO);

                return result
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(res ->
                        ResponseEntity
                            .ok()
                            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, res.getId()))
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /def-types} : get all the defTypes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of defTypes in body.
     */
    @GetMapping("/def-types")
    public Mono<List<DefTypeDTO>> getAllDefTypes() {
        log.debug("REST request to get all DefTypes");
        return defTypeService.findAll().collectList();
    }

    /**
     * {@code GET  /def-types} : get all the defTypes as a stream.
     * @return the {@link Flux} of defTypes.
     */
    @GetMapping(value = "/def-types", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<DefTypeDTO> getAllDefTypesAsStream() {
        log.debug("REST request to get all DefTypes as a stream");
        return defTypeService.findAll();
    }

    /**
     * {@code GET  /def-types/:id} : get the "id" defType.
     *
     * @param id the id of the defTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the defTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/def-types/{id}")
    public Mono<ResponseEntity<DefTypeDTO>> getDefType(@PathVariable String id) {
        log.debug("REST request to get DefType : {}", id);
        Mono<DefTypeDTO> defTypeDTO = defTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(defTypeDTO);
    }

    /**
     * {@code DELETE  /def-types/:id} : delete the "id" defType.
     *
     * @param id the id of the defTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/def-types/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity<Void>> deleteDefType(@PathVariable String id) {
        log.debug("REST request to delete DefType : {}", id);
        return defTypeService
            .delete(id)
            .map(result ->
                ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build()
            );
    }
}
