package one.kii.summer.codec.utils;

import org.apache.commons.codec.binary.Hex;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by WangYanJiong on 02/04/2017.
 */
public class HashTools {


    public static String hashHex(String... messages) {
        byte[] digest;
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            //ignore
        }
        for (String message : messages) {
            if (StringUtils.isEmpty(message)) {
                continue;
            }
            try {
                byte[] data = message.getBytes("UTF-8");
                md.update(data);
            } catch (UnsupportedEncodingException e) {
                //ignore;
            }
        }
        digest = md.digest();
        return Hex.encodeHexString(digest);
    }

}