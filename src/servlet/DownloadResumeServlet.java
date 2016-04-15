package servlet;

import model.User;
import model.UserInfo;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Yc on 2016/4/9 for students_system.
 */
@WebServlet(name = "DownloadResumeServlet",urlPatterns = {"/downloadResume"})
public class DownloadResumeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        User me = (User) request.getSession().getAttribute("username");
        if(me==null) return;
        else{
            OutputStream os = res.getOutputStream();
            UserInfo userInfo = new UserInfo();
            userInfo.setUsername(me.getUsername());
            if(userInfo.selectResume()){
                res.setContentType(userInfo.getResume_type());
                byte[] buf = new byte[1024*1024];
                int v = -1;
                while ((v=userInfo.getResume().read(buf))!=-1){
                    os.write(buf,0,v);
                }
                os.flush();
                os.close();
            }else{
                return;
            }
        }
    }
}
