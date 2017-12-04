// Author Michael Meier micmine4@gmail.com
package net.wfs.web.engine.Wfs;


import net.wfs.web.Rank;

/**
 * Created by michael on 5/10/17.
 */
public interface IWfsEngine {

    String getRankColor(String rank);

    String getRankColorFont(String rank);

    String getOnlineColor(String IsOnline);

    String hasrank(String id);

    String parseExisting(String existing);

    int safeLongToInt(long l);

    String deToRank(String deRank);

    String rankToDe(String Rank);

    String simplePassword();
}
