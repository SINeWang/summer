package one.kii.summer.asdf.xi;

import one.kii.summer.io.context.WriteContext;
import one.kii.summer.io.exception.*;
import one.kii.summer.io.receiver.ErestResponse;
import org.springframework.http.ResponseEntity;

/**
 * Created by WangYanJiong on 03/06/2017.
 */
public class CommitApiCaller {


    public static <R, C extends WriteContext, F> ResponseEntity<R> call(CommitApi<R, C, F> api, C context, F form) {
        try {
            return ErestResponse.ok(context.getRequestId(), api.commit(context, form));
        } catch (BadRequest badRequest) {
            return ErestResponse.badRequest(context.getRequestId(), badRequest.getKeys());
        } catch (Conflict conflict) {
            return ErestResponse.conflict(context.getRequestId(), conflict.getKeys());
        } catch (Forbidden forbidden) {
            return ErestResponse.forbidden(context.getRequestId(), forbidden.getKeys());
        } catch (NotFound notFound) {
            return ErestResponse.notFound(context.getRequestId(), notFound.getKeys());
        } catch (Panic panic) {
            return ErestResponse.panic(context.getRequestId(), panic.getMessage());
        }
    }
}
