package one.kii.summer.io.context;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Created by WangYanJiong on 4/13/17.
 */

@EqualsAndHashCode(callSuper = false)
public class RequestContext extends SummerContext {

    @Getter
    String requestId;

    @Getter
    String ownerId;

    @Getter
    String referId;

    public RequestContext(String requestId, String ownerId) {
        this.requestId = requestId;
        this.ownerId = ownerId;
    }
}
