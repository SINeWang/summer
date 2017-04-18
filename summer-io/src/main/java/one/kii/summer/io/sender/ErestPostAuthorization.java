package one.kii.summer.io.sender;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

/**
 * Created by WangYanJiong on 17/04/2017.
 */
public class ErestPostAuthorization extends ErestWrite {

    private String authorization;

    public ErestPostAuthorization(String operatorId, String authorization) {
        super(operatorId);
        super.httpMethod = HttpMethod.POST;
        this.authorization = authorization;
    }

    protected HttpHeaders getHttpHeaders() {
        HttpHeaders headers = buildHttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, authorization);
        return headers;
    }
}
