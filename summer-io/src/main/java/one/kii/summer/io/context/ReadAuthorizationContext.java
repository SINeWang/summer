package one.kii.summer.io.context;

import lombok.Getter;

/**
 * Created by WangYanJiong on 4/13/17.
 */

public class ReadAuthorizationContext extends RequestContext {

    @Getter
    String visitorId;

    @Getter
    String authorization;

    public ReadAuthorizationContext(String requestId, String ownerId, String visitorId, String authorization) {
        super(requestId, ownerId);
        this.visitorId = visitorId;
        this.authorization = authorization;
    }
}
