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
import java.io.PrintWriter;
import java.sql.SQLException;

public class ListServlet extends HttpServlet {
    private static Logger log = Logger.getLogger(ListServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            PrintWriter out = resp.getWriter();
            HttpSession session = req.getSession();

            EngineFactory.getInstance().getWebComponentEngine().getHeader(req, resp);
            EngineFactory.getInstance().getWebComponentEngine().getTop(req, resp);

            if (session.getAttribute(LoginServlet.RANK_ATTRIBUTE_NAME).equals(Rank.Teacher) ||
                    session.getAttribute(LoginServlet.RANK_ATTRIBUTE_NAME).equals(Rank.Admin)) {


                out.print("---------------------------------\n" +
                        "Gruppen erstellen\n" +
                        "\n" +
                        "<form action=\"class\" method=\"post\">\n" +
                        "  <input type=\"text\" class=\"form-control\" name=\"name\" placeholder=\"Name\">\n" +
                        "  <input type=\"submit\" class=\"form-control\" name=\"type\" value=\"create\">\n" +
                        "</form>\n" +
                        "\n" +
                        "Klassen : <br>");
            } else {
                out.print("<br>Klassen : <br>");
            }


            for (int i = EngineFactory.getInstance().getWfsEngine().safeLongToInt(DatabaseConnectorFactory.getInstance().getGroupDatabaseConnector().countGroup()); i >= 1; i--) {
                String existing = EngineFactory.getInstance().getWfsEngine().parseExisting(DatabaseConnectorFactory.getInstance().getGroupDatabaseConnector().isExisting(String.valueOf(i)));
                String parsedexisting = EngineFactory.getInstance().getWfsEngine().parseExisting(existing);

                String name = DatabaseConnectorFactory.getInstance().getGroupDatabaseConnector().selectName(String.valueOf(i));
                String size = DatabaseConnectorFactory.getInstance().getGroupDatabaseConnector().selectSize(String.valueOf(i));

                if (parsedexisting.equals("1")) {
                    out.print("( " + name + " : " + size + " )<br>");
                } else {

                }
            }

            if (session.getAttribute(LoginServlet.RANK_ATTRIBUTE_NAME).equals(Rank.Teacher) ||
                    session.getAttribute(LoginServlet.RANK_ATTRIBUTE_NAME).equals(Rank.Admin)) {

                out.print("---------------------------------\n" +
                        "Schüler erstellen\n" +
                        "\n" +
                        "<div class=\"form-group\">" +
                        "<form action=\"accound\" method=\"post\" id=\"schueler\">\n" +
                        "  <input type=\"text\" class=\"form-control\" name=\"name\" placeholder=\"Name\">\n" +
                        "<select class=\"form-control\" form=\"schueler\" name=\"rank\">\n" +
                        "        <option>Schueler</option>\n" +
                        "        <option>Lehrer</option>\n" +
                        "      </select>" +
                        "  <input type=\"hidden\" class=\"form-control\" name=\"type\" value=\"create\">\n" +
                        "  <input type=\"submit\" class=\"form-control\" >" +
                        "</form>" +
                        "</div>");
            } else {
                out.print("<br>Schüler :<br");
            }


            //req.get

            for (int i = EngineFactory.getInstance().getWfsEngine().safeLongToInt(DatabaseConnectorFactory.getInstance().getAccountDatabaseConnector().countAccound()); i >= 1; i--) {
                String existing = EngineFactory.getInstance().getWfsEngine().parseExisting(DatabaseConnectorFactory.getInstance().getAccountDatabaseConnector().isExisting(String.valueOf(i)));
                String parsedexisting = EngineFactory.getInstance().getWfsEngine().parseExisting(existing);

                String name = DatabaseConnectorFactory.getInstance().getAccountDatabaseConnector().selectName(String.valueOf(i));
                String rank = DatabaseConnectorFactory.getInstance().getAccountDatabaseConnector().selectRank(String.valueOf(i));


                if (parsedexisting.equals("1")) {
                    out.print("<a href=/wfs/view?id=" + String.valueOf(i) + ">( Name : " + name +  " / Rank : " + rank + "  )</a><br>");
                } else {

                }

            }

            EngineFactory.getInstance().getWebComponentEngine().getBotom(req, resp);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
