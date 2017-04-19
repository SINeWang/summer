package one.kii.summer.io.sender;

import one.kii.summer.io.context.ErestHeaders;
import one.kii.summer.io.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by WangYanJiong on 19/04/2017.
 */
public abstract class ErestWriteWithoutRequestBody extends ErestClient {

    private static Logger logger = LoggerFactory.getLogger(ErestWriteWithoutRequestBody.class);
    protected String operatorId;


    public ErestWriteWithoutRequestBody(String operatorId) {
        this.operatorId = operatorId;
    }

    protected HttpHeaders getHttpHeaders() {
        HttpHeaders headers = buildHttpHeaders();
        if (operatorId != null) {
            headers.set("X-SUMMER-OperatorId", operatorId);
        }
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);

        return headers;
    }

    public <T> ResponseEntity<T> executeWithHead(String urlTemplate, Class<T> klass, Object... uriVariables) throws Panic, NotFound, BadRequest, Conflict, Forbidden {
        logger.debug("request: {}, execute:{}, url:{}, uriVariables:{}", requestId, httpMethod, urlTemplate, uriVariables);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = getHttpHeaders();
        HttpEntity request = new HttpEntity<>(headers);
        try {
            return restTemplate.exchange(urlTemplate, httpMethod, request, klass, uriVariables);
        } catch (HttpStatusCodeException status) {
            handleWriteException(status);
        }
        throw new Panic();
    }

    public <T> T execute(String urlTemplate, Class<T> klass, Object... uriVariables) throws Panic, NotFound, BadRequest, Conflict, Forbidden {
        logger.debug("request: {}, execute:{}, url:{}, uriVariables:{}", requestId, httpMethod, urlTemplate, uriVariables);
        return executeWithHead(urlTemplate, klass, uriVariables).getBody();
    }


    protected List<String> toList(Object object) {
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

    protected void handleWriteException(HttpStatusCodeException status) throws NotFound, BadRequest, Conflict, Panic, Forbidden {
        HttpStatus code = status.getStatusCode();
        HttpHeaders headers = status.getResponseHeaders();
        handleBasic(code, headers);
        switch (code) {
            case CONFLICT:
                List<String> key409 = headers.get(ErestHeaders.CONFLICT_KEY);
                throw new Conflict(key409.toArray(new String[0]));
            case FORBIDDEN:
                List<String> key403 = headers.get(ErestHeaders.FORBIDDEN_KEY);
                throw new Forbidden(key403.toArray(new String[0]));
        }
        throw new Panic();
    }

}
