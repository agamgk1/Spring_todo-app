package db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

//klasa służąca do migracji bazy danych przy uzyciu javy i springa - musi rozszezac klase oraz implementowac metode
public class V2__insert_example_todo extends BaseJavaMigration {
    @Override
    public void migrate(Context context) {
        new JdbcTemplate(new SingleConnectionDataSource(context.getConnection(), true))
    .execute("insert into tasks (description, done) values ('Learn java migration', true)");
    }
}
