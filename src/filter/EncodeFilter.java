package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Created by Yc on 2016/4/8 for students_system.
 */
@WebFilter(filterName = "EncodeFilter",urlPatterns = {"/*"},asyncSupported = true)
public class EncodeFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");//设置请求的编码格式
        resp.setCharacterEncoding("utf-8");
        //req.startAsync(req,resp);
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
