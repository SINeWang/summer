package one.kii.summer.erest;

import org.springframework.http.HttpHeaders;

/**
 * Created by WangYanJiong on 11/04/2017.
 */
public class ErestGetBasic extends ErestRead {

    private String visitorId;

    public ErestGetBasic(String visitorId) {
        this.visitorId = visitorId;
    }

    protected HttpHeaders getHttpHeaders() {
        HttpHeaders headers = buildHttpHeaders();
        headers.set("X-SUMMER-VisitorId", visitorId);
        return headers;
    }
}
