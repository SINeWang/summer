package one.kii.summer.asdf.api;

import one.kii.summer.io.context.WriteContext;
import one.kii.summer.io.exception.*;

import javax.validation.Valid;

/**
 * Created by WangYanJiong on 03/06/2017.
 */
public interface CommitApi<R, C extends WriteContext, F> {

    R commit(C context, @Valid F form) throws BadRequest, Conflict, Forbidden, NotFound, Panic;
}
