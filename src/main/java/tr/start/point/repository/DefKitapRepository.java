package tr.start.point.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tr.start.point.domain.DefKitap;

/**
 * Spring Data SQL reactive repository for the DefKitap entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DefKitapRepository extends R2dbcRepository<DefKitap, Long>, DefKitapRepositoryInternal {
    // just to avoid having unambigous methods
    @Override
    Flux<DefKitap> findAll();

    @Override
    Mono<DefKitap> findById(Long id);

    @Override
    <S extends DefKitap> Mono<S> save(S entity);
}

interface DefKitapRepositoryInternal {
    <S extends DefKitap> Mono<S> insert(S entity);
    <S extends DefKitap> Mono<S> save(S entity);
    Mono<Integer> update(DefKitap entity);

    Flux<DefKitap> findAll();
    Mono<DefKitap> findById(Long id);
    Flux<DefKitap> findAllBy(Pageable pageable);
    Flux<DefKitap> findAllBy(Pageable pageable, Criteria criteria);
}
