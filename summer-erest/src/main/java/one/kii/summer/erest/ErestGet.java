package one.kii.summer.erest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

/**
 * Created by WangYanJiong on 4/7/17.
 */
public class ErestGet {

    private static Logger logger = LoggerFactory.getLogger(ErestGet.class);

    public <T> T doGet(String urlTemplate, HttpHeaders headers, Class<T> klass, Object... uriVariables) {
        logger.debug("doGet:{}, return:{}", urlTemplate, klass.getSimpleName());

        headers.set("X-SUMMER-RequestId", UUID.randomUUID().toString());

        RestTemplate restTemplate = new RestTemplate();

        HttpEntity request = new HttpEntity<>(headers);

        ResponseEntity<T> response = restTemplate.exchange(urlTemplate, HttpMethod.GET, request, klass, uriVariables);
        return response.getBody();

    }

}
