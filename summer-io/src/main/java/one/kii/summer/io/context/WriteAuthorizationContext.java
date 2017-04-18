package one.kii.summer.io.context;

import lombok.Getter;

/**
 * Created by WangYanJiong on 4/13/17.
 */

public class WriteAuthorizationContext extends RequestContext {

    @Getter
    String operatorId;

    @Getter
    String authorization;

    public WriteAuthorizationContext(String requestId, String ownerId, String operatorId, String authorization) {
        super(requestId, ownerId);
        this.operatorId = operatorId;
        this.authorization = authorization;
    }
}
