package util;

import org.apache.commons.codec.digest.DigestUtils;
import sun.misc.BASE64Decoder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Yc on 2016/4/8 for students_system.
 */
public class EncodeUtil {
    public static String encode(String code){
        return DigestUtils.md5Hex(code);
    }

    public static boolean equals(String raw,String done) {
        return raw.equals(done) || encode(raw).equals(done);
    }

    public static String getTypeFromBase64(String base64){
        return base64.substring(base64.indexOf("data:")+5,base64.indexOf(";base64"));
    }

    public static InputStream base64ToStream(String base64) throws IOException {
        if (base64 == null || !base64.startsWith("data:")) {
            return null;
        }
        String data = base64.split(",", 2)[1];
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] bytes = decoder.decodeBuffer(data);
        InputStream is = new ByteArrayInputStream(bytes);
        return is;
    }
}
