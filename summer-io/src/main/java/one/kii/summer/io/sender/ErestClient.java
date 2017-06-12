package one.kii.summer.io.sender;

import one.kii.summer.io.context.ErestHeaders;
import one.kii.summer.io.exception.BadRequest;
import one.kii.summer.io.exception.NotFound;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.UUID;

/**
 * Created by WangYanJiong on 11/04/2017.
 */
public abstract class ErestClient {

    protected String requestId = UUID.randomUUID().toString();

    protected HttpMethod httpMethod;

    protected HttpHeaders buildHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(ErestHeaders.REQUEST_ID, requestId);
        return headers;
    }

    protected void handleBasic(HttpStatus code, HttpHeaders headers) throws NotFound, BadRequest {
        switch (code) {
            case NOT_FOUND:
                List<String> keys404 = headers.get(ErestHeaders.NOT_FOUND_KEY);
                MultiValueMap<String, String> map404 = buildReasons(headers, keys404, ErestHeaders.NOT_FOUND_KEY);
                throw new NotFound(map404);
            case BAD_REQUEST:
                List<String> keys400 = headers.get(ErestHeaders.BAD_REQUEST_KEY);
                MultiValueMap<String, String> map400 = buildReasons(headers, keys400, ErestHeaders.BAD_REQUEST_KEY);
                throw new BadRequest(map400);
        }
    }

    private MultiValueMap<String, String> buildReasons(HttpHeaders headers, List<String> keys, String erestHeaders) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        if (keys != null || keys.size() > 0) {
            for (String key : keys) {
                List<String> values = headers.get(erestHeaders + ":" + key);
                if (values.isEmpty()) {
                    map.set(key, null);
                } else {
                    map.put(key, values);
                }
            }
        }
        return map;
    }
}
