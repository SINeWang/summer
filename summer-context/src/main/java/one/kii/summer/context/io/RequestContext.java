package one.kii.summer.context.io;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

/**
 * Created by WangYanJiong on 4/13/17.
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class RequestContext extends SummerContext {

    String requestId;

    String ownerId;

}
