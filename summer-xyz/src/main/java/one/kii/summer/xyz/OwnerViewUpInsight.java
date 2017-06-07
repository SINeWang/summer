package one.kii.summer.xyz;

import lombok.Data;
import one.kii.summer.io.annotations.MayHave;

import java.util.Date;

/**
 * Created by WangYanJiong on 07/06/2017.
 */
@Data
public class OwnerViewUpInsight {


    /**
     * Owner play as Subscriber
     */
    private String subscriberId;

    private Long id;

    /**
     * upstream set of provider's publication
     */
    private Long subSet;

    private String group;

    private String name;

    private String tree;

    private Date beginTime;

    @MayHave
    private Date endTime;

}
