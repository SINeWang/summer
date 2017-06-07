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
     * publication from upstream
     */
    private Long set;

    /**
     * root id of publication from upstream
     */
    private Long rootId;


    private String providerId;

    /**
     * Owner play as Subscriber
     */
    private String ownerId;

    private Long id;

    private String group;

    private String name;

    private String tree;

    private Date beginTime;

    @MayHave
    private Date endTime;

}
