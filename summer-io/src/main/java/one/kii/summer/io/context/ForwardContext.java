package one.kii.summer.io.context;

import lombok.Data;

import java.util.UUID;

/**
 * Created by WangYanJiong on 4/13/17.
 */

@Data
public class ForwardContext extends SummerContext {

    String requestId;

    String forwardId = UUID.randomUUID().toString();

}
