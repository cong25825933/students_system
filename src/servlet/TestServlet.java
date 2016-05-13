package servlet;

import org.apache.jasper.runtime.JspRuntimeLibrary;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Yc on 2016/5/9 for students_system.
 */
@WebServlet(name = "TestServlet",urlPatterns = {"/test"})
public class TestServlet extends HttpServlet {
    int v = 0;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().print(v+++"<br>");
        PrintWriter out = response.getWriter();
        JspRuntimeLibrary.include(request,response,"/include.txt",false);
    }
}
