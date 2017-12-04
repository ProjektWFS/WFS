// Author Michael Meier micmine4@gmail.com
package net.wfs.web.engine.db;

import jptools.database.DatabaseManager;
import jptools.database.sqlprocessor.SimpleDeleteSQLProcessor;
import jptools.database.sqlprocessor.SimpleSelectSQLProcessor;
import jptools.logger.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractDatabaseConnector {

    /**
     * The quary managent
     */

    private DatabaseManager databaseManager;


    protected AbstractDatabaseConnector(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    protected String selectSimpleQuery(String sql, String... parameters) throws SQLException {
        Object[] result = getDatabaseManager().executeQuery(new SimpleSelectSQLProcessor(sql), parameters);

        if (result == null || result.length == 0) {
            return null;
        }

        try {
            return (String) result[0];
        } catch (Exception e) {
            System.err.println("Could not cast result as string (" + result[0].getClass().getName() + "):" + result[0]);
            throw e;
        }
    }

    protected List<String> selectQuery(String sql, String... parameters) throws SQLException {
        Object[] result = getDatabaseManager().executeQuery(new SearchSelectSQLProcessor(sql), parameters);

        if (result == null || result.length == 0) {
            return new ArrayList<>();
        }

        try {
            if (result.length > 1) {
                List<String> r = new ArrayList<>();
                for (int i = 0; i<result.length; i++) {
                    try {
                        r.add((String)result[i]);
                    } catch (Exception e) {
                        System.err.println("Could not cast result as string (" + result[i].getClass().getName() + "):" + result[i]);
                    }
                }
                return r;
            } else {
                return Arrays.asList((String) result[0]);
            }
        } catch (Exception e) {
            System.err.println("Could not cast result as string (" + result[0].getClass().getName() + "):" + result[0]);
            throw e;
        }
    }

    protected Long countSelectQuery(String sql, String... parameters) throws SQLException {
        Object[] result = getDatabaseManager().executeQuery(new SimpleSelectSQLProcessor(sql), parameters);

        if (result == null || result.length == 0) {
            return null;
        }

        return (Long) result[0];
    }


    protected Long dropQuery(String sql, String... parameters) throws SQLException {
        Object[] result = getDatabaseManager().executeQuery(new SimpleDeleteSQLProcessor(sql), parameters);

        if (result == null || result.length == 0) {
            return null;
        }

        return new Long((Integer) result[0]);
    }


    protected DatabaseManager getDatabaseManager() throws SQLException {
        return databaseManager;
    }


    class SearchSelectSQLProcessor extends SimpleSelectSQLProcessor {

        public SearchSelectSQLProcessor(String sql) {
            super(sql);
        }

        protected Object[] process(ResultSet rs) throws SQLException {
            List<String> data = new ArrayList();
            if (rs != null) {
                while(rs.next()) {
                    String message = rs.getString(1);
                    data.add(message);
                }
            }

            if (data.size() <= 0) {
                Logger logger = this.getLogger();
                if (logger.isDebugEnabled()) {
                    logger.debug("The SQL select statement has no data selected!");
                }

                return null;
            } else {
                return data.toArray(new String[data.size()]);
            }
        }
    }
}
