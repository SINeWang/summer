package one.kii.summer.bound;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by WangYanJiong on 02/04/2017.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Form<T> extends Request {

    private T body;
}
