// Author Michael Meier micmine4@gmail.com
package net.wfs.web;

import jptools.logger.Logger;
import jptools.util.EnumUtil;
import net.wfs.web.engine.EngineFactory;
import net.wfs.web.engine.db.DatabaseConnectorFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Properties;

public class LoginServlet extends HttpServlet {
    private static Logger log = Logger.getLogger(LoginServlet.class);
    public static final String RANK_ATTRIBUTE_NAME = "rank";
    public static final String USER_ATTRIBUTE_ID = "id";


    /**
     * check reset mode
     *
     * @return
     */

    public String isReset() {
        Properties prop = new Properties();
        InputStream inputStream = LoginServlet.class.getResourceAsStream("/account.properties");
        try {
            prop.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty("reset");
    }

    /**
     * login disign
     *
     * @param request
     * @param response
     * @throws IOException
     */

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {

            PrintWriter out = response.getWriter();
            HttpSession session = request.getSession();

            String push = request.getParameter("push");
            String first = request.getParameter("first");
            String name = request.getParameter("name");


            String id = DatabaseConnectorFactory.getInstance().getAccountDatabaseConnector().selectId(name);


            // Head


            if (first != null) {

                if (first.equals("true")) {
                    try {
                        if (DatabaseConnectorFactory.getInstance().getAccountDatabaseConnector().selectPassword(id).equalsIgnoreCase("passwd")) {
                            DatabaseConnectorFactory.getInstance().getAccountDatabaseConnector().writePassword(id, EngineFactory.getInstance().getWfsEngine().simplePassword());
                            out.print("Dein passwort : " + DatabaseConnectorFactory.getInstance().getAccountDatabaseConnector().selectPassword(id));
                        } else {
                            response.sendRedirect("login");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    //print datenbank pw
                }


            } else {


                EngineFactory.getInstance().getWebComponentEngine().getHeaderLogin(request, response);
                out.print("<div class=\"wrapper\"><br>");

                if (isReset().equals("1")) {
                    Properties prop = new Properties();
                    InputStream inputStream = LoginServlet.class.getResourceAsStream("/account.properties");
                    prop.load(inputStream);

                    DatabaseConnectorFactory.getInstance().getGroupusageDatabaseConnector().deleteTabel();
                    DatabaseConnectorFactory.getInstance().getGroupusageDatabaseConnector().createTable();
                    DatabaseConnectorFactory.getInstance().getGroupDatabaseConnector().deleteTabel();
                    DatabaseConnectorFactory.getInstance().getGroupDatabaseConnector().createTable();
                    DatabaseConnectorFactory.getInstance().getAccountDatabaseConnector().deleteTabel();
                    DatabaseConnectorFactory.getInstance().getAccountDatabaseConnector().createTable();
                    DatabaseConnectorFactory.getInstance().getAccountDatabaseConnector().writeName(prop.getProperty("standard.id"), prop.getProperty("standard.name"));
                    DatabaseConnectorFactory.getInstance().getAccountDatabaseConnector().writePassword(prop.getProperty("standard.id"), prop.getProperty("standard.password"));
                    DatabaseConnectorFactory.getInstance().getAccountDatabaseConnector().writeRank(prop.getProperty("standard.id"), prop.getProperty("standard.rank"));

                    log.info("Account database is reseated");

                }

                // accound

                out.print("<div class=\"container\">\n" +
                        "    <div class=\"row\">\n" +
                        "        <div class=\"col-md-12\">\n" +
                        "            <div class=\"wrap\">\n" +
                        "                <p class=\"form-title\">\n" +
                        "                    Login</p>\n" +
                        "                <form class=\"login\" action=\"login\" method=\"post\">\n" +
                        "                <input type=\"text\" name=\"name\" placeholder=\"Username\" />\n" +
                        "                <input type=\"password\" name=\"password\" placeholder=\"Password\" />\n" +
                        "                <input type=\"submit\" name=\"submit\" value=\"login\" class=\"btn btn-success btn-sm\" />\n" +
                        "                </form>\n" +
                        "            </div>\n" +
                        "        </div>\n" +
                        "    </div>\n");

                if (push != null) {
                    if (push.equals("false")) {
                        out.print("<script>$.notify(\"Falsches passwort\", \"error\");</script>");
                        out.print("<script>$.notify(\"Falls dies ein fehler ist melde dich bei einem SrMod\", \"info\");</script>");
                    } else {

                    }
                } else {

                }

                out.print("</div>");

                EngineFactory.getInstance().getWebComponentEngine().getBotom(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    /**
     * login mechanic
     *
     * @param request
     * @param response
     * @throws IOException
     */

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        PrintWriter out = response.getWriter();
        String submit = request.getParameter("submit");
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        try {
            // Head
            String id = DatabaseConnectorFactory.getInstance().getAccountDatabaseConnector().selectId(name);
            if (password.equals(DatabaseConnectorFactory.getInstance().getAccountDatabaseConnector().selectPassword(id))) {

                if (DatabaseConnectorFactory.getInstance().getAccountDatabaseConnector().selectPassword(id).equalsIgnoreCase("passwd")) {
                    response.sendRedirect("login?first=true&name=" + name);
                } else {


                    HttpSession session = request.getSession();

                    boolean loggedIn = session != null && session.getAttribute(LoginServlet.RANK_ATTRIBUTE_NAME) != null;
                    if (loggedIn) {
                        // create new session / cookie in case the user was already logged in
                        System.err.println("Create new session because user was already logged in!");
                        session.invalidate();
                        session = request.getSession(true);
                    }

                    Rank rank = EnumUtil.valueOf(Rank.class, DatabaseConnectorFactory.getInstance().getAccountDatabaseConnector().selectRank(id));
                    session.setAttribute(RANK_ATTRIBUTE_NAME, rank);
                    session.setAttribute(USER_ATTRIBUTE_ID, id);
                    //session.setAttribute(USER_ATTRIBUTE_NAME, name);
                    log.debug("User with the name : " + name + " is now lodged in");
                    response.sendRedirect("feed");
                }


            } else {


                out.print("False password");
                log.debug("User with the name : " + name + " Has tried to login (False password)");
                response.sendRedirect("login?push=false");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
