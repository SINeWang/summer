package one.kii.summer.erest;

import org.springframework.http.HttpHeaders;

import java.util.UUID;

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
}
