package one.kii.summer.asdf.api;

import lombok.extern.slf4j.Slf4j;
import one.kii.summer.io.context.ReadContext;
import one.kii.summer.io.exception.BadRequest;
import one.kii.summer.io.exception.Panic;
import one.kii.summer.io.receiver.ErestResponse;
import one.kii.summer.io.validator.NotBadRequest;
import one.kii.summer.io.validator.NotBadResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Slf4j
public class SearchApiCaller {

    private SearchApiCaller() {
    }

    public static <R, C extends ReadContext, F> ResponseEntity<List<R>> sync(SearchApi<R, C, F> api, C context, F form) {
        log.debug("begin: api={},context={},form={}", api, context, form);
        try {
            NotBadRequest.from(form);
        } catch (BadRequest badRequest) {
            return ErestResponse.badRequest(context.getRequestId(), badRequest.getKeys());
        }
        try {
            List<R> response = api.search(context, form);
            log.debug("after: response=<{}>", (Object) response.toArray(new Object[0]));
            NotBadResponse.of(response);
            return ErestResponse.ok(context.getRequestId(), response);
        } catch (BadRequest badRequest) {
            log.error("after: badRequest=<{}>", (Object) badRequest.getKeys());
            return ErestResponse.badRequest(context.getRequestId(), badRequest.getKeys());
        } catch (Panic panic) {
            log.error("after: panic=<{}>", (Object) panic.getKeys());
            return ErestResponse.panic(context.getRequestId(), panic.getKeys());
        }
    }

    public static <R, F> ResponseEntity<List<R>> sync(SimpleSearchApi<R, F> api, ReadContext context, F form) {
        return sync((SearchApi<R, ReadContext, F>) api, context, form);
    }
}
