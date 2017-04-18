package one.kii.summer.io.sender;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

/**
 * Created by WangYanJiong on 18/04/2017.
 */
public class ErestPutAuthorization extends ErestWrite {

    private String token;

    public ErestPutAuthorization(String operatorId, String token) {
        super(operatorId);
        super.httpMethod = HttpMethod.PUT;
    }

    protected HttpHeaders getHttpHeaders() {
        HttpHeaders headers = buildHttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, token);
        return headers;
    }
}
