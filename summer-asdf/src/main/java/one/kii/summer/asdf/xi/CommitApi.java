package one.kii.summer.asdf.xi;

import one.kii.summer.io.context.WriteContext;
import one.kii.summer.io.exception.*;

/**
 * Created by WangYanJiong on 03/06/2017.
 */
public interface CommitApi<R, C extends WriteContext, F> {

    R commit(C context, F form) throws BadRequest, Conflict, Forbidden, NotFound, Panic;
}
