package one.kii.summer.io.sender;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

/**
 * Created by WangYanJiong on 18/04/2017.
 */
public class ErestPutAuthorization extends ErestWrite {

    private String authorization;

    public ErestPutAuthorization(String operatorId, String authorization) {
        super(operatorId);
        super.httpMethod = HttpMethod.PUT;
        this.authorization = authorization;
    }

    protected HttpHeaders getHttpHeaders() {
        HttpHeaders headers = buildHttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, authorization);
        return headers;
    }
}
