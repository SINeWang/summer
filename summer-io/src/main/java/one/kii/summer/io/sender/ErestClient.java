package one.kii.summer.io.sender;

import one.kii.summer.io.context.ErestHeaders;
import one.kii.summer.io.exception.BadRequest;
import one.kii.summer.io.exception.NotFound;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

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
                String key = headers.get(ErestHeaders.NOT_FOUND_KEY).get(0);
                throw new NotFound(key);
            case BAD_REQUEST:
                List<String> fields = headers.get(ErestHeaders.BAD_REQUEST_FIELDS);
                throw new BadRequest(fields.toArray(new String[0]));

        }
    }
}
