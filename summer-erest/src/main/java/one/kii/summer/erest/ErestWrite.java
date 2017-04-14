package one.kii.summer.erest;

import one.kii.summer.context.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static one.kii.summer.erest.ErestHeaders.CONFLICT_KEY;
import static one.kii.summer.erest.ErestHeaders.FORBIDDEN_KEY;

/**
 * Created by WangYanJiong on 11/04/2017.
 */
public abstract class ErestWrite extends ErestClient {

    private static Logger logger = LoggerFactory.getLogger(ErestWrite.class);

    protected HttpMethod httpMethod;

    protected String operatorId;

    public ErestWrite(String operatorId) {
        this.operatorId = operatorId;
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


    protected void handleWriteException(HttpStatusCodeException status) throws NotFound, BadRequest, Conflict, Panic, Forbidden {
        HttpStatus code = status.getStatusCode();
        HttpHeaders headers = status.getResponseHeaders();
        handleBasic(code, headers);
        switch (code) {
            case CONFLICT:
                String key409 = headers.get(CONFLICT_KEY).get(0);
                throw new Conflict(key409);
            case FORBIDDEN:
                String key403 = headers.get(FORBIDDEN_KEY).get(0);
                throw new Forbidden(key403);
        }
        throw new Panic();
    }

    public <T> T execute(String urlTemplate, Object form, Class<T> klass, Object... uriVariables) throws Forbidden, Conflict, NotFound, Panic, BadRequest {
        return executeWithHead(urlTemplate, form, klass, uriVariables).getBody();
    }

    public <T> ResponseEntity<T> executeWithHead(String urlTemplate, Object form, Class<T> klass, Object... uriVariables) throws Conflict, Panic, NotFound, BadRequest, Forbidden {
        logger.debug("request: {}, execute:{}, url:{}, uriVariables:{}", requestId, httpMethod, urlTemplate, uriVariables);

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
        return executeWithHead(urlTemplate, map, klass, uriVariables);
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

    protected HttpHeaders getHttpHeaders() {
        HttpHeaders headers = buildHttpHeaders();
        if (operatorId != null) {
            headers.set("X-SUMMER-OperatorId", operatorId);
        }
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);

        return headers;
    }


}
