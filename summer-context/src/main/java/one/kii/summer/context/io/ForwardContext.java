package one.kii.summer.context.io;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

/**
 * Created by WangYanJiong on 4/13/17.
 */

@Data
public class ForwardContext extends SummerContext {

    String requestId;

    String forwardId = UUID.randomUUID().toString();

}
