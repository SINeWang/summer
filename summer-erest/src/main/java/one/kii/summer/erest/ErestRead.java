package one.kii.summer.erest;


import one.kii.summer.context.exception.BadRequest;
import one.kii.summer.context.exception.NotFound;
import one.kii.summer.context.exception.Panic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

/**
 * Created by WangYanJiong on 4/7/17.
 */
public abstract class ErestRead extends ErestClient {

    private static Logger logger = LoggerFactory.getLogger(ErestRead.class);
    private HttpMethod httpMethod = HttpMethod.GET;

    protected abstract HttpHeaders getHttpHeaders();

    public <T> T execute(String urlTemplate, Class<T> klass, Object... uriVariables) throws NotFound, BadRequest, Panic {
        return executeWithHead(urlTemplate, klass, uriVariables).getBody();
    }

    public <T> ResponseEntity<T> executeWithHead(String urlTemplate, Class<T> klass, Object... uriVariables) throws NotFound, BadRequest, Panic {
        logger.debug("request: {}, execute:{}, url:{}, uriVariables:{}", requestId, httpMethod, urlTemplate, uriVariables);

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = getHttpHeaders();

        HttpEntity request = new HttpEntity<>(headers);

        try {
            return restTemplate.exchange(urlTemplate, HttpMethod.GET, request, klass, uriVariables);
        } catch (HttpStatusCodeException status) {
            handleReadException(status);
        }
        throw new Panic();
    }


    protected void handleReadException(HttpStatusCodeException status) throws NotFound, BadRequest {
        HttpStatus code = status.getStatusCode();
        HttpHeaders headers = status.getResponseHeaders();
        handleBasic(code, headers);
    }

}
