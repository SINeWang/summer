package one.kii.summer.bound;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by WangYanJiong on 02/04/2017.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Receipt<T> extends Response {
    private T body;
}
