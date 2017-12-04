// Author Michael Meier micmine4@gmail.com
package net.wfs.web.engine.Wfs;


import jptools.util.StringHelper;
import net.wfs.web.Rank;
import net.wfs.web.engine.EngineFactory;
import net.wfs.web.engine.db.DatabaseConnectorFactory;

import java.sql.SQLException;
import java.util.Random;


/**
 * Created by michael on 5/10/17.
 */
public class WfsImpl implements IWfsEngine {
    /**
     * Return Rank color
     *
     * @param rank
     * @return
     */
    public String getRankColor(String rank) {
        if (rank.equalsIgnoreCase("Admin")) {
            return "#9b1414";
        } else if (rank.equalsIgnoreCase("Teacher")) {
            return "#ef5047";
        } else if (rank.equalsIgnoreCase("student")) {
            return "#5231e2";
        } else {
            return "#ffffff";
        }
    }

    /**
     * Return Rank color font
     *
     * @param rank
     * @return
     */
    public String getRankColorFont(String rank) {
        if (rank.equalsIgnoreCase("Admin")) {
            return "#e0dede";
        } else if (rank.equalsIgnoreCase("Teacher")) {
            return "#e0dede";
        } else if (rank.equalsIgnoreCase("student")) {
            return "#282727";
        } else {
            return "#000000";
        }
    }

    /**
     * Return online coler (not Used)
     *
     * @param IsOnline
     * @return
     */
    public String getOnlineColor(String IsOnline) {
        if (IsOnline.equalsIgnoreCase("true")) {
            return "#31af40";
        } else if (IsOnline.equalsIgnoreCase("false")) {
            return "#af4c31";
        } else {
            return "#ffffff";
        }
    }

    public String parseExisting(String msg) {
        return StringHelper.trimRight(StringHelper.trimLeft(msg, '['), ']').trim();
    }

    /**
     * Check if the user has a Rank
     *
     * @param id
     * @return
     */
    public String hasrank(String id) {
        try {
            String existing = EngineFactory.getInstance().getWfsEngine().parseExisting(DatabaseConnectorFactory.getInstance().getAccountDatabaseConnector().isExisting(id));

            String existingparsed = parseExisting(existing);

            if (existingparsed.equals("1")) {
                return DatabaseConnectorFactory.getInstance().getAccountDatabaseConnector().selectRank(id);
            } else {
                return "user";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "error";
    }

    public int safeLongToInt(long l) {
        int i = (int) l;
        if ((long) i != l) {
            throw new IllegalArgumentException(l + " cannot be cast to int without changing its value.");
        }
        return i;
    }

    @Override
    public String deToRank(String deRank) {
        if (deRank.equals("Schueler")) {
            return String.valueOf(Rank.student);
        } else if (deRank.equals("Lehrer")) {
            return String.valueOf(Rank.Teacher);
        } else if (deRank.equals("Admin")) {
            return String.valueOf(Rank.Admin);
        } else {
            return null;
        }
    }

    @Override
    public String rankToDe(String SysRnak) {
        if (SysRnak.equals(Rank.student)) {
            return "Schueler";
        } else if (SysRnak.equals(Rank.Teacher)) {
            return "Lehrer";
        } else if (SysRnak.equals(Rank.Admin)) {
            return "Admin";
        } else {
            return null;
        }
    }

    @Deprecated
    public String simplePassword() {
        int len = 5;
        String charsCaps = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String chars = "abcdefghijklmnopqrstuvwxyz";
        String nums = "0123456789";
        String symbols = "!@#$%^&*_=+-/€.?<>)";

        String passSymbols = charsCaps + chars + nums + symbols;
        Random rnd = new Random();

        char[] password = new char[len];
        int index = 0;
        for (int i = 0; i < len; i++) {
            password[i] = passSymbols.charAt(rnd.nextInt(passSymbols.length()));

        }
        return String.valueOf(password);

    }

}
