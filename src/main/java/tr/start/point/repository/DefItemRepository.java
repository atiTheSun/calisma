package tr.start.point.repository;

import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tr.start.point.domain.DefItem;

/**
 * Spring Data SQL reactive repository for the DefItem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DefItemRepository extends R2dbcRepository<DefItem, UUID>, DefItemRepositoryInternal {
    @Query("SELECT * FROM def_item entity WHERE entity.type_id = :id")
    Flux<DefItem> findByType(UUID id);

    @Query("SELECT * FROM def_item entity WHERE entity.type_id IS NULL")
    Flux<DefItem> findAllWhereTypeIsNull();

    @Query("SELECT * FROM def_item entity WHERE entity.parent_id = :id")
    Flux<DefItem> findByParent(UUID id);

    @Query("SELECT * FROM def_item entity WHERE entity.parent_id IS NULL")
    Flux<DefItem> findAllWhereParentIsNull();

    // just to avoid having unambigous methods
    @Override
    Flux<DefItem> findAll();

    @Override
    Mono<DefItem> findById(UUID id);

    @Override
    <S extends DefItem> Mono<S> save(S entity);
}

interface DefItemRepositoryInternal {
    <S extends DefItem> Mono<S> insert(S entity);
    <S extends DefItem> Mono<S> save(S entity);
    Mono<Integer> update(DefItem entity);

    Flux<DefItem> findAll();
    Mono<DefItem> findById(UUID id);
    Flux<DefItem> findAllBy(Pageable pageable);
    Flux<DefItem> findAllBy(Pageable pageable, Criteria criteria);
}
