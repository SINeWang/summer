package one.kii.summer.io.receiver;

import one.kii.summer.io.context.ReadContext;

/**
 * Created by WangYanJiong on 4/13/17.
 */
public abstract class ReadController {

    protected ReadContext buildContext(String requestId, String visitorId, String ownerId) {
        return new ReadContext(requestId, visitorId, ownerId);
    }
}
