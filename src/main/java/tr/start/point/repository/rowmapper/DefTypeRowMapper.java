package tr.start.point.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;
import tr.start.point.domain.DefType;
import tr.start.point.service.ColumnConverter;

/**
 * Converter between {@link Row} to {@link DefType}, with proper type conversions.
 */
@Service
public class DefTypeRowMapper implements BiFunction<Row, String, DefType> {

    private final ColumnConverter converter;

    public DefTypeRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link DefType} stored in the database.
     */
    @Override
    public DefType apply(Row row, String prefix) {
        DefType entity = new DefType();
        entity.setId(converter.fromRow(row, prefix + "_id", String.class));
        entity.setName(converter.fromRow(row, prefix + "_name", String.class));
        return entity;
    }
}
