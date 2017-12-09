package one.kii.summer.io.context;

import lombok.Getter;

/**
 * Created by WangYanJiong on 4/13/17.
 */

public class ReadContext extends RequestContext {

    @Getter
    String visitorId;

    public ReadContext(String requestId, String ownerId, String visitorId) {
        super(requestId, ownerId);
        this.visitorId = visitorId;
    }

    public ReadContext(WriteContext writeContext) {
        super(writeContext.getRequestId(), writeContext.getOwnerId());
        this.visitorId = writeContext.getOperatorId();
    }
}
