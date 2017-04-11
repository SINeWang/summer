package one.kii.summer.erest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * Created by WangYanJiong on 4/7/17.
 */
public abstract class ErestRead extends ErestClient {

    private static Logger logger = LoggerFactory.getLogger(ErestRead.class);
    private HttpMethod httpMethod = HttpMethod.GET;

    protected abstract HttpHeaders getHttpHeaders();

    public <T> T execute(String urlTemplate, Class<T> klass, Object... uriVariables) {
        logger.debug("request: {}, execute:{}, url:{}, uriVariables:{}", requestId, httpMethod, urlTemplate, uriVariables);

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = getHttpHeaders();

        HttpEntity request = new HttpEntity<>(headers);

        ResponseEntity<T> response = restTemplate.exchange(urlTemplate, HttpMethod.GET, request, klass, uriVariables);
        return response.getBody();

    }

}
