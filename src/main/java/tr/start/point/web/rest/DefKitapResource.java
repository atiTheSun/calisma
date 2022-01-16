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
import tr.start.point.repository.DefKitapRepository;
import tr.start.point.service.DefKitapService;
import tr.start.point.service.dto.DefKitapDTO;
import tr.start.point.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link tr.start.point.domain.DefKitap}.
 */
@RestController
@RequestMapping("/api")
public class DefKitapResource {

    private final Logger log = LoggerFactory.getLogger(DefKitapResource.class);

    private static final String ENTITY_NAME = "defKitap";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DefKitapService defKitapService;

    private final DefKitapRepository defKitapRepository;

    public DefKitapResource(DefKitapService defKitapService, DefKitapRepository defKitapRepository) {
        this.defKitapService = defKitapService;
        this.defKitapRepository = defKitapRepository;
    }

    /**
     * {@code POST  /def-kitaps} : Create a new defKitap.
     *
     * @param defKitapDTO the defKitapDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new defKitapDTO, or with status {@code 400 (Bad Request)} if the defKitap has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/def-kitaps")
    public Mono<ResponseEntity<DefKitapDTO>> createDefKitap(@Valid @RequestBody DefKitapDTO defKitapDTO) throws URISyntaxException {
        log.debug("REST request to save DefKitap : {}", defKitapDTO);
        if (defKitapDTO.getId() != null) {
            throw new BadRequestAlertException("A new defKitap cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return defKitapService
            .save(defKitapDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/def-kitaps/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /def-kitaps/:id} : Updates an existing defKitap.
     *
     * @param id the id of the defKitapDTO to save.
     * @param defKitapDTO the defKitapDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated defKitapDTO,
     * or with status {@code 400 (Bad Request)} if the defKitapDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the defKitapDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/def-kitaps/{id}")
    public Mono<ResponseEntity<DefKitapDTO>> updateDefKitap(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody DefKitapDTO defKitapDTO
    ) throws URISyntaxException {
        log.debug("REST request to update DefKitap : {}, {}", id, defKitapDTO);
        if (defKitapDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, defKitapDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return defKitapRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return defKitapService
                    .save(defKitapDTO)
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
     * {@code PATCH  /def-kitaps/:id} : Partial updates given fields of an existing defKitap, field will ignore if it is null
     *
     * @param id the id of the defKitapDTO to save.
     * @param defKitapDTO the defKitapDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated defKitapDTO,
     * or with status {@code 400 (Bad Request)} if the defKitapDTO is not valid,
     * or with status {@code 404 (Not Found)} if the defKitapDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the defKitapDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/def-kitaps/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<DefKitapDTO>> partialUpdateDefKitap(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody DefKitapDTO defKitapDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update DefKitap partially : {}, {}", id, defKitapDTO);
        if (defKitapDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, defKitapDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return defKitapRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<DefKitapDTO> result = defKitapService.partialUpdate(defKitapDTO);

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
     * {@code GET  /def-kitaps} : get all the defKitaps.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of defKitaps in body.
     */
    @GetMapping("/def-kitaps")
    public Mono<List<DefKitapDTO>> getAllDefKitaps() {
        log.debug("REST request to get all DefKitaps");
        return defKitapService.findAll().collectList();
    }

    /**
     * {@code GET  /def-kitaps} : get all the defKitaps as a stream.
     * @return the {@link Flux} of defKitaps.
     */
    @GetMapping(value = "/def-kitaps", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<DefKitapDTO> getAllDefKitapsAsStream() {
        log.debug("REST request to get all DefKitaps as a stream");
        return defKitapService.findAll();
    }

    /**
     * {@code GET  /def-kitaps/:id} : get the "id" defKitap.
     *
     * @param id the id of the defKitapDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the defKitapDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/def-kitaps/{id}")
    public Mono<ResponseEntity<DefKitapDTO>> getDefKitap(@PathVariable Long id) {
        log.debug("REST request to get DefKitap : {}", id);
        Mono<DefKitapDTO> defKitapDTO = defKitapService.findOne(id);
        return ResponseUtil.wrapOrNotFound(defKitapDTO);
    }

    /**
     * {@code DELETE  /def-kitaps/:id} : delete the "id" defKitap.
     *
     * @param id the id of the defKitapDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/def-kitaps/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity<Void>> deleteDefKitap(@PathVariable Long id) {
        log.debug("REST request to delete DefKitap : {}", id);
        return defKitapService
            .delete(id)
            .map(result ->
                ResponseEntity
                    .noContent()
                    .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
                    .build()
            );
    }
}
