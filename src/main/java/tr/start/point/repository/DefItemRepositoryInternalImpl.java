package tr.start.point.repository;

import static org.springframework.data.relational.core.query.Criteria.where;
import static org.springframework.data.relational.core.query.Query.query;

import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Select;
import org.springframework.data.relational.core.sql.SelectBuilder.SelectFromAndJoinCondition;
import org.springframework.data.relational.core.sql.Table;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.r2dbc.core.RowsFetchSpec;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tr.start.point.domain.DefItem;
import tr.start.point.repository.rowmapper.DefItemRowMapper;
import tr.start.point.repository.rowmapper.DefItemRowMapper;
import tr.start.point.repository.rowmapper.DefTypeRowMapper;
import tr.start.point.service.EntityManager;

/**
 * Spring Data SQL reactive custom repository implementation for the DefItem entity.
 */
@SuppressWarnings("unused")
class DefItemRepositoryInternalImpl implements DefItemRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final DefTypeRowMapper deftypeMapper;
    private final DefItemRowMapper defitemMapper;

    private static final Table entityTable = Table.aliased("def_item", EntityManager.ENTITY_ALIAS);
    private static final Table typeTable = Table.aliased("def_type", "e_type");
    private static final Table parentTable = Table.aliased("def_item", "parent");

    public DefItemRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        DefTypeRowMapper deftypeMapper,
        DefItemRowMapper defitemMapper
    ) {
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.deftypeMapper = deftypeMapper;
        this.defitemMapper = defitemMapper;
    }

    @Override
    public Flux<DefItem> findAllBy(Pageable pageable) {
        return findAllBy(pageable, null);
    }

    @Override
    public Flux<DefItem> findAllBy(Pageable pageable, Criteria criteria) {
        return createQuery(pageable, criteria).all();
    }

    RowsFetchSpec<DefItem> createQuery(Pageable pageable, Criteria criteria) {
        List<Expression> columns = DefItemSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        columns.addAll(DefTypeSqlHelper.getColumns(typeTable, "type"));
        columns.addAll(DefItemSqlHelper.getColumns(parentTable, "parent"));
        SelectFromAndJoinCondition selectFrom = Select
            .builder()
            .select(columns)
            .from(entityTable)
            .leftOuterJoin(typeTable)
            .on(Column.create("type_id", entityTable))
            .equals(Column.create("id", typeTable))
            .leftOuterJoin(parentTable)
            .on(Column.create("parent_id", entityTable))
            .equals(Column.create("id", parentTable));

        String select = entityManager.createSelect(selectFrom, DefItem.class, pageable, criteria);
        String alias = entityTable.getReferenceName().getReference();
        String selectWhere = Optional
            .ofNullable(criteria)
            .map(crit ->
                new StringBuilder(select)
                    .append(" ")
                    .append("WHERE")
                    .append(" ")
                    .append(alias)
                    .append(".")
                    .append(crit.toString())
                    .toString()
            )
            .orElse(select); // TODO remove once https://github.com/spring-projects/spring-data-jdbc/issues/907 will be fixed
        return db.sql(selectWhere).map(this::process);
    }

    @Override
    public Flux<DefItem> findAll() {
        return findAllBy(null, null);
    }

    @Override
    public Mono<DefItem> findById(UUID id) {
        return createQuery(null, where("id").is(id)).one();
    }

    private DefItem process(Row row, RowMetadata metadata) {
        DefItem entity = defitemMapper.apply(row, "e");
        entity.setType(deftypeMapper.apply(row, "type"));
        entity.setParent(defitemMapper.apply(row, "parent"));
        return entity;
    }

    @Override
    public <S extends DefItem> Mono<S> insert(S entity) {
        return entityManager.insert(entity);
    }

    @Override
    public <S extends DefItem> Mono<S> save(S entity) {
        if (entity.getId() == null) {
            return insert(entity);
        } else {
            return update(entity)
                .map(numberOfUpdates -> {
                    if (numberOfUpdates.intValue() <= 0) {
                        throw new IllegalStateException("Unable to update DefItem with id = " + entity.getId());
                    }
                    return entity;
                });
        }
    }

    @Override
    public Mono<Integer> update(DefItem entity) {
        //fixme is this the proper way?
        return r2dbcEntityTemplate.update(entity).thenReturn(1);
    }
}
