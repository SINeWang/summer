package one.kii.summer.asdf.xi;

import one.kii.summer.io.context.ReadContext;
import one.kii.summer.io.exception.BadRequest;
import one.kii.summer.io.exception.Panic;
import one.kii.summer.io.receiver.ErestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Created by WangYanJiong on 05/06/2017.
 */
public class SearchApiCaller {

    private static Logger logger = LoggerFactory.getLogger(SearchApiCaller.class);

    private SearchApiCaller() {
    }

    public static <R, C extends ReadContext, F> ResponseEntity<List<R>> sync(SearchApi<R, C, F> api, C context, F form) {
        logger.debug("begin: api={},context={},form={}", api, context, form);
        try {
            List<R> response = api.search(context, form);
            logger.debug("after: response={}", (Object) response.toArray(new Object[0]));
            return ErestResponse.ok(context.getRequestId(), response);
        } catch (BadRequest badRequest) {
            logger.error("after: badRequest=<{}>", (Object) badRequest.getKeys());
            return ErestResponse.badRequest(context.getRequestId(), badRequest.getKeys());
        } catch (Panic panic) {
            logger.error("after: panic=<{}>", (Object) panic.getKeys());
            return ErestResponse.panic(context.getRequestId(), panic.getKeys());
        }
    }
}
