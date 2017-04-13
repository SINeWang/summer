package one.kii.summer.context.io;

/**
 * Created by WangYanJiong on 4/13/17.
 */
public abstract class WriteController {

    protected WriteContext buildContext(String requestId, String operatorId, String ownerId) {
        WriteContext context = new WriteContext();
        context.setRequestId(requestId);
        context.setOperatorId(operatorId);
        context.setOwnerId(ownerId);
        return context;
    }
}
