package one.kii.summer.context.io;

/**
 * Created by WangYanJiong on 4/13/17.
 */
public abstract class ReadController {

    protected ReadContext buildContext(String requestId, String visitorId, String ownerId) {
        ReadContext context = new ReadContext();
        context.setRequestId(requestId);
        context.setVisitorId(visitorId);
        context.setOwnerId(ownerId);
        return context;
    }
}
