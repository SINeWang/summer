package one.kii.summer.asdf.api;

import lombok.extern.slf4j.Slf4j;
import one.kii.summer.io.context.ReadContext;
import one.kii.summer.io.exception.BadRequest;
import one.kii.summer.io.exception.Panic;
import one.kii.summer.io.receiver.ErestResponse;
import org.springframework.http.ResponseEntity;

@Slf4j
public class PagingSearchApiCaller {

    private PagingSearchApiCaller() {
    }

    public static <R, C extends ReadContext, F> ResponseEntity<PagingSearchApi.Receipt<R>> sync(
            PagingSearchApi<R, C, F> api,
            C context,
            F form,
            PagingSearchApi.Paging paging) {

        log.debug("begin: api={},context={},form={}, paging={}", api, context, form, paging);
        try {
            PagingSearchApi.Receipt<R> response = api.search(context, form, paging);
            log.debug("after: response=<{}>", response);
            return ErestResponse.ok(context.getRequestId(), response);
        } catch (BadRequest badRequest) {
            log.error("after: badRequest=<{}>", (Object) badRequest.getKeys());
            return ErestResponse.badRequest(context.getRequestId(), badRequest.getKeys());
        } catch (Panic panic) {
            log.error("after: panic=<{}>", (Object) panic.getKeys());
            return ErestResponse.panic(context.getRequestId(), panic.getKeys());
        }
    }

    public static <R, F> ResponseEntity<PagingSearchApi.Receipt<R>> sync(
            SimplePagingSearchApi<R, F> api,
            ReadContext context,
            F form,
            PagingSearchApi.Paging paging) {
        return sync((PagingSearchApi<R, ReadContext, F>) api, context, form, paging);
    }
}
