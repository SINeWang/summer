package one.kii.summer.asdf.dai;

import one.kii.summer.io.exception.BadRequest;
import one.kii.summer.io.exception.Conflict;

import java.util.List;

/**
 * Created by WangYanJiong on 06/06/2017.
 */
public interface RememberMultipleDai<H, L> {

    void remember(H header, List<L> list) throws BadRequest, Conflict;
}
