// Author Michael Meier micmine4@gmail.com
package net.wfs.web.engine.db;

import jptools.database.DatabaseManager;
import jptools.database.sqlprocessor.SimpleCreateSQLProcessor;
import jptools.database.sqlprocessor.SimpleDeleteSQLProcessor;
import jptools.database.sqlprocessor.SimpleInsertSQLProcessor;
import jptools.logger.Logger;

import java.sql.SQLException;
import java.util.List;


public class GroupUsageDatabaseConnector extends AbstractDatabaseConnector {
    private static Logger log = Logger.getLogger(GroupUsageDatabaseConnector.class);

    public GroupUsageDatabaseConnector(DatabaseManager databaseManager) {
        super(databaseManager);
    }

    public void createTable() throws SQLException {
        getDatabaseManager().executeQuery(new SimpleCreateSQLProcessor(DatabaseConstants.GROUPUSAGE_CREATE_TABLE_QUERY));
        //getDatabaseManager().executeQuery(new SimpleInsertSQLProcessor(DatabaseConstants.GROUPUSAGE_CREATE_INDEX_QUERY));
    }


    public void deleteTabel() throws SQLException {
        getDatabaseManager().executeQuery(new SimpleDeleteSQLProcessor(DatabaseConstants.GROUPUSAGE_DROP_TABLE_QUERY));
    }

    public String selectUser(String id) throws SQLException {
        return selectSimpleQuery(DatabaseConstants.GROUPUSAGE_SELECT_USER_QUERY, id);
    }

    public String selectGroup(String id) throws SQLException {
        return selectSimpleQuery(DatabaseConstants.GROUPUSAGE_SELECT_GROUP_QUERY, id);
    }


    public void writeGroupusage(String user, String group) throws SQLException {
        getDatabaseManager().executeQuery(new SimpleInsertSQLProcessor(DatabaseConstants.GROUPUSAGE_INSERT_GROUPUSAGE_QUERY), new Object[]{user, group});

    }

    public void deleteGroupusage(String user, String group) throws SQLException {
        getDatabaseManager().executeQuery(new SimpleInsertSQLProcessor(DatabaseConstants.GROUPUSAGE_DROP_GROUPUSAGE_QUERY), new Object[]{user, group});
    }

    public List<String> Search(GroupUsageType type, String value) throws SQLException {
        return selectQuery(SEARCH_QUERY(type, value));
    }

    public String SEARCH_QUERY(GroupUsageType type, String value) {
        if (type.equals(GroupUsageType.group)) {
            return "SELECT " + "groupid" + " FROM " + DatabaseConstants.GROUPUSAGE_TABLENAME + " WHERE " + "userid" + " LIKE '" + value + "'";
        } else if (type.equals(GroupUsageType.user)) {
            return "SELECT " + "userid" + " FROM " + DatabaseConstants.GROUPUSAGE_TABLENAME + " WHERE " + "groupid" + " LIKE '" + value + "'";
        } else {
            return null;
        }
    }

/*
    public Long dorpGroupusage(String id) throws SQLException {
        return dropQuery(DatabaseConstants.GROUPUSAGE_DROP_GROUPUSAGE_QUERY, id);
    }


    public String isExisting_Group(String group) throws SQLException {
        return String.valueOf(selectQuery(GroupUsage_EXIST_Group(group)));
    }

    public String isExisting_User(String user) throws SQLException {
        return String.valueOf(selectQuery(GroupUsage_EXIST_User(user)));
    }

    String GroupUsage_EXIST_Group(String group) {
        return "SELECT EXISTS(SELECT 1 FROM wfs.groupusage WHERE group LIKE '%" + group + "%'" + "LIMIT 1)";
    }

    String GroupUsage_EXIST_User(String user) {
        return "SELECT EXISTS(SELECT 1 FROM wfs.groupusage WHERE user LIKE '%" + user + "%'" + "LIMIT 1)";
    } */
public Long countGroupusage() throws SQLException {
    return countSelectQuery(DatabaseConstants.GROUPUSAGE_SELECT_COUNT_QUERY);
}
}
