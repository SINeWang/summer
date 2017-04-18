package one.kii.summer.io.sender;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

/**
 * Created by WangYanJiong on 17/04/2017.
 */
public class ErestPostAuthorization extends ErestWrite {

    private String token;

    public ErestPostAuthorization(String operatorId, String token) {
        super(operatorId);
        super.httpMethod = HttpMethod.POST;
        this.token = token;
    }

    protected HttpHeaders getHttpHeaders() {
        HttpHeaders headers = buildHttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, token);
        return headers;
    }
}
