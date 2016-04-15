package servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Created by Yc on 2016/4/10 for students_system.
 */
@WebServlet(name = "AsyncServlet",urlPatterns = {"/async"},asyncSupported = true)
public class AsyncServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        PrintWriter out = res.getWriter();
        AsyncContext ac = request.startAsync();
        ac.setTimeout(-1);
        res.setContentType("text/html;charset=utf-8");
        out.print("begin</br>");
        final int[] i = {1};
        ac.start(new Runnable() {
            @Override
            public void run() {
                new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            ac.getResponse().getWriter().print(new Date() + "<br>");
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        try {
                            ac.getResponse().getWriter().flush();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        if(i[0]++>100)
                            ac.complete();
                    }
                }).start();
                //Thread.sleep(10*1000);
                //ac.dispatch("/async.jsp");

            }
        });
        out.flush();
    }
}
