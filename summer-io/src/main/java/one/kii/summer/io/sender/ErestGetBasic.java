package one.kii.summer.io.sender;

import one.kii.summer.io.context.ErestHeaders;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

/**
 * Created by WangYanJiong on 11/04/2017.
 */
public class ErestGetBasic extends ErestRead {

    private String visitorId;

    public ErestGetBasic(String visitorId) {
        this.visitorId = visitorId;
        super.httpMethod = HttpMethod.GET;
    }

    protected HttpHeaders getHttpHeaders() {
        HttpHeaders headers = buildHttpHeaders();
        headers.set(ErestHeaders.VISITOR_ID, visitorId);
        return headers;
    }
}
