package util;

import java.io.UnsupportedEncodingException;

/**
 * Created by Yc on 2016/4/8 for students_system.
 */
public class StringUtil {
    public static String stringConvert(String s) throws UnsupportedEncodingException {
        return new String(new String(s.getBytes("ISO-8859-1"), "utf-8"));
    }
}
