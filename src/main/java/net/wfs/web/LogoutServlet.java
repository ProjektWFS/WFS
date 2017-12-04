// Author Michael Meier micmine4@gmail.com
package net.wfs.web;

import net.wfs.web.filter.LoginFilter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {


    /**
     * let You log out
     * @param request
     * @param response
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        request.setAttribute(LoginServlet.RANK_ATTRIBUTE_NAME, null);
        request.setAttribute(LoginServlet.USER_ATTRIBUTE_ID, null);
        request.getSession().invalidate();

        response.sendRedirect(request.getContextPath() + LoginFilter.LOGIN_PATH);
    }
}
