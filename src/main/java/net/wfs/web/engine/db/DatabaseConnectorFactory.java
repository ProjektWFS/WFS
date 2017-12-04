// Author Michael Meier micmine4@gmail.com
package net.wfs.web.engine.db;

import jptools.database.DatabaseConfig;
import jptools.database.DatabaseManager;
import jptools.database.connection.pool.DatabasePoolManager;
import jptools.logger.Logger;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;


public final class DatabaseConnectorFactory {
    private static DatabaseConnectorFactory instance;
    private DatabasePoolManager databasePoolManager;
    private AccoundDatabaseConnector account;
    private FeedDatabaseConnector feed;
    private GroupDatabaseConnector Group;
    private GroupUsageDatabaseConnector Groupusage;
    private static Logger log = Logger.getLogger(DatabaseConnectorFactory.class);


    private DatabaseConnectorFactory() {

        try {
            databasePoolManager = connect();
        } catch (SQLException e) {
            log.error("No database connection");
        }

        DatabaseManager databaseManager = new DatabaseManager(databasePoolManager);
        account = new AccoundDatabaseConnector(databaseManager);
        feed = new FeedDatabaseConnector(databaseManager);
        Group = new GroupDatabaseConnector(databaseManager);
        Groupusage = new GroupUsageDatabaseConnector(databaseManager);
    }


    /**
     * Get the factory instance
     *
     * @return the instance
     */
    public static DatabaseConnectorFactory getInstance() throws SQLException {

        if (instance==null) {
            synchronized (DatabaseConnectorFactory.class) {
                instance = new DatabaseConnectorFactory();
            }
        }

        return instance;
    }

    public void closeConnections() {
        databasePoolManager.stop();
        databasePoolManager.cleanup();
    }




    public GroupDatabaseConnector getGroupDatabaseConnector() {
        return Group;
    }

    public GroupUsageDatabaseConnector getGroupusageDatabaseConnector() {
        return Groupusage;
    }


    public AccoundDatabaseConnector getAccountDatabaseConnector() {
        return account;
    }

    public FeedDatabaseConnector getFeedDatabaseConnector() {
        return feed;
    }


    private DatabasePoolManager connect() throws SQLException {

        Properties prop = new Properties();

        try {
            InputStream inputStream = AbstractDatabaseConnector.class.getResourceAsStream("/database.properties");
            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                System.err.println("Could not find database.properties!");
            }
        } catch (Exception e) {
            System.err.println("Could not read database settings: " + e.getMessage());
        }

        DatabaseConfig databaseConfig = new DatabaseConfig(prop);
        return new DatabasePoolManager(databaseConfig);
    }
}
