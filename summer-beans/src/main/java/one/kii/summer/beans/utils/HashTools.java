package one.kii.summer.beans.utils;

import one.kii.summer.beans.annotations.KeyFactor;
import org.apache.commons.codec.binary.Hex;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by WangYanJiong on 02/04/2017.
 */
public class HashTools {

    public static final String NULL_HEX = "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855";


    public static String hashHex(String... messages) {
        if (messages == null) {
            return NULL_HEX;
        }
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

    public static String hashHex(Object object) {
        if (object == null) {
            return NULL_HEX;
        }
        Class klass = object.getClass();
        Field[] fields = klass.getDeclaredFields();

        List<String> hashFields = new ArrayList<>();
        for (Field field : fields) {
            Annotation annotation = field.getAnnotation(KeyFactor.class);
            if (annotation != null) {
                hashFields.add(field.getName());
            }
        }
        if (hashFields.isEmpty()) {
            return NULL_HEX;
        }
        Collections.sort(hashFields);
        String[] values = new String[hashFields.size()];
        for (int i = 0; i < values.length; i++) {
            Field field = null;
            try {
                field = klass.getDeclaredField(hashFields.get(i));
                field.setAccessible(true);
            } catch (NoSuchFieldException e) {
                //ignore
            }
            if (field == null) {
                continue;
            }
            try {
                Object v = field.get(object);
                if (v == null) {
                    continue;
                }
                String value = String.valueOf(v);
                if (value.length() == 0) {
                    continue;
                }
                values[i] = value;
            } catch (IllegalAccessException e) {
                // ignore
            }
        }
        return hashHex(values);
    }
}
