// Author Michael Meier micmine4@gmail.com
package net.wfs.web.engine.db;

/**
 * Created by michael on 5/25/17.
 */
public interface DatabaseConstants {


    // account -----------------------------------------------------------------------------------------------------------------------

    // id , rank , name , password
    // ID , RANK , NAME , PASSWORD

    String ACCOUND_TABLENAME = "wfs.account";
    String ACCOUND_CREATE_TABLE_QUERY = "CREATE TABLE " + ACCOUND_TABLENAME + "(id VARCHAR(100) , rank VARCHAR(100) , name VARCHAR(100), password VARCHAR(100))";
    String ACCOUND_CREATE_INDEX_QUERY = "CREATE INDEX " + "wfs" + "_idx1 ON " + "account" + " (id)";
    String ACCOUND_DROP_TABLE_QUERY = "DROP TABLE " + ACCOUND_TABLENAME;

    String ACCOUND_SELECT_ID_QUERY = "SELECT id from " + ACCOUND_TABLENAME + " WHERE name = ?";
    String ACCOUND_SELECT_NAME_QUERY = "SELECT name from " + ACCOUND_TABLENAME + " WHERE id = ?";
    String ACCOUND_SELECT_RANK_QUERY = "SELECT rank from " + ACCOUND_TABLENAME + " WHERE id = ?";
    String ACCOUND_SELECT_PASSWORD_QUERY = "SELECT password from " + ACCOUND_TABLENAME + " WHERE id = ?";

    String ACCOUND_SELECT_COUNT_ACCOUND_BYID_QUERY = "SELECT COUNT(*) from " + ACCOUND_TABLENAME + " WHERE id = ?";
    String ACCOUND_SELECT_COUNT_QUERY = "SELECT COUNT(*) from " + ACCOUND_TABLENAME;

    String ACCOUND_INSERT_ACCOUND_QUERY = "INSERT INTO " + ACCOUND_TABLENAME + " VALUES(?, ?, ?, ?)";

    String ACCOUND_DROP_ACCOUND_QUERY = "DELETE FROM " + ACCOUND_TABLENAME + " WHERE id = ?";

    String ACCOUND_UPDATE_ID_QUERY = "UPDATE " + ACCOUND_TABLENAME + " SET id = ? WHERE name = ?";
    String ACCOUND_UPDATE_RANK_QUERY = "UPDATE " + ACCOUND_TABLENAME + " SET rank = ? WHERE id = ?";
    String ACCOUND_UPDATE_NAME_QUERY = "UPDATE " + ACCOUND_TABLENAME + " SET name = ? WHERE id = ?";
    String ACCOUND_UPDATE_PASSWORD_QUERY = "UPDATE " + ACCOUND_TABLENAME + " SET password = ? WHERE id = ?";

// feed -----------------------------------------------------------------------------------------------------------------------

    // id , writer , views , title , text , eventid (if neaded)
    // ID , WRITER , VIEWS , TITLE , TEXT , EVENTID (if neaded)

    String FEED_TABLENAME = "wfs.feed";
    String FEED_CREATE_TABLE_QUERY = "CREATE TABLE " + FEED_TABLENAME + "(id VARCHAR(100) , writer VARCHAR(100) , views VARCHAR(100), title VARCHAR(100), text VARCHAR(8000), eventid VARCHAR(100))";
    String FEED_CREATE_INDEX_QUERY = "CREATE INDEX " + "wfs" + "_idx1 ON " + "feed" + " (id)";
    String FEED_DROP_TABLE_QUERY = "DROP TABLE " + FEED_TABLENAME;

    String FEED_SELECT_WRITER_QUERY = "SELECT writer from " + FEED_TABLENAME + " WHERE id = ?";
    String FEED_SELECT_VIEWS_QUERY = "SELECT views from " + FEED_TABLENAME + " WHERE id = ?";
    String FEED_SELECT_TITLE_QUERY = "SELECT title from " + FEED_TABLENAME + " WHERE id = ?";
    String FEED_SELECT_TEXT_QUERY = "SELECT text from " + FEED_TABLENAME + " WHERE id = ?";
    String FEED_SELECT_EVENTID_QUERY = "SELECT eventid from " + FEED_TABLENAME + " WHERE id = ?";

    String FEED_SELECT_COUNT_FEED_BYID_QUERY = "SELECT COUNT(*) from " + FEED_TABLENAME + " WHERE id = ?";
    String FEED_SELECT_COUNT_QUERY = "SELECT COUNT(*) from " + FEED_TABLENAME;

    String FEED_INSERT_FEED_QUERY = "INSERT INTO " + FEED_TABLENAME + " VALUES(?, ?, , ?, ?, ?)";

    String FEED_DROP_FEED_QUERY = "DELETE FROM " + FEED_TABLENAME + " WHERE id = ?";

    String FEED_UPDATE_WRITER_QUERY = "UPDATE " + FEED_TABLENAME + " SET writer = ? WHERE id = ?";
    String FEED_UPDATE_VIEWS_QUERY = "UPDATE " + FEED_TABLENAME + " SET views = ? WHERE id = ?";
    String FEED_UPDATE_TITLE_QUERY = "UPDATE " + FEED_TABLENAME + " SET title = ? WHERE id = ?";
    String FEED_UPDATE_TEXT_QUERY = "UPDATE " + FEED_TABLENAME + " SET text = ? WHERE id = ?";
    String FEED_UPDATE_EVENTID_QUERY = "UPDATE " + FEED_TABLENAME + " SET eventid = ? WHERE id = ?";

    // Group -----------------------------------------------------------------------------------------------------------------------

    // id , name , size
    // ID , NAME , SIZE

    String GROUP_TABLENAME = "wfs.group";
    String GROUP_CREATE_TABLE_QUERY = "CREATE TABLE " + GROUP_TABLENAME + "(id VARCHAR(100) , name VARCHAR(100) , size VARCHAR(100));";
    String GROUP_CREATE_INDEX_QUERY = "CREATE INDEX wfs ON group (id);";
    String GROUP_DROP_TABLE_QUERY = "DROP TABLE " + GROUP_TABLENAME;

    String GROUP_SELECT_ID_QUERY = "SELECT id from " + GROUP_TABLENAME + " WHERE name = ?";
    String GROUP_SELECT_NAME_QUERY = "SELECT name from " + GROUP_TABLENAME + " WHERE id = ?";
    String GROUP_SELECT_SIZE_QUERY = "SELECT size from " + GROUP_TABLENAME + " WHERE id = ?";

    String GROUP_SELECT_COUNT_GROUP_BYID_QUERY = "SELECT COUNT(*) from " + GROUP_TABLENAME + " WHERE id = ?";
    String GROUP_SELECT_COUNT_QUERY = "SELECT COUNT(*) from " + GROUP_TABLENAME;

    String GROUP_INSERT_CLASS_QUERY = "INSERT INTO " + GROUP_TABLENAME + " VALUES(?, ?, ?)";

    String GROUP_DROP_CLASS_QUERY = "DELETE FROM " + GROUP_TABLENAME + " WHERE id = ?";

    String GROUP_UPDATE_NAME_QUERY = "UPDATE " + GROUP_TABLENAME + " SET name = ? WHERE id = ?";
    String GROUP_UPDATE_SIZE_QUERY = "UPDATE " + GROUP_TABLENAME + " SET size = ? WHERE id = ?";


    // GroupUsage-----------------------------------------------------------------------------------------------------------------------

    // id , name , size
    // ID , NAME , SIZE


    String GROUPUSAGE_TABLENAME = "wfs.groupusage";
    String GROUPUSAGE_CREATE_TABLE_QUERY = "CREATE TABLE " + GROUPUSAGE_TABLENAME + "(userid VARCHAR(100) , groupid VARCHAR(100))";
    String GROUPUSAGE_CREATE_INDEX_QUERY = "CREATE INDEX " + "wfs" + "_idx1 ON " + "groupusage" + " (user)";
    String GROUPUSAGE_DROP_TABLE_QUERY = "DROP TABLE " + GROUPUSAGE_TABLENAME;

    String GROUPUSAGE_SELECT_USER_QUERY = "SELECT userid from " + GROUPUSAGE_TABLENAME + " WHERE groupid = ?";
    String GROUPUSAGE_SELECT_GROUP_QUERY = "SELECT groupid from " + GROUPUSAGE_TABLENAME + " WHERE userid = ?";


    String GROUPUSAGE_SELECT_COUNT_GROUPUSAGE_BYID_QUERY = "SELECT COUNT(*) from " + GROUPUSAGE_TABLENAME + " WHERE id = ?";
    String GROUPUSAGE_SELECT_COUNT_QUERY = "SELECT COUNT(*) from " + GROUPUSAGE_TABLENAME;

    String GROUPUSAGE_INSERT_GROUPUSAGE_QUERY = "INSERT INTO " + GROUPUSAGE_TABLENAME + " VALUES(?, ?)";

    String GROUPUSAGE_DROP_GROUPUSAGE_QUERY = "DELETE FROM " + GROUPUSAGE_TABLENAME + " where (userid = ? AND groupid = ?);";



}