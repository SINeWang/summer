package one.kii.summer.bound;

import lombok.Data;

import java.util.List;

/**
 * Created by WangYanJiong on 02/04/2017.
 */
@Data
public class Context {

    String providerId;

    String consumerId;

    String processId;

    String requestId;

    String responseId;

    String transactionId;

    List<String> forwardIds;

    List<String> replyIds;
}
