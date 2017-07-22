package one.kii.summer.asdf.api;

import one.kii.summer.io.context.ReadContext;
import one.kii.summer.io.exception.BadRequest;
import one.kii.summer.io.exception.Panic;
import one.kii.summer.io.receiver.ErestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

public class PagingSearchApiCaller {

    private static Logger logger = LoggerFactory.getLogger(SearchApiCaller.class);

    private PagingSearchApiCaller() {
    }

    public static <R, C extends ReadContext, F extends PagingSearchApi.Paging> ResponseEntity<PagingSearchApi.Receipt<R>> sync(PagingSearchApi<R, C, F> api, C context, F form, PagingSearchApi.Paging paging) {
        logger.debug("begin: api={},context={},form={}, paging={}", api, context, form, paging);
        try {
            PagingSearchApi.Receipt<R> response = api.search(context, form, paging);
            logger.debug("after: response=<{}>", response);
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
