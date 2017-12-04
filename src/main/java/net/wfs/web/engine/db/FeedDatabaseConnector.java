// Author Michael Meier micmine4@gmail.com
package net.wfs.web.engine.db;

import jptools.database.DatabaseManager;
import jptools.database.sqlprocessor.SimpleCreateSQLProcessor;
import jptools.database.sqlprocessor.SimpleDeleteSQLProcessor;
import jptools.database.sqlprocessor.SimpleInsertSQLProcessor;
import jptools.database.sqlprocessor.SimpleUpdateSQLProcessor;

import java.sql.SQLException;

public class FeedDatabaseConnector extends AbstractDatabaseConnector {

    public FeedDatabaseConnector(DatabaseManager databaseManager) {
        super(databaseManager);
    }

    public void createTable() throws SQLException {
        getDatabaseManager().executeQuery(new SimpleCreateSQLProcessor(DatabaseConstants.FEED_CREATE_TABLE_QUERY));
        getDatabaseManager().executeQuery(new SimpleInsertSQLProcessor(DatabaseConstants.FEED_CREATE_INDEX_QUERY));
    }


    public void deleteTabel() throws SQLException {
        getDatabaseManager().executeQuery(new SimpleDeleteSQLProcessor(DatabaseConstants.FEED_DROP_TABLE_QUERY));
    }

    public String selectWriter(String id) throws SQLException {
        return selectSimpleQuery(DatabaseConstants.FEED_SELECT_WRITER_QUERY, id);
    }

    public String selectViews(String id) throws SQLException {
        return selectSimpleQuery(DatabaseConstants.FEED_SELECT_VIEWS_QUERY, id);
    }

    public String selectTitle(String id) throws SQLException {
        return selectSimpleQuery(DatabaseConstants.FEED_SELECT_TITLE_QUERY, id);
    }

    public String selectText(String id) throws SQLException {
        return selectSimpleQuery(DatabaseConstants.FEED_SELECT_TEXT_QUERY, id);
    }

    public String selectEventid(String id) throws SQLException {
        return selectSimpleQuery(DatabaseConstants.FEED_SELECT_EVENTID_QUERY, id);
    }

    public Long countFeed() throws SQLException {
        return countSelectQuery(DatabaseConstants.FEED_SELECT_COUNT_QUERY);
    }

    public Long dorpFeed(String id) throws SQLException {
        return dropQuery(DatabaseConstants.FEED_DROP_FEED_QUERY, id);
    }


    public void writeWriter(String id, String writer) throws SQLException {
        Long count = countSelectQuery(DatabaseConstants.FEED_SELECT_COUNT_FEED_BYID_QUERY, id);

        if (count == null || count.longValue() == 0) {
            getDatabaseManager().executeQuery(new SimpleInsertSQLProcessor(DatabaseConstants.FEED_INSERT_FEED_QUERY), new Object[]{id, writer, "", "", "" , ""});
        } else {
            getDatabaseManager().executeQuery(new SimpleUpdateSQLProcessor(DatabaseConstants.FEED_UPDATE_WRITER_QUERY), new Object[]{writer, id});
        }
    }


    public void writeViews(String id, String views) throws SQLException {
        Long count = countSelectQuery(DatabaseConstants.FEED_SELECT_COUNT_FEED_BYID_QUERY, id);

        if (count == null || count.longValue() == 0) {
            getDatabaseManager().executeQuery(new SimpleInsertSQLProcessor(DatabaseConstants.FEED_INSERT_FEED_QUERY), new Object[]{id, "", views, "", "", ""});
        } else {
            getDatabaseManager().executeQuery(new SimpleUpdateSQLProcessor(DatabaseConstants.FEED_UPDATE_VIEWS_QUERY), new Object[]{views, id});
        }
    }

    public void writeTitle(String id, String title) throws SQLException {
        Long count = countSelectQuery(DatabaseConstants.FEED_SELECT_COUNT_FEED_BYID_QUERY, id);

        if (count == null || count.longValue() == 0) {
            getDatabaseManager().executeQuery(new SimpleInsertSQLProcessor(DatabaseConstants.FEED_INSERT_FEED_QUERY), new Object[]{id, "", "", title , "", ""});
        } else {
            getDatabaseManager().executeQuery(new SimpleUpdateSQLProcessor(DatabaseConstants.FEED_UPDATE_TITLE_QUERY), new Object[]{title, id});
        }
    }

    public void writeText(String id, String text) throws SQLException {
        Long count = countSelectQuery(DatabaseConstants.FEED_SELECT_COUNT_FEED_BYID_QUERY, id);

        if (count == null || count.longValue() == 0) {
            getDatabaseManager().executeQuery(new SimpleInsertSQLProcessor(DatabaseConstants.FEED_INSERT_FEED_QUERY), new Object[]{id, "", "", text, ""});
        } else {
            getDatabaseManager().executeQuery(new SimpleUpdateSQLProcessor(DatabaseConstants.FEED_UPDATE_TEXT_QUERY), new Object[]{text, id});
        }
    }

    public void writeEventId(String id, String eventid) throws SQLException {
        Long count = countSelectQuery(DatabaseConstants.FEED_SELECT_COUNT_FEED_BYID_QUERY, id);

        if (count == null || count.longValue() == 0) {
            getDatabaseManager().executeQuery(new SimpleInsertSQLProcessor(DatabaseConstants.FEED_INSERT_FEED_QUERY), new Object[]{id, "", "", "", eventid});
        } else {
            getDatabaseManager().executeQuery(new SimpleUpdateSQLProcessor(DatabaseConstants.FEED_UPDATE_EVENTID_QUERY), new Object[]{eventid, id});
        }
    }

    public String isExisting(String id) throws SQLException {
        return String.valueOf(selectQuery(FEED_EXIST(id)));
    }

    String FEED_EXIST(String id) {
        return "SELECT EXISTS(SELECT 1 FROM wfs.feed WHERE id LIKE '%" + id + "%'" + "LIMIT 1)";
    }
}
