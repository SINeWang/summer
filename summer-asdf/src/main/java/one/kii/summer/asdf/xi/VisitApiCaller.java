package one.kii.summer.asdf.xi;

import one.kii.summer.io.context.ReadContext;
import one.kii.summer.io.exception.BadRequest;
import one.kii.summer.io.exception.NotFound;
import one.kii.summer.io.exception.Panic;
import one.kii.summer.io.receiver.ErestResponse;
import org.springframework.http.ResponseEntity;

/**
 * Created by WangYanJiong on 03/06/2017.
 */
public class VisitApiCaller {

    private VisitApiCaller() {
    }

    public static <R, C extends ReadContext, F> ResponseEntity<R> sync(VisitApi<R, C, F> api, C context, F form) {
        try {
            return ErestResponse.ok(context.getRequestId(), api.visit(context, form));
        } catch (BadRequest badRequest) {
            return ErestResponse.badRequest(context.getRequestId(), badRequest.getKeys());
        } catch (NotFound notFound) {
            return ErestResponse.notFound(context.getRequestId(), notFound.getKeys());
        } catch (Panic panic) {
            return ErestResponse.panic(context.getRequestId(), panic.getKeys());
        }
    }
}
