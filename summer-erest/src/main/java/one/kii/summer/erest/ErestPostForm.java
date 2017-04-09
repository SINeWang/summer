package one.kii.summer.erest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Created by WangYanJiong on 4/7/17.
 */
public class ErestPostForm {

    private static Logger logger = LoggerFactory.getLogger(ErestPostForm.class);

    public <T> T doPost(String urlTemplate, HttpHeaders headers, MultiValueMap<String, String> bodyMap, Class<T> klass, Object... uriVariables) {
        logger.debug("forAny:{}, return:{}", urlTemplate, klass.getSimpleName());

        headers.set("X-SUMMER-RequestId", UUID.randomUUID().toString());
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);


        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(bodyMap, headers);


        ResponseEntity<T> response = restTemplate.exchange(urlTemplate, HttpMethod.POST, request, klass, uriVariables);
        return response.getBody();
    }


    public <T> T doPost(String urlTemplate, HttpHeaders headers, Object form, Class<T> klass, Object... uriVariables) {

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();

        Field[] fields = form.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(form);
                map.put(field.getName(), toList(value));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return doPost(urlTemplate, headers, map, klass, uriVariables);
    }

    private List<String> toList(Object object) {
        if (object == null) {
            return Collections.emptyList();
        }
        List<String> list = new ArrayList<>();
        if (!object.getClass().isArray()) {
            list.add(object.toString());
            return list;
        } else {
            Object[] objects = (Object[]) object;
            for (Object value : objects) {
                if (value != null) {
                    list.add(value.toString());
                }
            }
            return list;
        }
    }
}