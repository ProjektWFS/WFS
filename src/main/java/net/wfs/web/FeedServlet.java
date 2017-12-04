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

public class FeedServlet extends HttpServlet {
    private static Logger log = Logger.getLogger(FeedServlet.class);


    /*
    erste anzeige feler
     */

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            try {
                String id = req.getParameter("id");
                PrintWriter out = resp.getWriter();
                HttpSession session = req.getSession();
                if (id == null) {


                    EngineFactory.getInstance().getWebComponentEngine().getHeader(req, resp);
                    EngineFactory.getInstance().getWebComponentEngine().getTop(req, resp);


                    if (session.getAttribute(LoginServlet.RANK_ATTRIBUTE_NAME).equals(Rank.Teacher) ||
                            session.getAttribute(LoginServlet.RANK_ATTRIBUTE_NAME).equals(Rank.Admin)) {

                        //out.print("write form to doget Feedservlet");


                        out.print("<div class=\"center-block\">" +
                                "<form action=\"/wfs/feed\" id=\"usrform\" method=\"post\">\n" +
                                "<input type=\"text\" name=\"title\" placeholder=\"title\">\n" +
                                "<input type=\"hidden\" name=\"type\" value=\"create\">" +
                                "  <input type=\"submit\">\n" +
                                "</form>\n" +
                                "\n" +
                                "<textarea name=\"text\" form=\"usrform\" rows=\"5\" cols=\"50\"placeholder=\"Text hier eingeben. Sie k&ouml;nnen das textfelt gr&ouml;sser ziehen\"></textarea>" +
                                "</div>");
                    }
                    out.print("<div class=\"container\">\n" +
                            "    <div id=\"blog\" class=\"row\">\n");


                    for (int i = EngineFactory.getInstance().getWfsEngine().safeLongToInt(DatabaseConnectorFactory.getInstance().getFeedDatabaseConnector().countFeed()); i >= 1; i--) {
                        String existing = EngineFactory.getInstance().getWfsEngine().parseExisting(DatabaseConnectorFactory.getInstance().getFeedDatabaseConnector().isExisting(String.valueOf(i)));
                        String parsedexisting = EngineFactory.getInstance().getWfsEngine().parseExisting(existing);

                        if (parsedexisting.equals("1")) {
                            String writer = DatabaseConnectorFactory.getInstance().getAccountDatabaseConnector().selectName(DatabaseConnectorFactory.getInstance().getFeedDatabaseConnector().selectWriter(String.valueOf(i)));
                            String views = DatabaseConnectorFactory.getInstance().getFeedDatabaseConnector().selectViews(String.valueOf(i));
                            String text = DatabaseConnectorFactory.getInstance().getFeedDatabaseConnector().selectText(String.valueOf(i));
                            String title = DatabaseConnectorFactory.getInstance().getFeedDatabaseConnector().selectTitle(String.valueOf(i));
                            String eventid = DatabaseConnectorFactory.getInstance().getFeedDatabaseConnector().selectEventid(String.valueOf(i));





                    /*
                    out.print("------------------------------");
                    out.print("writer : " + writer);
                    out.print("views : " + views);
                    out.print("text : " + text);
                    out.print("title : " + title);
                    out.print("writer : " + eventid);
                    out.print("------------------------------");*/
                            out.print("<div class=\"col-md-10 blogShort\">\n" +
                                    "        <h1> " + title + " von  " + writer + "</h1>\n" +
                                    //"        <img src=\"http://www.kaczmarek-photo.com/wp-content/uploads/2012/06/guinnes-150x150.jpg\" alt=\"post img\" class=\"pull-left img-responsive thumb margin10 img-thumbnail\">\n" +
                                    "\n" +
                                    //  "        <em>This snippet use <a href=\"http://bootsnipp.com/snippets/featured/sexy-sidebar-navigation\" target=\"_blank\">Sexy Sidebar Navigation</a></em>\n" +
                                    "        <article>\n" +
                                    "          <p>\n" + text +
                                    "          </p>\n" +
                                    "        </article>\n" +
                                    "        <a class=\"btn btn-blog pull-right marginBottom10\" href=\"" + req.getRequestURL().toString() + "?id=" + i + "\">Zum beitrag</a>\n" +
                                    "      </div>");
                        } else {

                        }
                    }


                    out.print("<div class=\"col-md-12 gap10\"></div>\n" +
                            "    </div>\n" +
                            "  </div>");

                    EngineFactory.getInstance().getWebComponentEngine().getBotom(req, resp);
                } else {
                    String writer = DatabaseConnectorFactory.getInstance().getAccountDatabaseConnector().selectName(DatabaseConnectorFactory.getInstance().getFeedDatabaseConnector().selectWriter(String.valueOf(id)));
                    String views = DatabaseConnectorFactory.getInstance().getFeedDatabaseConnector().selectViews(String.valueOf(id));
                    String text = DatabaseConnectorFactory.getInstance().getFeedDatabaseConnector().selectText(String.valueOf(id));
                    String title = DatabaseConnectorFactory.getInstance().getFeedDatabaseConnector().selectTitle(String.valueOf(id));
                    String eventid = DatabaseConnectorFactory.getInstance().getFeedDatabaseConnector().selectEventid(String.valueOf(id));
                    EngineFactory.getInstance().getWebComponentEngine().getHeader(req, resp);
                    EngineFactory.getInstance().getWebComponentEngine().getTop(req, resp);
                    out.print("<div class=\"container\">\n" +
                            "    <div id=\"blog\" class=\"row\">\n" +
                            "      <div class=\"col-sm-2 paddingTop20\">\n" +
                            "      </div>" +
                            "        </div>\n");
                    out.print("<div class=\"col-md-10 blogShort\">\n" +
                            "        <h1> " + title + " von  " + writer + " views : " + views + "</h1>\n" +
                            // "        <img src=\"http://www.kaczmarek-photo.com/wp-content/uploads/2012/06/guinnes-150x150.jpg\" alt=\"post img\" class=\"pull-left img-responsive thumb margin10 img-thumbnail\">\n" +
                            "\n" +
                            // "        <em>This snippet use <a href=\"http://bootsnipp.com/snippets/featured/sexy-sidebar-navigation\" target=\"_blank\">Sexy Sidebar Navigation</a></em>\n" +
                            "        <article>\n" +
                            "          <p>\n" + text +
                            "          </p>\n" +
                            "        </article>\n" +
                            "      </div>");
                    if (session.getAttribute(LoginServlet.RANK_ATTRIBUTE_NAME).equals(Rank.Teacher) ||
                            session.getAttribute(LoginServlet.RANK_ATTRIBUTE_NAME).equals(Rank.Admin)) {
                        out.print("<div class=\"center-block\">" +
                                "<form action=\"/wfs/feed\"  method=\"post\">\n" +
                                "<input type=\"hidden\" name=\"id\" value=" + id + ">" +
                                "<input type=\"hidden\" name=\"type\" value=\"delete\">" +
                                "  <input type=\"submit\" name=\"delete\">\n" +
                                "</form>\n" +
                                "\n" +
                                "</div>");
                    } else {

                    }

                    out.print("<div class=\"col-md-12 gap10\"></div>\n" +
                            "    </div>\n" +
                            "  </div>");
                    EngineFactory.getInstance().getWebComponentEngine().getBotom(req, resp);

                    if (views.isEmpty()) {
                        DatabaseConnectorFactory.getInstance().getFeedDatabaseConnector().writeViews(id, "0");
                    }

                    DatabaseConnectorFactory.getInstance().getFeedDatabaseConnector().writeViews(id, String.valueOf(1 + Integer.parseInt(views)));
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if (session.getAttribute(LoginServlet.RANK_ATTRIBUTE_NAME).equals(Rank.Teacher) ||
                session.getAttribute(LoginServlet.RANK_ATTRIBUTE_NAME).equals(Rank.Admin)) {
            PrintWriter out = resp.getWriter();
            String type = req.getParameter("type");

            if (type.equals("create")) {
                try {
                    String title = req.getParameter("title");
                    String text = req.getParameter("text");
                    String writer = String.valueOf(session.getAttribute(LoginServlet.USER_ATTRIBUTE_ID));
                    String id = String.valueOf(1 + Integer.parseInt(String.valueOf(DatabaseConnectorFactory.getInstance().getFeedDatabaseConnector().countFeed())));

                    out.print("Creating feed ...");
                    DatabaseConnectorFactory.getInstance().getFeedDatabaseConnector().writeTitle(id, title);
                    DatabaseConnectorFactory.getInstance().getFeedDatabaseConnector().writeText(id, text);
                    DatabaseConnectorFactory.getInstance().getFeedDatabaseConnector().writeWriter(id, writer);

                    log.debug("Creating Feed : " + title);
                    out.print("finish");
                    resp.sendRedirect("/wfs/feed");


                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (type.equals("delete")) {
                String id = req.getParameter("id");

                try {
                    out.print("Deleting ... ");
                    DatabaseConnectorFactory.getInstance().getFeedDatabaseConnector().dorpFeed(id);
                    log.debug("Deleted Feed with id " + id);
                    out.print("Finish");
                    resp.sendRedirect("/wfs/feed");
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

        }
    }
}
