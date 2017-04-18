package one.kii.summer.io.sender;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

/**
 * Created by WangYanJiong on 11/04/2017.
 */
public class ErestGetAuthorization extends ErestRead {

    private String token;

    public ErestGetAuthorization(String token) {
        this.token = token;
        super.httpMethod = HttpMethod.GET;
    }

    protected HttpHeaders getHttpHeaders() {
        HttpHeaders headers = buildHttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, token);
        return headers;
    }
}
