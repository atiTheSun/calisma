package tr.start.point.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tr.start.point.domain.DefType;

/**
 * Spring Data SQL reactive repository for the DefType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DefTypeRepository extends R2dbcRepository<DefType, String>, DefTypeRepositoryInternal {
    // just to avoid having unambigous methods
    @Override
    Flux<DefType> findAll();

    @Override
    Mono<DefType> findById(String id);

    @Override
    <S extends DefType> Mono<S> save(S entity);
}

interface DefTypeRepositoryInternal {
    <S extends DefType> Mono<S> insert(S entity);
    <S extends DefType> Mono<S> save(S entity);
    Mono<Integer> update(DefType entity);

    Flux<DefType> findAll();
    Mono<DefType> findById(String id);
    Flux<DefType> findAllBy(Pageable pageable);
    Flux<DefType> findAllBy(Pageable pageable, Criteria criteria);
}
