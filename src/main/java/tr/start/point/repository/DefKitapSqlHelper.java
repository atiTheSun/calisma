package tr.start.point.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class DefKitapSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("isbn", table, columnPrefix + "_isbn"));
        columns.add(Column.aliased("kitap_adi", table, columnPrefix + "_kitap_adi"));

        return columns;
    }
}
