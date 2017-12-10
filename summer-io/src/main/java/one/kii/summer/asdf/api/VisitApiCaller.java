package one.kii.summer.asdf.api;

import lombok.extern.slf4j.Slf4j;
import one.kii.summer.io.context.ReadContext;
import one.kii.summer.io.exception.BadRequest;
import one.kii.summer.io.exception.NotFound;
import one.kii.summer.io.exception.Panic;
import one.kii.summer.io.receiver.ErestResponse;
import one.kii.summer.io.validator.NotBadRequest;
import one.kii.summer.io.validator.NotBadResponse;
import org.springframework.http.ResponseEntity;

@Slf4j
public class VisitApiCaller {


    private VisitApiCaller() {
    }

    public static <R, C extends ReadContext, F> ResponseEntity<R> sync(VisitApi<R, C, F> api, C context, F form) {
        log.debug("begin: api={},context={},form={}", api, context, form);
        try {
            NotBadRequest.from(form);
        } catch (BadRequest badRequest) {
            return ErestResponse.badRequest(context.getRequestId(), badRequest.getKeys());
        }
        try {
            R response = api.visit(context, form);
            log.debug("after: response={}", response);
            NotBadResponse.of(response);
            return ErestResponse.ok(context.getRequestId(), response);
        } catch (BadRequest badRequest) {
            log.error("after: badRequest=<{}>", (Object) badRequest.getKeys());
            return ErestResponse.badRequest(context.getRequestId(), badRequest.getKeys());
        } catch (NotFound notFound) {
            log.error("after: notFound=<{}>", (Object) notFound.getKeys());
            return ErestResponse.notFound(context.getRequestId(), notFound.getKeys());
        } catch (Panic panic) {
            log.error("after: panic=<{}>", (Object) panic.getKeys());
            return ErestResponse.panic(context.getRequestId(), panic.getKeys());
        }
    }

    public static <R, C extends ReadContext, F> ResponseEntity<R> sync(SimpleVisitApi<R, F> api, ReadContext context, F form) {
        return sync((VisitApi<R, ReadContext, F>) api, context, form);
    }
}
