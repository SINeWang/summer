package one.kii.summer.asdf.xi;

import one.kii.summer.io.context.ReadContext;
import one.kii.summer.io.exception.BadRequest;
import one.kii.summer.io.exception.Panic;

import java.util.List;

/**
 * Created by WangYanJiong on 05/06/2017.
 */
public interface SearchApi<R, C extends ReadContext, F> {

    List<R> search(C context, F form) throws BadRequest, Panic;

}
