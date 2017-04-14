package one.kii.summer.io.context;

import lombok.Getter;

/**
 * Created by WangYanJiong on 4/13/17.
 */

public class WriteContext extends RequestContext {

    @Getter
    String operatorId;

    public WriteContext(String requestId, String ownerId, String operatorId) {
        super(requestId, ownerId);
        this.operatorId = operatorId;
    }
}
