package one.kii.summer.context.io;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by WangYanJiong on 4/13/17.
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class RequestContext {

    String requestId;

    String ownerId;

}
