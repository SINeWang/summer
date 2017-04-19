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
                List<String> values = headers.get(ErestHeaders.NOT_FOUND_KEY);
                if (values == null || values.size() == 0) {
                    throw new NotFound(null);
                }
                throw new NotFound(values.toArray(new String[0]));
            case BAD_REQUEST:
                List<String> fields = headers.get(ErestHeaders.BAD_REQUEST_FIELDS);
                if (fields == null || fields.size() == 0) {
                    throw new NotFound(null);
                }
                throw new BadRequest(fields.toArray(new String[0]));

        }
    }
}
