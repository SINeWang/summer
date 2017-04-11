package one.kii.summer.erest;

import org.springframework.http.HttpHeaders;

/**
 * Created by WangYanJiong on 11/04/2017.
 */
public class ErestGetBearer extends ErestRead {

    private String token;

    public ErestGetBearer(String token) {
        this.token = token;
    }

    protected HttpHeaders getHttpHeaders() {
        HttpHeaders headers = buildHttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + token);
        return headers;
    }
}
