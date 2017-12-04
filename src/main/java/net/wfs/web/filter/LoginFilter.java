// Author Michael Meier micmine4@gmail.com
package net.wfs.web.filter;

import jptools.logger.Logger;
import net.wfs.web.LoginServlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoginFilter implements Filter {
    private static Logger log = Logger.getLogger(LoginFilter.class);


    public static final String LOGIN_PATH = "/login";
    public static final String RESOURCES_PATH = "/resources";
    public static final String admin = "/admin";



    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }


    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        Properties prop = new Properties();
        InputStream inputStream = LoginServlet.class.getResourceAsStream("/general.properties");
        prop.load(inputStream);

        String loginURI = request.getContextPath() + LOGIN_PATH;
        String resourcesURI = request.getContextPath() + RESOURCES_PATH + "/";
        String adminURI = request.getContextPath() + admin;

        boolean loginRequest = request.getRequestURI().equals(loginURI);
        boolean resourceRequest = request.getRequestURI().startsWith(resourcesURI);
        boolean adminRequest = request.getRequestURI().equals(adminURI);

        // get session
        HttpSession session = request.getSession(false);

        //log.info("request : " + request.getRequestURI());

        boolean loggedIn = session != null && session.getAttribute(LoginServlet.RANK_ATTRIBUTE_NAME) != null;
        if (loggedIn || loginRequest || resourceRequest) {
            log.debug("Allow request to " + request.getRequestURI());
            chain.doFilter(request, response); /*
        } else if (adminRequest && prop.getProperty("admin").equals("true")) {
            chain.doFilter(request, response); */
        } else {
            response.sendRedirect(loginURI);
        }
    }

    @Override
    public void destroy() {

    }


}