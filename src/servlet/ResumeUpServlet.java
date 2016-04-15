package servlet;

import model.User;
import model.UserInfo;
import net.sf.json.JSONObject;
import util.EncodeUtil;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Yc on 2016/4/9 for students_system.
 */
@WebServlet(name = "ResumeUpServlet",urlPatterns = {"/resumeUp"})
public class ResumeUpServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        User me = (User) request.getSession().getAttribute("username");
        InputStream is = request.getInputStream();
        byte[] bytes = new byte[1024*1024];
        int v = -1;
        StringBuffer sb = new StringBuffer();
        while ((v=is.read(bytes))!=-1){
            sb.append(new String(bytes,0,v));
        }
        if(sb.length()<=4194304) {
            String base64 = sb.toString();
            String type = EncodeUtil.getTypeFromBase64(base64);
            is = EncodeUtil.base64ToStream(base64);
            UserInfo info = new UserInfo();
            info.setResume(is);
            info.setResume_type(type);
            info.setUsername(me.getUsername());
            JSONObject json = new JSONObject();
            boolean f = info.insertResume();
            json.put("status", f ? 1 : -1);
            json.put("msg", f ? "上传成功" : "上传失败");
            res.getWriter().print(json);
        }else{
            JSONObject json = new JSONObject();
            json.put("status",-1);
            json.put("msg", "上传失败，文件过大");
            res.getWriter().print(json);
        }
    }
}
