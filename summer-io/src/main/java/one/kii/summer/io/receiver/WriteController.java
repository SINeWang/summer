package one.kii.summer.io.receiver;

import one.kii.summer.io.context.WriteContext;

/**
 * Created by WangYanJiong on 4/13/17.
 */
public abstract class WriteController {

    protected WriteContext buildContext(String requestId, String operatorId, String ownerId) {
        return new WriteContext(requestId, ownerId, operatorId);
    }

}
