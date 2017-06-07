package one.kii.summer.xyz;

import lombok.Data;
import one.kii.summer.io.annotations.MayHave;

import java.util.Date;

/**
 * Created by WangYanJiong on 07/06/2017.
 *
 * Disabled extension for <code>class.getDeclaredFields()</code>
 */
@Data
public final class ViewUpWithId {

    /**
     * Owner play as Subscriber
     */
    private String subscriberId;

    private Long id;

    @MayHave
    private Date beginTime;

}
