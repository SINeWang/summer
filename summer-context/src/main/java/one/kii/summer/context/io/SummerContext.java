package one.kii.summer.context.io;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

/**
 * Created by WangYanJiong on 14/04/2017.
 */

@Data
@EqualsAndHashCode(callSuper = false)
public abstract class SummerContext {

    String processId = UUID.randomUUID().toString();

}
