package one.kii.summer.bound;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * Created by WangYanJiong on 26/03/2017.
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class Summary {

    private Status status;

    private Date time;

    private List<String> reasons;

    public enum Status {
        ACCEPTED,
        REJECTED
    }
}
