package one.kii.summer.asdf.api;

import one.kii.summer.io.context.ReadContext;
import one.kii.summer.io.exception.BadRequest;
import one.kii.summer.io.exception.NotFound;
import one.kii.summer.io.exception.Panic;

/**
 * Created by WangYanJiong on 03/06/2017.
 */
public interface VisitApi<R, C extends ReadContext, F> {

    R visit(C context, F form) throws BadRequest, NotFound, Panic;

}
