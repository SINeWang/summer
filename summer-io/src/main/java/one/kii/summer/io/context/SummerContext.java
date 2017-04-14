package one.kii.summer.io.context;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

/**
 * Created by WangYanJiong on 14/04/2017.
 */

@EqualsAndHashCode(callSuper = false)
public abstract class SummerContext {

    @Getter
    String processId = UUID.randomUUID().toString();

}
