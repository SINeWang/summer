package one.kii.summer.zoom;

import lombok.Data;
import one.kii.summer.io.annotations.MayHave;

import java.util.Date;

/**
 * Created by WangYanJiong on 07/06/2017.
 */
@Data
public class InsideView {


    /**
     * publication from upstream
     */
    private String set;

    /**
     * root id of publication from upstream
     */
    private String rootId;


    private String providerId;

    /**
     * Owner play as Subscriber, Subscriber is Owner
     */
    private String subscriberId;

    private String id;

    private String group;

    private String name;

    private String tree;

    private Date beginTime;

    @MayHave
    private Date endTime;

}
