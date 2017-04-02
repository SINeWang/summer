package one.kii.summer.bound;

import lombok.Data;

/**
 * Created by WangYanJiong on 02/04/2017.
 */
@Data
public class Receipt<T> extends Response {
    private T body;
}
