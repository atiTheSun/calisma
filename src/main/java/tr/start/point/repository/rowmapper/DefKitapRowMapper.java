package tr.start.point.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;
import tr.start.point.domain.DefKitap;
import tr.start.point.service.ColumnConverter;

/**
 * Converter between {@link Row} to {@link DefKitap}, with proper type conversions.
 */
@Service
public class DefKitapRowMapper implements BiFunction<Row, String, DefKitap> {

    private final ColumnConverter converter;

    public DefKitapRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link DefKitap} stored in the database.
     */
    @Override
    public DefKitap apply(Row row, String prefix) {
        DefKitap entity = new DefKitap();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setIsbn(converter.fromRow(row, prefix + "_isbn", String.class));
        entity.setKitapAdi(converter.fromRow(row, prefix + "_kitap_adi", String.class));
        return entity;
    }
}
