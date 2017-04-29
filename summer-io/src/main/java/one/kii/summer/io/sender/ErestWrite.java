package one.kii.summer.io.sender;

import one.kii.summer.io.exception.*;
import one.kii.summer.io.utils.MultiValueForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;


/**
 * Created by WangYanJiong on 11/04/2017.
 */
public abstract class ErestWrite extends ErestWriteWithoutRequestBody {

    private static Logger logger = LoggerFactory.getLogger(ErestWrite.class);

    public ErestWrite(String operatorId) {
        super(operatorId);
    }

    public <T> T execute(String urlTemplate, Object form, Class<T> klass, Object... uriVariables) throws Panic, BadRequest, Conflict, NotFound, Forbidden {
        MultiValueMap<String, String> bodyMap = MultiValueForm.from(form);
        return execute(urlTemplate, bodyMap, klass, uriVariables);
    }

    public <T> T execute(String urlTemplate, MultiValueMap<String, String> bodyMap, Class<T> klass, Object... uriVariables) throws Panic, BadRequest, Conflict, NotFound, Forbidden {
        return executeWithHead(urlTemplate, bodyMap, klass, uriVariables).getBody();
    }

    public <T> ResponseEntity<T> executeWithHead(String urlTemplate, MultiValueMap<String, String> bodyMap, Class<T> klass, Object... uriVariables) throws Panic, NotFound, BadRequest, Conflict, Forbidden {
        logger.debug("request: {}, execute:{}, url:{}, uriVariables:{}", requestId, httpMethod, urlTemplate, uriVariables);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = getHttpHeaders();
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(bodyMap, headers);
        try {
            return restTemplate.exchange(urlTemplate, httpMethod, request, klass, uriVariables);
        } catch (HttpStatusCodeException status) {
            handleWriteException(status);
        }
        throw new Panic();
    }

    public <T> ResponseEntity<T> executeWithHead(String urlTemplate, Object form, Class<T> klass, Object... uriVariables) throws Conflict, Panic, NotFound, BadRequest, Forbidden {
        logger.debug("request: {}, execute:{}, url:{}, uriVariables:{}", requestId, httpMethod, urlTemplate, uriVariables);

        MultiValueMap<String, String> bodyMap = MultiValueForm.from(form);
        return executeWithHead(urlTemplate, bodyMap, klass, uriVariables);
    }


}
