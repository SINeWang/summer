package one.kii.summer.zoom;

import lombok.Data;
import one.kii.summer.io.annotations.MayHave;

import java.util.Date;

/**
 * Created by WangYanJiong on 07/06/2017.
 */
@Data
public class OutsideView {


    /**
     * subscription from upstream
     */
    private String id;

    /**
     * publication to downstream
     */
    private String set;

    /**
     * Owner play as Provider, Provider is Owner
     */
    private String providerId;

    private String group;

    private String name;

    private String stability;

    private String version;

    private Date beginTime;

    @MayHave
    private Date endTime;

}
