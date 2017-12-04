// Author Michael Meier micmine4@gmail.com
package net.wfs.web;

import jptools.logger.Logger;
import net.wfs.web.engine.EngineFactory;
import net.wfs.web.engine.db.DatabaseConnectorFactory;
import net.wfs.web.engine.db.DatabaseConstants;
import net.wfs.web.engine.db.GroupUsageDatabaseConnector;
import net.wfs.web.engine.db.GroupUsageType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class ViewServlet extends HttpServlet {
    private static Logger log = Logger.getLogger(ViewServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            PrintWriter out = resp.getWriter();
            HttpSession session = req.getSession();
            String id = req.getParameter("id");

            EngineFactory.getInstance().getWebComponentEngine().getHeader(req, resp);
            EngineFactory.getInstance().getWebComponentEngine().getTop(req, resp);

            if (session.getAttribute(LoginServlet.RANK_ATTRIBUTE_NAME).equals(Rank.Teacher) ||
                    session.getAttribute(LoginServlet.RANK_ATTRIBUTE_NAME).equals(Rank.Admin)) {

                if (DatabaseConnectorFactory.getInstance().getGroupDatabaseConnector().countGroup().equals("0")) {
                    out.print("No Group");
                } else {

                    out.print("---------------------------------\n" +
                            "Zu Gruppe hinzufügen\n" +
                            "\n" +
                            "<div class=\"form-group\">" +
                            "<form action=\"view\" method=\"post\" id=\"group\">\n" +
                            "  <select class=\"form-control\" form=\"group\" name=\"group\">\n");
                    for (int i = EngineFactory.getInstance().getWfsEngine().safeLongToInt(DatabaseConnectorFactory.getInstance().getGroupDatabaseConnector().countGroup()); i >= 1; i--) {
                        String existing = EngineFactory.getInstance().getWfsEngine().parseExisting(DatabaseConnectorFactory.getInstance().getGroupDatabaseConnector().isExisting(String.valueOf(i)));
                        String parsedexisting = EngineFactory.getInstance().getWfsEngine().parseExisting(existing);

                        String groupname = DatabaseConnectorFactory.getInstance().getGroupDatabaseConnector().selectName(String.valueOf(i));
                        String groupid = String.valueOf(i);

                        if (parsedexisting.equals("1")) {
                            out.print("<option value=" + groupid + ">" + groupname + "</option>\n");
                        } else {

                        }
                    }
                    out.print("      </select>" +
                            "  <input type=\"hidden\" class=\"form-control\" name=\"user\" value=" + id + ">\n" +
                            "  <input type=\"hidden\" class=\"form-control\" name=\"type\" value=\"add\">\n" +
                            "  <input type=\"submit\" class=\"form-control\" >" +
                            "</form>" +
                            "</div>");
                    /*
                    out.print("---------------------------------\n" +
                            "Zu gruppe hinzufügen\n" +
                            "\n" +
                            "<form action=\"view\" id=\"group\" method=\"post\">\n" +
                            "  <input type=\"hidden\" class=\"form-control\" name=\"user\" value=" + id + ">\n");
                    out.print("  <input type=\"submit\" class=\"form-control\" name=\"type\" value=\"add\" >\n" +
                            "</form>\n" +
                            "\n");
                    out.print("  <select name=\"group\" form=\"group\">\n");
                    for (int i = EngineFactory.getInstance().getWfsEngine().safeLongToInt(DatabaseConnectorFactory.getInstance().getGroupDatabaseConnector().countGroup()); i >= 1; i--) {
                        String existing = EngineFactory.getInstance().getWfsEngine().parseExisting(DatabaseConnectorFactory.getInstance().getGroupDatabaseConnector().isExisting(String.valueOf(i)));
                        String parsedexisting = EngineFactory.getInstance().getWfsEngine().parseExisting(existing);

                        String groupname = DatabaseConnectorFactory.getInstance().getGroupDatabaseConnector().selectName(String.valueOf(i));
                        String groupid = DatabaseConnectorFactory.getInstance().getGroupDatabaseConnector().selectId(String.valueOf(i));

                        if (parsedexisting.equals("1")) {
                            out.print("<option value=" + groupid + ">" + groupname + "</option>\n");
                        } else {

                        }
                    }
                    out.print("  </select>"); */
                }

            } else {
                if (DatabaseConnectorFactory.getInstance().getGroupDatabaseConnector().countGroup().equals("0")) {
                    out.print("No Group");
                } else {
                    out.print("<br>Gruppen : <br>");
                }
            }


            String name = DatabaseConnectorFactory.getInstance().getAccountDatabaseConnector().selectName(id);
            String rank = DatabaseConnectorFactory.getInstance().getAccountDatabaseConnector().selectRank(id);

            out.print("Name : " + name + " | Rank" + rank + ")");


            if (DatabaseConnectorFactory.getInstance().getGroupusageDatabaseConnector().countGroupusage().equals("0") || DatabaseConnectorFactory.getInstance().getGroupusageDatabaseConnector().countGroupusage().equals(0)) {
                out.print("<br>nogroup");
            } else {

                List<String> groups = DatabaseConnectorFactory.getInstance().getGroupusageDatabaseConnector().Search(GroupUsageType.group, id);


                out.print("<br>Gruppen : <br>");
                for (String group : groups) {
                    out.print(DatabaseConnectorFactory.getInstance().getGroupDatabaseConnector().selectName(group) + ", <br>");

                }

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        HttpSession session = req.getSession();

        if (session.getAttribute(LoginServlet.RANK_ATTRIBUTE_NAME).equals(Rank.Teacher) ||
                session.getAttribute(LoginServlet.RANK_ATTRIBUTE_NAME).equals(Rank.Admin)) {

            String user = req.getParameter("user");
            String group = req.getParameter("group");
            String type = req.getParameter("type");

            Properties prop = new Properties();
            InputStream inputStream = LoginServlet.class.getResourceAsStream("/account.properties");
            prop.load(inputStream);
            try {
                // executers

                if (user != null && group != null) {
                    //get database and execute
                    if (type.equals("add")) {
                        DatabaseConnectorFactory.getInstance().getGroupusageDatabaseConnector().writeGroupusage(user, group);
                        DatabaseConnectorFactory.getInstance().getGroupDatabaseConnector().writeSize(group, String.valueOf(Integer.parseInt(DatabaseConnectorFactory.getInstance().getGroupDatabaseConnector().selectSize(group)) + 1));
                    } else if (type.equals("remove")) {
                        DatabaseConnectorFactory.getInstance().getGroupusageDatabaseConnector().deleteGroupusage(user, group);
                        DatabaseConnectorFactory.getInstance().getGroupDatabaseConnector().writeSize(group, String.valueOf(Integer.parseInt(DatabaseConnectorFactory.getInstance().getGroupDatabaseConnector().selectSize(group)) - 1));
                    } else {
                        log.debug("unknow user or group");
                    }
                } else {
                    log.debug("unknow user or group");
                }

                resp.sendRedirect("/wfs/view?id=" + user);
            } catch (SQLException e) {
                e.printStackTrace();
                resp.sendRedirect("/wfs/view?id=" + user);
            }

        } else {
            resp.sendRedirect("/wfs/list");
        }
    }
}
