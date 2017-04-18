package one.kii.summer.io.sender;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

/**
 * Created by WangYanJiong on 11/04/2017.
 */
public class ErestDeleteAuthorization extends ErestWrite {

    private String authorization;

    public ErestDeleteAuthorization(String operatorId, String authorization) {
        super(operatorId);
        this.authorization = authorization;
        super.httpMethod = HttpMethod.DELETE;
    }

    protected HttpHeaders getHttpHeaders() {
        HttpHeaders headers = buildHttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, authorization);
        return headers;
    }
}
