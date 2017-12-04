// Author Michael Meier micmine4@gmail.com
package net.wfs.web.engine.db;

import jptools.database.DatabaseManager;
import jptools.database.sqlprocessor.SimpleCreateSQLProcessor;
import jptools.database.sqlprocessor.SimpleDeleteSQLProcessor;
import jptools.database.sqlprocessor.SimpleInsertSQLProcessor;
import jptools.database.sqlprocessor.SimpleUpdateSQLProcessor;

import java.sql.SQLException;

public class GroupDatabaseConnector extends AbstractDatabaseConnector {

    public GroupDatabaseConnector(DatabaseManager databaseManager) {
        super(databaseManager);
    }

    public void createTable() throws SQLException {
        getDatabaseManager().executeQuery(new SimpleCreateSQLProcessor(DatabaseConstants.GROUP_CREATE_TABLE_QUERY));
        //getDatabaseManager().executeQuery(new SimpleInsertSQLProcessor(DatabaseConstants.GROUP_CREATE_INDEX_QUERY));
    }


    public void deleteTabel() throws SQLException {
        getDatabaseManager().executeQuery(new SimpleDeleteSQLProcessor(DatabaseConstants.GROUP_DROP_TABLE_QUERY));
    }

    public String selectName(String id) throws SQLException {
        return selectSimpleQuery(DatabaseConstants.GROUP_SELECT_NAME_QUERY, id);
    }

    public String selectId(String name) throws SQLException {
        return selectSimpleQuery(DatabaseConstants.GROUP_SELECT_ID_QUERY, name);
    }

    public String selectSize(String id) throws SQLException {
        return selectSimpleQuery(DatabaseConstants.GROUP_SELECT_SIZE_QUERY, id);
    }


    public Long countGroup() throws SQLException {
        return countSelectQuery(DatabaseConstants.GROUP_SELECT_COUNT_QUERY);
    }

    public Long dorpGroup(String id) throws SQLException {
        return dropQuery(DatabaseConstants.GROUP_DROP_CLASS_QUERY, id);
    }


    public void writeName(String id, String name) throws SQLException {
        Long count = countSelectQuery(DatabaseConstants.GROUP_SELECT_COUNT_GROUP_BYID_QUERY, id);

        if (count == null || count.longValue() == 0) {
            getDatabaseManager().executeQuery(new SimpleInsertSQLProcessor(DatabaseConstants.GROUP_INSERT_CLASS_QUERY), new Object[]{id, name, ""});
        } else {
            getDatabaseManager().executeQuery(new SimpleUpdateSQLProcessor(DatabaseConstants.GROUP_UPDATE_NAME_QUERY), new Object[]{name, id});
        }
    }


    public void writeSize(String id, String size) throws SQLException {
        Long count = countSelectQuery(DatabaseConstants.GROUP_SELECT_COUNT_GROUP_BYID_QUERY, id);

        if (count == null || count.longValue() == 0) {
            getDatabaseManager().executeQuery(new SimpleInsertSQLProcessor(DatabaseConstants.GROUP_INSERT_CLASS_QUERY), new Object[]{id, "", size});
        } else {
            getDatabaseManager().executeQuery(new SimpleUpdateSQLProcessor(DatabaseConstants.GROUP_UPDATE_SIZE_QUERY), new Object[]{size, id});
        }
    }



    public String isExisting(String id) throws SQLException {
        return String.valueOf(selectQuery(Group_EXIST(id)));
    }

    String Group_EXIST(String id) {
        return "SELECT EXISTS(SELECT 1 FROM wfs.group WHERE id LIKE '%" + id + "%'" + "LIMIT 1)";
    }

}
