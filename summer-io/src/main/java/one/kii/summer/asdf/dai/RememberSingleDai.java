package one.kii.summer.asdf.dai;

import one.kii.summer.io.exception.BadRequest;
import one.kii.summer.io.exception.Conflict;

/**
 * Created by WangYanJiong on 06/06/2017.
 */
public interface RememberSingleDai<T> {

    void remember(T object) throws BadRequest, Conflict;
}
