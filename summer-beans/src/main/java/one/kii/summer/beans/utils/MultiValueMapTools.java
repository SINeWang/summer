package one.kii.summer.beans.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WangYanJiong on 12/05/2017.
 */
public class MultiValueMapTools {

    private static MultiValueMap EMPTY_MAP = new LinkedMultiValueMap();

    public static MultiValueMap<String, String> from(String json) {
        if (StringUtils.isEmpty(json)) {
            return EMPTY_MAP;
        }
        Map<String, String> map = Collections.emptyMap();
        try {
            map = new ObjectMapper().readValue(json, HashMap.class);
        } catch (IOException e) {
            // ignore
        }
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        for (String key : map.keySet()) {
            Object value = map.get(key);
            if (value == null) {
                multiValueMap.put(key, null);
                continue;
            }
            if (value instanceof List) {
                List values = (List) value;
                for (Object v : values) {
                    if (v instanceof String) {
                        multiValueMap.add(key, (String) v);
                    }
                    if (v.getClass().isPrimitive()) {
                        multiValueMap.add(key, String.valueOf(v));
                    } else {
                        // ignore
                    }
                }
            } else {
                multiValueMap.set(key, String.valueOf(map.get(key)));
            }
        }
        return multiValueMap;
    }
}
