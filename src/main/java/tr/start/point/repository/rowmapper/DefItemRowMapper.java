package tr.start.point.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;
import tr.start.point.domain.DefItem;
import tr.start.point.service.ColumnConverter;

/**
 * Converter between {@link Row} to {@link DefItem}, with proper type conversions.
 */
@Service
public class DefItemRowMapper implements BiFunction<Row, String, DefItem> {

    private final ColumnConverter converter;

    public DefItemRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link DefItem} stored in the database.
     */
    @Override
    public DefItem apply(Row row, String prefix) {
        DefItem entity = new DefItem();
        entity.setId(converter.fromRow(row, prefix + "_id", UUID.class));
        entity.setCode(converter.fromRow(row, prefix + "_code", String.class));
        entity.setName(converter.fromRow(row, prefix + "_name", String.class));
        entity.setTypeId(converter.fromRow(row, prefix + "_type_id", String.class));
        entity.setParentId(converter.fromRow(row, prefix + "_parent_id", UUID.class));
        return entity;
    }
}
