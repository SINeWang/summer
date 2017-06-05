package one.kii.summer.asdf.xi;

import one.kii.summer.io.context.WriteContext;
import one.kii.summer.io.exception.*;
import one.kii.summer.io.receiver.ErestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

/**
 * Created by WangYanJiong on 03/06/2017.
 */
public class CommitApiCaller {

    private static Logger logger = LoggerFactory.getLogger(CommitApiCaller.class);

    private CommitApiCaller() {
    }

    public static <R, C extends WriteContext, F> ResponseEntity<R> sync(CommitApi<R, C, F> api, C context, F form) {
        logger.debug("before: api={},context={},form={}", api, context, form);
        try {
            R response = api.commit(context, form);
            logger.debug("after: response={}", response);
            return ErestResponse.ok(context.getRequestId(), response);
        } catch (BadRequest badRequest) {
            logger.error("after: badRequest=<{}>", (Object) badRequest.getKeys());
            return ErestResponse.badRequest(context.getRequestId(), badRequest.getKeys());
        } catch (Conflict conflict) {
            logger.error("after: conflict=<{}>", (Object) conflict.getKeys());
            return ErestResponse.conflict(context.getRequestId(), conflict.getKeys());
        } catch (Forbidden forbidden) {
            logger.error("after: forbidden=<{}>", (Object) forbidden.getKeys());
            return ErestResponse.forbidden(context.getRequestId(), forbidden.getKeys());
        } catch (NotFound notFound) {
            logger.error("after: notFound=<{}>", (Object) notFound.getKeys());
            return ErestResponse.notFound(context.getRequestId(), notFound.getKeys());
        } catch (Panic panic) {
            logger.error("after: panic=<{}>", (Object) panic.getKeys());
            return ErestResponse.panic(context.getRequestId(), panic.getKeys());
        }
    }
}
