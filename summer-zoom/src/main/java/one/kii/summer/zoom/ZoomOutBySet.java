package one.kii.summer.zoom;

import lombok.Data;
import one.kii.summer.io.annotations.MayHave;

import java.util.Date;

/**
 * Created by WangYanJiong on 07/06/2017.
 *
 * Disabled extension for <code>class.getDeclaredFields()</code>
 */
@Data
public final class ZoomOutBySet {

    /**
     * Owner play as Provider
     */
    private String providerId;

    private String set;

    @MayHave
    private Date beginTime;

    @MayHave
    private Date endTime;

}
