package util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Yc on 2016/4/8 for students_system.
 */
public class CookieUtil {
    public static Cookie getCookie(String key, String value,int maxage, boolean isSecure){
        Cookie c = new Cookie(key,value);
        c.setPath("/");
        c.setMaxAge(maxage);
        if(isSecure) {
            c.setHttpOnly(true);
            c.setSecure(true);
        }
        return c;
    }

    public static String findCookie(String key,HttpServletRequest request){
        Cookie[] cs = request.getCookies();
        if(cs==null)    return null;
        for (Cookie c : cs){
            if(c.getName().equals(key))
                return c.getValue();
        }
        return "";
    }

}
