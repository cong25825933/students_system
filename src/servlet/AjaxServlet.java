package servlet;

import model.User;
import model.UserInfo;
import net.sf.json.JSONObject;
import util.CookieUtil;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * Created by Yc on 2016/4/8 for students_system.
 */
@WebServlet(name = "AjaxServlet",urlPatterns = {"/ajaxDo"})
public class AjaxServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        String act = request.getParameter("act");
        switch (act){
            case "login":{
                String user = request.getParameter("user");
                String pwd = request.getParameter("pwd");
                boolean rem = Boolean.parseBoolean(request.getParameter("rem"));
                JSONObject json = new JSONObject();
                if(user.isEmpty()||pwd.isEmpty()){
                    json.put("status",-1);
                    json.put("msg","用户/密码为空");
                    response.getWriter().print(json);
                    return;
                }
                User u;
                if((u=new User(user,pwd)).check()){
                    json.put("status",1);
                    response.getWriter().print(json);
                    request.getSession().setAttribute("username",u);
                    if(rem){
                        response.addCookie(CookieUtil.getCookie("NAME", URLEncoder.encode(user, "utf-8"), Integer.MAX_VALUE,false));
                        response.addCookie(CookieUtil.getCookie("PWD", pwd,Integer.MAX_VALUE,false));
                    }else{
                        response.addCookie(CookieUtil.getCookie("NAME", "",0,false));
                        response.addCookie(CookieUtil.getCookie("PWD", "",0,false));
                    }
                }else{
                    json.put("status",-1);
                    json.put("msg","用户名/密码不正确");
                    response.getWriter().print(json);
                }
                break;
            }
            case "reg":{
                String user = request.getParameter("user");
                String pwd = request.getParameter("pwd");
                JSONObject json = new JSONObject();
                User u;
                if(user.isEmpty()||pwd.isEmpty()){
                    json.put("status",-1);
                    json.put("msg","用户/密码为空");
                    response.getWriter().print(json);
                    return;
                }
                if((u=new User(user,pwd)).exists()){
                    json.put("status",-1);
                    json.put("msg","用户已经存在");
                    response.getWriter().print(json);
                }else{
                    json.put("status",1);
                    json.put("msg", "注册成功");
                    response.getWriter().print(json);
                    u.insert();
                }
                break;
            }
            case "exit":{
                request.getSession().removeAttribute("username");
                break;
            }
            case "import":{
                String id = request.getParameter("id");
                String name = request.getParameter("name");
                String sex = request.getParameter("sex");
                String birth = request.getParameter("birth");
                User me = (User) request.getSession().getAttribute("username");
                UserInfo userInfo = new UserInfo(id,name,sex,birth,me.getUsername());
                boolean f = userInfo.insertBaseInfo();
                JSONObject json = new JSONObject();
                json.put("status",f?1:-1);
                json.put("msg", f ? "成功录入" : "录入失败，可能是学号已经存在");
                res.getWriter().print(json);
                break;
            }
            case "clear":{
                User me = (User) request.getSession().getAttribute("username");
                UserInfo userInfo = new UserInfo();
                userInfo.setUsername(me.getUsername());
                boolean f = userInfo.remove();
                JSONObject json = new JSONObject();
                json.put("status",f?1:-1);
                json.put("msg", f ? "删除成功" : "删除失败，可能你未录入信息");
                res.getWriter().print(json);
                break;
            }
        }
    }
}
