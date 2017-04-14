package one.kii.summer.erest;

import one.kii.summer.context.exception.BadRequest;
import one.kii.summer.context.exception.NotFound;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.UUID;

import static one.kii.summer.erest.ErestHeaders.BAD_REQUEST_FIELDS;
import static one.kii.summer.erest.ErestHeaders.CONFLICT_KEY;
import static one.kii.summer.erest.ErestHeaders.NOT_FOUND_KEY;

/**
 * Created by WangYanJiong on 11/04/2017.
 */
public abstract class ErestClient {

    protected String requestId = UUID.randomUUID().toString();

    protected HttpHeaders buildHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(ErestHeaders.REQUEST_ID, requestId);
        return headers;
    }

    protected void handleBasic(HttpStatus code, HttpHeaders headers) throws NotFound, BadRequest {
        switch (code) {
            case NOT_FOUND:
                String key = headers.get(NOT_FOUND_KEY).get(0);
                throw new NotFound(key);
            case BAD_REQUEST:
                List<String> fields = headers.get(BAD_REQUEST_FIELDS);
                throw new BadRequest(fields.toArray(new String[0]));

        }
    }
}
