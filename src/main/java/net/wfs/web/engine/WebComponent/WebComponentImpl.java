// Author Michael Meier micmine4@gmail.com
package net.wfs.web.engine.WebComponent;


import jptools.logger.Logger;
import net.wfs.web.LoginServlet;
import net.wfs.web.Rank;
import net.wfs.web.engine.EngineFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;


/**
 * Created by michael on 5/10/17.
 */
public class WebComponentImpl implements IWebComponentEngine {

    private static Logger log = Logger.getLogger(LoginServlet.class);
    public String projekt = "wfs";


    /**
     * Print Web login Header
     *
     * @param response
     * @throws IOException
     */


    private void getHeaderLoginFast(HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        String projekt = "wfs";
        out.print("<html>");
        out.print("<head>\n" +
                "  <title>WfS</title>\n" +
                "\n" +
                "  <meta charset=\"utf-8\">\n" +
                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "  <meta name=\"theme-color\" content=\"#226922\">\n" +
                "  <meta property=\"og:title\" content=\"Web Fore school\">\n" +
                "  <meta property=\"og:image\" content=\"/resources/WFS-logo.jpg\">\n" +
                "  <meta property=\"og:description\" content=\"usersystem login\">\n" +
                "  <meta property=\"og:url\" content=\"Wfs.net\">\n" +
                "\n" +
                "  <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">\n" +
                "  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>\n" +
                "  <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>\n");
        out.print("  <link rel=\"stylesheet\" href=\"/wfs/resources/css/style-login.css\">\n");
        out.print("  <link rel=\"apple-touch-icon\" sizes=\"57x57\" href=\"/" + projekt + "/resources/favicon/apple-icon-57x57.png\">\n" +
                "  <link rel=\"apple-touch-icon\" sizes=\"60x60\" href=\"/" + projekt + "/resources/favicon/apple-icon-60x60.png\">\n" +
                "  <link rel=\"apple-touch-icon\" sizes=\"72x72\" href=\"/" + projekt + "/resources/favicon/apple-icon-72x72.png\">\n" +
                "  <link rel=\"apple-touch-icon\" sizes=\"76x76\" href=\"/" + projekt + "/resources/favicon/apple-icon-76x76.png\">\n" +
                "  <link rel=\"apple-touch-icon\" sizes=\"114x114\" href=\"/" + projekt + "/resources/favicon/apple-icon-114x114.png\">\n" +
                "  <link rel=\"apple-touch-icon\" sizes=\"120x120\" href=\"/" + projekt + "/resources/favicon/apple-icon-120x120.png\">\n" +
                "  <link rel=\"apple-touch-icon\" sizes=\"144x144\" href=\"/" + projekt + "/resources/favicon/apple-icon-144x144.png\">\n" +
                "  <link rel=\"apple-touch-icon\" sizes=\"152x152\" href=\"/" + projekt + "/resources/favicon/apple-icon-152x152.png\">\n" +
                "  <link rel=\"apple-touch-icon\" sizes=\"180x180\" href=\"/" + projekt + "/resources/favicon/apple-icon-180x180.png\">\n" +
                "  <link rel=\"icon\" type=\"image/png\" sizes=\"192x192\" href=\"/" + projekt + "/resources/android-icon-192x192.png\">\n" +
                "  <link rel=\"icon\" type=\"image/png\" sizes=\"32x32\" href=\"/" + projekt + "/resources/favicon/favicon-32x32.png\">\n" +
                "  <link rel=\"icon\" type=\"image/png\" sizes=\"96x96\" href=\"/" + projekt + "/resources/favicon/favicon-96x96.png\">\n" +
                "  <link rel=\"icon\" type=\"image/png\" sizes=\"16x16\" href=\"/" + projekt + "/resources/favicon/favicon-16x16.png\">\n" +
                "  <link rel=\"manifest\" href=\"/resources/favicon/manifest.json\">\n" +
                "  <meta name=\"msapplication-TileColor\" content=\"#ff6600\">\n" +
                "  <meta name=\"msapplication-TileImage\" content=\"/" + projekt + "/resources/favicon/ms-icon-144x144.png\">\n" +
                "</head> <body>");
    }

    private void getHeaderLoginLocal(HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        String projekt = "wfs";
        out.print("<html>");
        out.print("<head>\n" +
                "  <title>WfS</title>\n" +
                "\n" +
                "  <meta charset=\"utf-8\">\n" +
                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "  <meta name=\"theme-color\" content=\"#226922\">\n" +
                "  <meta property=\"og:title\" content=\"Web Fore school\">\n" +
                "  <meta property=\"og:image\" content=\"/resources/WFS-logo.jpg\">\n" +
                "  <meta property=\"og:description\" content=\"usersystem login\">\n" +
                "  <meta property=\"og:url\" content=\"Wfs.net\">\n" +
                "\n");
        out.print("  <script src=\"/" + projekt + "/resources/js/jquery-3.2.1.min.js\"></script>\n" +
                "  <script src=\"/" + projekt + "/resources/js/bootstrap.min.js\"></script>\n" +
                "  <script src=\"/" + projekt + "/resources/js/nav.js\"></script>\n");
        out.print("  <link rel=\"stylesheet\" href=\"/" + projekt + "/resources/css/nav.css\">\n");
        out.print("  <link rel=\"stylesheet\" href=\"/" + projekt + "/resources/css/style-login.css\">\n");
        out.print("  <link rel=\"stylesheet\" href=\"/" + projekt + "/resources/css/bootstrap.min.css\">\n");
        out.print("  <link rel=\"apple-touch-icon\" sizes=\"57x57\" href=\"/" + projekt + "/resources/favicon/apple-icon-57x57.png\">\n" +
                "  <link rel=\"apple-touch-icon\" sizes=\"60x60\" href=\"/" + projekt + "/resources/favicon/apple-icon-60x60.png\">\n" +
                "  <link rel=\"apple-touch-icon\" sizes=\"72x72\" href=\"/" + projekt + "/resources/favicon/apple-icon-72x72.png\">\n" +
                "  <link rel=\"apple-touch-icon\" sizes=\"76x76\" href=\"/" + projekt + "/resources/favicon/apple-icon-76x76.png\">\n" +
                "  <link rel=\"apple-touch-icon\" sizes=\"114x114\" href=\"/" + projekt + "/resources/favicon/apple-icon-114x114.png\">\n" +
                "  <link rel=\"apple-touch-icon\" sizes=\"120x120\" href=\"/" + projekt + "/resources/favicon/apple-icon-120x120.png\">\n" +
                "  <link rel=\"apple-touch-icon\" sizes=\"144x144\" href=\"/" + projekt + "/resources/favicon/apple-icon-144x144.png\">\n" +
                "  <link rel=\"apple-touch-icon\" sizes=\"152x152\" href=\"/" + projekt + "/resources/favicon/apple-icon-152x152.png\">\n" +
                "  <link rel=\"apple-touch-icon\" sizes=\"180x180\" href=\"/" + projekt + "/resources/favicon/apple-icon-180x180.png\">\n" +
                "  <link rel=\"icon\" type=\"image/png\" sizes=\"192x192\" href=\"/" + projekt + "/resources/android-icon-192x192.png\">\n" +
                "  <link rel=\"icon\" type=\"image/png\" sizes=\"32x32\" href=\"/" + projekt + "/resources/favicon/favicon-32x32.png\">\n" +
                "  <link rel=\"icon\" type=\"image/png\" sizes=\"96x96\" href=\"/" + projekt + "/resources/favicon/favicon-96x96.png\">\n" +
                "  <link rel=\"icon\" type=\"image/png\" sizes=\"16x16\" href=\"/" + projekt + "/resources/favicon/favicon-16x16.png\">\n" +
                "  <link rel=\"manifest\" href=\"/resources/favicon/manifest.json\">\n" +
                "  <meta name=\"msapplication-TileColor\" content=\"#ff6600\">\n" +
                "  <meta name=\"msapplication-TileImage\" content=\"/" + projekt + "/resources/favicon/ms-icon-144x144.png\">\n" +
                "</head> <body>");
    }


    private void getHeaderFast(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String rank = String.valueOf(session.getAttribute(LoginServlet.RANK_ATTRIBUTE_NAME));
        PrintWriter out = response.getWriter();
        String projekt = "wfs";
        out.print("<html>");
        out.print("<head>\n" +
                "  <title>Wfs - Web for school</title>\n" +
                "\n" +
                "  <meta charset=\"utf-8\">\n" +
                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "  <meta name=\"theme-color\" content=" + EngineFactory.getInstance().getWfsEngine().getRankColor(rank) + ">\n" +
                "  <meta property=\"og:title\" content=\"Web Fore school\">\n" +
                "  <meta property=\"og:image\" content=\"/resources/img/WFS-logo.jpg\">\n" +
                "  <meta property=\"og:description\" content=\"Wfs login\">\n" +
                "  <meta property=\"og:url\" content=\"Wfs.net\">\n" +
                "  <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">\n" +
                "  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>\n" +
                "  <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>\n" +
                "  <script src=\"/" + projekt + "/resources/js/nav.js\"></script>\n");
        out.print("  <link rel=\"stylesheet\" href=\"/" + projekt + "/resources/css/nav.css\">\n");
        out.print("  <link rel=\"stylesheet\" href=\"/" + projekt + "/resources/css/style-login.css\">\n");
        out.print("  <link rel=\"apple-touch-icon\" sizes=\"57x57\" href=\"/" + projekt + "/resources/favicon/apple-icon-57x57.png\">\n" +
                "  <link rel=\"apple-touch-icon\" sizes=\"60x60\" href=\"/" + projekt + "/resources/favicon/apple-icon-60x60.png\">\n" +
                "  <link rel=\"apple-touch-icon\" sizes=\"72x72\" href=\"/" + projekt + "/resources/favicon/apple-icon-72x72.png\">\n" +
                "  <link rel=\"apple-touch-icon\" sizes=\"76x76\" href=\"/" + projekt + "/resources/favicon/apple-icon-76x76.png\">\n" +
                "  <link rel=\"apple-touch-icon\" sizes=\"114x114\" href=\"/" + projekt + "/resources/favicon/apple-icon-114x114.png\">\n" +
                "  <link rel=\"apple-touch-icon\" sizes=\"120x120\" href=\"/" + projekt + "/resources/favicon/apple-icon-120x120.png\">\n" +
                "  <link rel=\"apple-touch-icon\" sizes=\"144x144\" href=\"/" + projekt + "/resources/favicon/apple-icon-144x144.png\">\n" +
                "  <link rel=\"apple-touch-icon\" sizes=\"152x152\" href=\"/" + projekt + "/resources/favicon/apple-icon-152x152.png\">\n" +
                "  <link rel=\"apple-touch-icon\" sizes=\"180x180\" href=\"/" + projekt + "/resources/favicon/apple-icon-180x180.png\">\n" +
                "  <link rel=\"icon\" type=\"image/png\" sizes=\"192x192\" href=\"/" + projekt + "/resources/android-icon-192x192.png\">\n" +
                "  <link rel=\"icon\" type=\"image/png\" sizes=\"32x32\" href=\"/" + projekt + "/resources/favicon/favicon-32x32.png\">\n" +
                "  <link rel=\"icon\" type=\"image/png\" sizes=\"96x96\" href=\"/" + projekt + "/resources/favicon/favicon-96x96.png\">\n" +
                "  <link rel=\"icon\" type=\"image/png\" sizes=\"16x16\" href=\"/" + projekt + "/resources/favicon/favicon-16x16.png\">\n" +
                "  <link rel=\"manifest\" href=\"/resources/favicon/manifest.json\">\n" +
                "  <meta name=\"msapplication-TileColor\" content=" + EngineFactory.getInstance().getWfsEngine().getRankColor(rank) + ">\n" +
                "  <meta name=\"msapplication-TileImage\" content=\"/" + projekt + "/resources/favicon/ms-icon-144x144.png\">\n" +
                "</head> <body>");
    }


    private void getHeaderLocal(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String rank = String.valueOf(session.getAttribute(LoginServlet.RANK_ATTRIBUTE_NAME));
        PrintWriter out = response.getWriter();
        String projekt = "wfs";
        out.print("<html>");
        out.print("<head>\n" +
                "  <title>Wfs - Web for school</title>\n" +
                "\n" +
                "  <meta charset=\"utf-8\">\n" +
                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "  <meta name=\"theme-color\" content=" + EngineFactory.getInstance().getWfsEngine().getRankColor(rank) + ">\n" +
                "  <meta property=\"og:title\" content=\"Web Fore school\">\n" +
                "  <meta property=\"og:image\" content=\"/resources/img/WFS-logo.jpg\">\n" +
                "  <meta property=\"og:description\" content=\"Wfs login\">\n" +
                "  <meta property=\"og:url\" content=\"Wfs.net\">\n");
        out.print("  <script src=\"/" + projekt + "/resources/js/jquery-3.2.1.min.js\"></script>\n" +
                "  <script src=\"/" + projekt + "/resources/js/bootstrap.min.js\"></script>\n" +
                "  <script src=\"/" + projekt + "/resources/js/nav.js\"></script>\n");
        out.print("  <link rel=\"stylesheet\" href=\"/" + projekt + "/resources/css/nav.css\">\n");
        out.print("  <link rel=\"stylesheet\" href=\"/" + projekt + "/resources/css/style-login.css\">\n");
        out.print("  <link rel=\"stylesheet\" href=\"/" + projekt + "/resources/css/bootstrap.min.css\">\n");
        out.print("  <link rel=\"apple-touch-icon\" sizes=\"57x57\" href=\"/" + projekt + "/resources/favicon/apple-icon-57x57.png\">\n" +
                "  <link rel=\"apple-touch-icon\" sizes=\"60x60\" href=\"/" + projekt + "/resources/favicon/apple-icon-60x60.png\">\n" +
                "  <link rel=\"apple-touch-icon\" sizes=\"72x72\" href=\"/" + projekt + "/resources/favicon/apple-icon-72x72.png\">\n" +
                "  <link rel=\"apple-touch-icon\" sizes=\"76x76\" href=\"/" + projekt + "/resources/favicon/apple-icon-76x76.png\">\n" +
                "  <link rel=\"apple-touch-icon\" sizes=\"114x114\" href=\"/" + projekt + "/resources/favicon/apple-icon-114x114.png\">\n" +
                "  <link rel=\"apple-touch-icon\" sizes=\"120x120\" href=\"/" + projekt + "/resources/favicon/apple-icon-120x120.png\">\n" +
                "  <link rel=\"apple-touch-icon\" sizes=\"144x144\" href=\"/" + projekt + "/resources/favicon/apple-icon-144x144.png\">\n" +
                "  <link rel=\"apple-touch-icon\" sizes=\"152x152\" href=\"/" + projekt + "/resources/favicon/apple-icon-152x152.png\">\n" +
                "  <link rel=\"apple-touch-icon\" sizes=\"180x180\" href=\"/" + projekt + "/resources/favicon/apple-icon-180x180.png\">\n" +
                "  <link rel=\"icon\" type=\"image/png\" sizes=\"192x192\" href=\"/" + projekt + "/resources/android-icon-192x192.png\">\n" +
                "  <link rel=\"icon\" type=\"image/png\" sizes=\"32x32\" href=\"/" + projekt + "/resources/favicon/favicon-32x32.png\">\n" +
                "  <link rel=\"icon\" type=\"image/png\" sizes=\"96x96\" href=\"/" + projekt + "/resources/favicon/favicon-96x96.png\">\n" +
                "  <link rel=\"icon\" type=\"image/png\" sizes=\"16x16\" href=\"/" + projekt + "/resources/favicon/favicon-16x16.png\">\n" +
                "  <link rel=\"manifest\" href=\"/resources/favicon/manifest.json\">\n" +
                "  <meta name=\"msapplication-TileColor\" content=" + EngineFactory.getInstance().getWfsEngine().getRankColor(rank) + ">\n" +
                "  <meta name=\"msapplication-TileImage\" content=\"/" + projekt + "/resources/favicon/ms-icon-144x144.png\">\n" +
                "</head> <body>");
    }

    public void getHeader(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Properties prop = new Properties();
        InputStream inputStream = LoginServlet.class.getResourceAsStream("/general.properties");
        prop.load(inputStream);

        if (prop.getProperty("header.type").equals("local")) {
            getHeaderLocal(request, response);
        } else if (prop.getProperty("header.type").equals("fast")) {
            getHeaderFast(request, response);
        } else {
            log.debug("Unknow header type set standart type");
            prop.setProperty("type", "fast");
            getHeaderFast(request, response);
        }
    }

    public void getHeaderLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Properties prop = new Properties();
        InputStream inputStream = LoginServlet.class.getResourceAsStream("/general.properties");
        prop.load(inputStream);

        if (prop.getProperty("header.type").equals("local")) {
            getHeaderLoginLocal(response);
        } else if (prop.getProperty("header.type").equals("fast")) {
            getHeaderLoginFast(response);
        } else {
            log.debug("Unknow header type set standart type");
            prop.setProperty("type", "fast");
            getHeaderFast(request, response);
        }
    }

    /**
     * Nav top
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @Override
    public void getTop(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();

        out.print(" <div class=\"jumbotron text-center\"  style=\"background-color: " + EngineFactory.getInstance().getWfsEngine().getRankColor(String.valueOf(session.getAttribute(LoginServlet.RANK_ATTRIBUTE_NAME))) + "\">\n" +
                        "        <h1 style=\"color: " + EngineFactory.getInstance().getWfsEngine().getRankColorFont(String.valueOf(session.getAttribute(LoginServlet.RANK_ATTRIBUTE_NAME))) + "\">WFS</h1>\n" +
                        "    <form action=\"logout\" method=\"get\">\n" +
                        "      <input type=\"submit\" class=\"btn btn-default\" value=\"logout\"></input>\n" +
                        "    </form>\n" +
                        "</div>"
                /*"        <div class=\"row\">\n" +
                "            <div class=\"col-sm-6 col-sm-offset-3\">\n" +
                "                <div id=\"imaginary_container\">\n" +
                "                    <form action=\"list\" method=\"get\">\n" +
                "                        <div class=\"input-group stylish-input-group\">\n" +
                "                            <input type=\"text\" class=\"form-control\" name=\"name\" placeholder=\"Search\" style=\"height: 48;\">\n" +
                "                            <span class=\"input-group-addon\">\n" +
                "                                <input type=\"submit\" name=\"submit\" value=\"Search\" class=\"btn btn-primary\"> \n" +
                "                            </span>\n" +
                "                        </div>\n" +
                "                    </form>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>\n"*/);
        out.print("<div id=\"wrapper\">\n" +
                "        <div class=\"overlay\"></div>\n" +
                "    \n" +
                "        <!-- Sidebar -->\n" +
                "        <nav class=\"navbar navbar-inverse navbar-fixed-top\" id=\"sidebar-wrapper\" role=\"navigation\">\n" +
                "            <ul class=\"nav sidebar-nav\">\n" +
                "                <li class=\"sidebar-brand\">\n" +
                "                    <a href=\"#\">\n" +
                "                       WFS\n" +
                "                    </a>\n" +
                "                </li>\n" +
                "                <li>\n" +
                "                    <a href=\"/wfs/feed\">Feed</a>\n" +
                "                </li>\n" +
                "                <li>\n" +
                "                    <a href=\"/wfs/list\">Liste</a>\n" +
                "                </li>\n");
        if (session.getAttribute(LoginServlet.RANK_ATTRIBUTE_NAME).equals(Rank.Teacher) ||
                session.getAttribute(LoginServlet.RANK_ATTRIBUTE_NAME).equals(Rank.Admin)) {
            out.print("                <li>\n" +
                    "                    <a href=\"/wfs/admin\">Admin</a>\n" +
                    "                </li>\n");
        }
        /*out.print("                <li class=\"dropdown\">\n" +
                "                  <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">Schüler<span class=\"caret\"></span></a>\n" +
                "                  <ul class=\"dropdown-menu\" role=\"menu\">\n" +
                "                    <li class=\"dropdown-header\">Dropdown heading</li>\n" +
                "                    <li><a href=\"#\">Liste</a></li>\n" +
                "                    <li><a href=\"#\"></a></li>\n" +
                "                    <li><a href=\"#\">Something else here</a></li>\n" +
                "                    <li><a href=\"#\">Separated link</a></li>\n" +
                "                    <li><a href=\"#\">One more separated link</a></li>\n" +
                "                  </ul>\n" +
                "                </li>\n"); */
        out.print("            </ul>\n" +
                "        </nav>\n" +
                "        <!-- /#sidebar-wrapper -->\n" +
                "\n" +
                "        <!-- Page Content -->\n" +
                "        <div id=\"page-content-wrapper\">\n" +
                "            <button type=\"button\" class=\"hamburger is-closed\" data-toggle=\"offcanvas\">\n" +
                "                <span class=\"hamb-top\"></span>\n" +
                "    \t\t\t<span class=\"hamb-middle\"></span>\n" +
                "\t\t\t\t<span class=\"hamb-bottom\"></span>\n" +
                "            </button>\n" +
                "            <div class=\"container\">\n" +
                "                <div class=\"row\">\n");

        out.print("</div>");
    }

    @Override
    public void getBotom(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.print("                </div>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <!-- /#page-content-wrapper -->\n" +
                "\n" +
                "    </div>\n" +
                "    <!-- /#wrapper -->");
    }


}






