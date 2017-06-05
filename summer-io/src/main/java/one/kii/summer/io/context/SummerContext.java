package one.kii.summer.io.context;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by WangYanJiong on 14/04/2017.
 */

@EqualsAndHashCode(callSuper = false)
public abstract class SummerContext implements Serializable {

    @Getter
    String processId = UUID.randomUUID().toString();

}
