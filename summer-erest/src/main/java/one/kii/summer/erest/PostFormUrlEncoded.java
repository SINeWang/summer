package one.kii.summer.erest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

/**
 * Created by WangYanJiong on 4/7/17.
 */
public class PostFormUrlEncoded {

    private static Logger logger = LoggerFactory.getLogger(GetWithOAuth2AccessToken.class);

    private String bearerAccessToken;

    public PostFormUrlEncoded(String bearerAccessToken) {
        this.bearerAccessToken = bearerAccessToken;
    }

    public <T> T post(String urlTemplate, MultiValueMap<String, String> bodyMap, Class<T> klass, Object... uriVariables) {
        logger.debug("forAny:{}, return:{}", urlTemplate, klass.getSimpleName());
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-SUMMER-RequestId", UUID.randomUUID().toString());
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);

        HttpEntity<?> httpEntity = new HttpEntity<Object>(headers);
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(bodyMap, headers);

        Class type = new Type(klass).getType();

        ResponseEntity<T> response = restTemplate.exchange(urlTemplate, HttpMethod.POST, request, type, uriVariables);
        return response.getBody();

    }

    private class Type {

        private Class type;

        private Type(Class type) {
            this.type = type;
        }

        Class getType() {
            return type;
        }
    }
}
