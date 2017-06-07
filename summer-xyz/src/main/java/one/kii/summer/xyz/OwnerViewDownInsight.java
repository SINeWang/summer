package one.kii.summer.xyz;

import lombok.Data;
import one.kii.summer.io.annotations.MayHave;

import java.util.Date;

/**
 * Created by WangYanJiong on 07/06/2017.
 */
@Data
public class OwnerViewDownInsight {


    /**
     * subscription from upstream
     */
    private Long id;

    /**
     * publication to downstream
     */
    private Long set;

    private String ownerId;

    private String group;

    private String name;

    private String stability;

    private String version;

    private Date beginTime;

    @MayHave
    private Date endTime;

}
