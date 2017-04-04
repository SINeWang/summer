package one.kii.summer.erest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * Created by WangYanJiong on 02/04/2017.
 */
public class GetWithOAuth2AccessToken {

    private static Logger logger = LoggerFactory.getLogger(GetWithOAuth2AccessToken.class);

    private String bearerAccessToken;

    public GetWithOAuth2AccessToken(String bearerAccessToken) {
        this.bearerAccessToken = bearerAccessToken;
    }

    public <T> T getAny(String urlTemplate, Class<T> klass, Object... uriVariables) {
        logger.debug("getAny:{}, return:{}", urlTemplate, klass.getSimpleName());
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + bearerAccessToken);

        HttpEntity<?> httpEntity = new HttpEntity<Object>(headers);
        RestTemplate restTemplate = new RestTemplate();

        Class type = new Type(klass).getType();

        ResponseEntity<T> response = restTemplate.exchange(urlTemplate, HttpMethod.GET, httpEntity, type, uriVariables);
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
