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
public final class ViewDownWithXyz {

    /**
     * Owner play as Provider
     */
    private String providerId;

    private String group;

    private String name;

    private String stability;

    private String version;

    @MayHave
    private Date beginTime;

}
