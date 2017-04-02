package one.kii.summer.bound;

import lombok.Data;

/**
 * Created by WangYanJiong on 02/04/2017.
 */
@Data
public class Form<T> extends Request {

    private T body;
}
