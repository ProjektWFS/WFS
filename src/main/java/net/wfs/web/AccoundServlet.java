// Author Michael Meier micmine4@gmail.com
package net.wfs.web;

import jptools.logger.Logger;
import net.wfs.web.engine.EngineFactory;
import net.wfs.web.engine.db.DatabaseConnectorFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class AccoundServlet extends HttpServlet {

    private static Logger log = Logger.getLogger(AccoundServlet.class);

    /**
     * Accounmanagment create / delete accounts
     *
     * @param request
     * @param response
     * @throws IOException
     */

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        String type = request.getParameter("type");
        String name = request.getParameter("name");
        String rank = request.getParameter("rank");
        String id = request.getParameter("id");

        if (session.getAttribute(LoginServlet.RANK_ATTRIBUTE_NAME).equals(Rank.Teacher) ||
                session.getAttribute(LoginServlet.RANK_ATTRIBUTE_NAME).equals(Rank.Admin)) {


            if (type == null) {

                out.print("Add a type");

            } else if (type.equals("create")) {
                try {

                    String idnew = String.valueOf(1 + Integer.parseInt(String.valueOf(DatabaseConnectorFactory.getInstance().getAccountDatabaseConnector().countAccound())));

                    out.print("Creating accound ...");

                    log.debug("type : " + rank);

                    DatabaseConnectorFactory.getInstance().getAccountDatabaseConnector().writeName(idnew, name);
                    DatabaseConnectorFactory.getInstance().getAccountDatabaseConnector().writePassword(idnew, "passwd");
                    DatabaseConnectorFactory.getInstance().getAccountDatabaseConnector().writeRank(idnew, EngineFactory.getInstance().getWfsEngine().deToRank(rank));


                    out.print("done");


                    log.debug("Created an accound name : " + name);

                    response.sendRedirect("list");

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else if (type.equals("delete")) {
                try {


                    out.print("Deleting accound ...");

                    out.print("(" + id + ")");

                    DatabaseConnectorFactory.getInstance().getAccountDatabaseConnector().dorpAccound(id);


                    out.print("done");


                    log.debug("Drop an accound id : " + id);

                    response.sendRedirect("list");

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                log.error("Link error : " + request.getRequestURI());

            }
        }
    }



    public static int safeLongToInt(long l) {
        int i = (int) l;
        if ((long) i != l) {
            throw new IllegalArgumentException(l + " cannot be cast to int without changing its value.");
        }
        return i;
    }
}
