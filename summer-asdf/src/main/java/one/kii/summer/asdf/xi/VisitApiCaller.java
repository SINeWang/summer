package one.kii.summer.asdf.xi;

import one.kii.summer.io.context.ReadContext;
import one.kii.summer.io.exception.BadRequest;
import one.kii.summer.io.exception.NotFound;
import one.kii.summer.io.exception.Panic;
import one.kii.summer.io.receiver.ErestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

/**
 * Created by WangYanJiong on 03/06/2017.
 */
public class VisitApiCaller {

    private static Logger logger = LoggerFactory.getLogger(VisitApiCaller.class);

    private VisitApiCaller() {
    }

    public static <R, C extends ReadContext, F> ResponseEntity<R> sync(VisitApi<R, C, F> api, C context, F form) {
        logger.debug("begin: api={},context={},form={}", api, context, form);
        try {
            R response = api.visit(context, form);
            logger.debug("after: response={}", response);
            return ErestResponse.ok(context.getRequestId(), response);
        } catch (BadRequest badRequest) {
            logger.error("after: badRequest=<{}>", (Object) badRequest.getKeys());
            return ErestResponse.badRequest(context.getRequestId(), badRequest.getKeys());
        } catch (NotFound notFound) {
            logger.error("after: notFound=<{}>", (Object) notFound.getKeys());
            return ErestResponse.notFound(context.getRequestId(), notFound.getKeys());
        } catch (Panic panic) {
            logger.error("after: panic=<{}>", (Object) panic.getKeys());
            return ErestResponse.panic(context.getRequestId(), panic.getKeys());
        }
    }
}
