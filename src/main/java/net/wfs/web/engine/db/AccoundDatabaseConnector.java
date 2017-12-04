// Author Michael Meier micmine4@gmail.com
package net.wfs.web.engine.db;

import jptools.database.DatabaseManager;
import jptools.database.sqlprocessor.SimpleCreateSQLProcessor;
import jptools.database.sqlprocessor.SimpleDeleteSQLProcessor;
import jptools.database.sqlprocessor.SimpleInsertSQLProcessor;
import jptools.database.sqlprocessor.SimpleUpdateSQLProcessor;

import java.sql.SQLException;

public class AccoundDatabaseConnector extends AbstractDatabaseConnector {

    public AccoundDatabaseConnector(DatabaseManager databaseManager) {
        super(databaseManager);
    }

    public void createTable() throws SQLException {
        getDatabaseManager().executeQuery(new SimpleCreateSQLProcessor(DatabaseConstants.ACCOUND_CREATE_TABLE_QUERY));
        getDatabaseManager().executeQuery(new SimpleInsertSQLProcessor(DatabaseConstants.ACCOUND_CREATE_INDEX_QUERY));
    }


    public void deleteTabel() throws SQLException {
        getDatabaseManager().executeQuery(new SimpleDeleteSQLProcessor(DatabaseConstants.ACCOUND_DROP_TABLE_QUERY));
    }


    public String selectName(String id) throws SQLException {
        return selectSimpleQuery(DatabaseConstants.ACCOUND_SELECT_NAME_QUERY, id);
    }

    public String selectId(String name) throws SQLException {
        return selectSimpleQuery(DatabaseConstants.ACCOUND_SELECT_ID_QUERY, name);
    }

    public String selectRank(String id) throws SQLException {
        return selectSimpleQuery(DatabaseConstants.ACCOUND_SELECT_RANK_QUERY, id);
    }

    public String selectPassword(String id) throws SQLException {
        return selectSimpleQuery(DatabaseConstants.ACCOUND_SELECT_PASSWORD_QUERY, id);
    }

    public Long countAccound() throws SQLException {
        return countSelectQuery(DatabaseConstants.ACCOUND_SELECT_COUNT_QUERY);
    }

    public Long dorpAccound(String id) throws SQLException {
        return dropQuery(DatabaseConstants.ACCOUND_DROP_ACCOUND_QUERY, id);
    }

    public String isExisting(String id) throws SQLException {
        return String.valueOf(selectQuery(ACCOUND_EXIST(id)));
    }

    public void writeName(String id, String name) throws SQLException {
        Long count = countSelectQuery(DatabaseConstants.ACCOUND_SELECT_COUNT_ACCOUND_BYID_QUERY, id);

        if (count == null || count.longValue() == 0) {
            getDatabaseManager().executeQuery(new SimpleInsertSQLProcessor(DatabaseConstants.ACCOUND_INSERT_ACCOUND_QUERY), new Object[]{id, "", name, ""});
        } else {
            getDatabaseManager().executeQuery(new SimpleUpdateSQLProcessor(DatabaseConstants.ACCOUND_UPDATE_NAME_QUERY), new Object[]{name, id});
        }
    }


    public void writeId(String id, String name) throws SQLException {
        Long count = countSelectQuery(DatabaseConstants.ACCOUND_SELECT_COUNT_ACCOUND_BYID_QUERY, id);

        if (count == null || count.longValue() == 0) {
            getDatabaseManager().executeQuery(new SimpleInsertSQLProcessor(DatabaseConstants.ACCOUND_INSERT_ACCOUND_QUERY), new Object[]{id, "", name, ""});
        } else {
            getDatabaseManager().executeQuery(new SimpleUpdateSQLProcessor(DatabaseConstants.ACCOUND_UPDATE_ID_QUERY), new Object[]{id, name});
        }
    }

    public void writeRank(String id, String rank) throws SQLException {
        Long count = countSelectQuery(DatabaseConstants.ACCOUND_SELECT_COUNT_ACCOUND_BYID_QUERY, id);

        if (count == null || count.longValue() == 0) {
            getDatabaseManager().executeQuery(new SimpleInsertSQLProcessor(DatabaseConstants.ACCOUND_INSERT_ACCOUND_QUERY), new Object[]{id, rank, "", ""});
        } else {
            getDatabaseManager().executeQuery(new SimpleUpdateSQLProcessor(DatabaseConstants.ACCOUND_UPDATE_RANK_QUERY), new Object[]{rank, id});
        }
    }

    public void writePassword(String id, String password) throws SQLException {
        Long count = countSelectQuery(DatabaseConstants.ACCOUND_SELECT_COUNT_ACCOUND_BYID_QUERY, id);

        if (count == null || count.longValue() == 0) {
            getDatabaseManager().executeQuery(new SimpleInsertSQLProcessor(DatabaseConstants.ACCOUND_INSERT_ACCOUND_QUERY), new Object[]{id, "", "", password});
        } else {
            getDatabaseManager().executeQuery(new SimpleUpdateSQLProcessor(DatabaseConstants.ACCOUND_UPDATE_PASSWORD_QUERY), new Object[]{password, id});
        }
    }

    String ACCOUND_EXIST(String id) {
        return "SELECT EXISTS(SELECT 1 FROM wfs.account WHERE id LIKE '%" + id + "%'" + "LIMIT 1)";
    }
}
