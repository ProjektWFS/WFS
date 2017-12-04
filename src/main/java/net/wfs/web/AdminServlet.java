// Author Michael Meier micmine4@gmail.com
package net.wfs.web;

import jptools.logger.Logger;
import net.wfs.web.engine.EngineFactory;
import net.wfs.web.engine.db.DatabaseConnectorFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Properties;

public class AdminServlet extends HttpServlet {
    private static Logger log = Logger.getLogger(AdminServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        HttpSession session = req.getSession();

        if (session.getAttribute(LoginServlet.RANK_ATTRIBUTE_NAME).equals(Rank.Teacher) ||
                session.getAttribute(LoginServlet.RANK_ATTRIBUTE_NAME).equals(Rank.Admin)) {

            EngineFactory.getInstance().getWebComponentEngine().getHeader(req, resp);
            EngineFactory.getInstance().getWebComponentEngine().getTop(req, resp);


            out.print("Account : <br>");
            out.print("<form action=\"admin\" method=\"post\">\n" +
                    "  <input type=\"hidden\" class=\"form-control\" name=\"db\" value=\"account\">\n" +
                    "  <input type=\"submit\" class=\"form-control\" name=\"type\" value=\"create\">\n" +
                    "</form>\n");
            out.print("<form action=\"admin\" method=\"post\">\n" +
                    "  <input type=\"hidden\" class=\"form-control\" name=\"db\" value=\"account\">\n" +
                    "  <input type=\"submit\" class=\"form-control\" name=\"type\" value=\"delete\">\n" +
                    "</form>\n");

            out.print("Feed : <br>");
            out.print("<form action=\"admin\" method=\"post\">\n" +
                    "  <input type=\"hidden\" class=\"form-control\" name=\"db\" value=\"feed\">\n" +
                    "  <input type=\"submit\" class=\"form-control\" name=\"type\" value=\"create\">\n" +
                    "</form>\n");
            out.print("<form action=\"admin\" method=\"post\">\n" +
                    "  <input type=\"hidden\" class=\"form-control\" name=\"db\" value=\"feed\">\n" +
                    "  <input type=\"submit\" class=\"form-control\" name=\"type\" value=\"delete\">\n" +
                    "</form>\n");

            out.print("Group : <br>");
            out.print("<form action=\"admin\" method=\"post\">\n" +
                    "  <input type=\"hidden\" class=\"form-control\" name=\"db\" value=\"group\">\n" +
                    "  <input type=\"submit\" class=\"form-control\" name=\"type\" value=\"create\">\n" +
                    "</form>\n");
            out.print("<form action=\"admin\" method=\"post\">\n" +
                    "  <input type=\"hidden\" class=\"form-control\" name=\"db\" value=\"group\">\n" +
                    "  <input type=\"submit\" class=\"form-control\" name=\"type\" value=\"delete\">\n" +
                    "</form>\n");

            out.print("GroupUsage : <br>");
            out.print("<form action=\"admin\" method=\"post\">\n" +
                    "  <input type=\"hidden\" class=\"form-control\" name=\"db\" value=\"groupusage\">\n" +
                    "  <input type=\"submit\" class=\"form-control\" name=\"type\" value=\"create\">\n" +
                    "</form>\n");
            out.print("<form action=\"admin\" method=\"post\">\n" +
                    "  <input type=\"hidden\" class=\"form-control\" name=\"db\" value=\"groupusage\">\n" +
                    "  <input type=\"submit\" class=\"form-control\" name=\"type\" value=\"delete\">\n" +
                    "</form>\n");

            EngineFactory.getInstance().getWebComponentEngine().getBotom(req, resp);
        } else {
            resp.sendRedirect("/feed");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        HttpSession session = req.getSession();

        if (session.getAttribute(LoginServlet.RANK_ATTRIBUTE_NAME).equals(Rank.Teacher) ||
                session.getAttribute(LoginServlet.RANK_ATTRIBUTE_NAME).equals(Rank.Admin)) {
            String database = req.getParameter("db");
            String type = req.getParameter("type");
            Properties prop = new Properties();
            InputStream inputStream = LoginServlet.class.getResourceAsStream("/account.properties");
            prop.load(inputStream);
            try {
                // executers

                if (type != null && database != null) {
                    //get database and execute
                    if (database.equals("account")) {
                        if (type.equals("create")) {
                            DatabaseConnectorFactory.getInstance().getAccountDatabaseConnector().createTable();
                            DatabaseConnectorFactory.getInstance().getAccountDatabaseConnector().writeName(prop.getProperty("standard.id"), prop.getProperty("standard.name"));
                            DatabaseConnectorFactory.getInstance().getAccountDatabaseConnector().writePassword(prop.getProperty("standard.id"), prop.getProperty("standard.password"));
                            DatabaseConnectorFactory.getInstance().getAccountDatabaseConnector().writeRank(prop.getProperty("standard.id"), prop.getProperty("standard.rank"));
                        } else if (type.equals("delete")) {
                            DatabaseConnectorFactory.getInstance().getAccountDatabaseConnector().deleteTabel();
                        } else {
                            log.debug("unknow type");
                        }
                    } else if (database.equals("feed")) {
                        if (type.equals("create")) {
                            DatabaseConnectorFactory.getInstance().getAccountDatabaseConnector().createTable();
                        } else if (type.equals("delete")) {
                            DatabaseConnectorFactory.getInstance().getAccountDatabaseConnector().deleteTabel();
                        } else {
                            log.debug("unknow type");
                        }
                    } else if (database.equals("group")) {
                        if (type.equals("create")) {
                            DatabaseConnectorFactory.getInstance().getGroupDatabaseConnector().createTable();
                        } else if (type.equals("delete")) {
                            DatabaseConnectorFactory.getInstance().getGroupDatabaseConnector().deleteTabel();
                        } else {
                            log.debug("unknow type");
                        }
                    } else if (database.equals("groupusage")) {
                        if (type.equals("create")) {
                            DatabaseConnectorFactory.getInstance().getGroupusageDatabaseConnector().createTable();
                        } else if (type.equals("delete")) {
                            DatabaseConnectorFactory.getInstance().getGroupusageDatabaseConnector().deleteTabel();
                        } else {
                            log.debug("unknow type");
                        }
                    }
                } else {
                    log.debug("unknow database");
                }

                resp.sendRedirect("/wfs/admin");
            } catch (SQLException e) {
                e.printStackTrace();
                resp.sendRedirect("/wfs/admin");
            }

        } else {
            resp.sendRedirect("/feed");
        }
    }
}
