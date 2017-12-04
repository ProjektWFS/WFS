// Author Michael Meier micmine4@gmail.com
package net.wfs.web.engine.WebComponent;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by michael on 5/10/17.
 */
public interface IWebComponentEngine {


    void getHeader(HttpServletRequest request, HttpServletResponse response) throws IOException;

    void getHeaderLogin(HttpServletRequest request, HttpServletResponse response) throws IOException;



    void getTop(HttpServletRequest request, HttpServletResponse response) throws IOException;

    void getBotom(HttpServletRequest request, HttpServletResponse response) throws IOException;






}
