package one.kii.summer.asdf.api;

import lombok.extern.slf4j.Slf4j;
import one.kii.summer.io.context.WriteContext;
import one.kii.summer.io.exception.*;
import one.kii.summer.io.receiver.ErestResponse;
import one.kii.summer.io.validator.NotBadRequest;
import one.kii.summer.io.validator.NotBadResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CommitApiCaller {


    private CommitApiCaller() {
    }

    public static <R, C extends WriteContext, F> ResponseEntity<R> sync(CommitApi<R, C, F> api, C context, F form, Errors errors) {
        log.debug("before: api={},context={},form={}, errors={}", api, context, form, errors);
        if (errors != null && errors.hasErrors()) {
            List<String> keys = new ArrayList<>();
            for (FieldError error : errors.getFieldErrors()) {
                keys.add(error.getField() + ':' + error.getRejectedValue());
            }
            return ErestResponse.badRequest(context.getRequestId(), keys.toArray(new String[0]));
        }
        try {
            NotBadRequest.from(form);
        } catch (BadRequest badRequest) {
            return ErestResponse.badRequest(context.getRequestId(), badRequest.getKeys());
        }
        try {
            R response = api.commit(context, form);
            log.debug("after: response={}", response);
            NotBadResponse.of(response);
            return ErestResponse.ok(context.getRequestId(), response);
//        } catch (ConstraintViolationException e) {
//            List<String> keys = new ArrayList<>();
//            for (ConstraintViolation violation : e.getConstraintViolations()) {
//                keys.add(violation.getPropertyPath().toString());
//            }
//            return ErestResponse.badRequest(context.getRequestId(), keys.toArray(new String[0]));
        } catch (BadRequest badRequest) {
            log.error("after: badRequest=<{}>", (Object) badRequest.getKeys());
            return ErestResponse.badRequest(context.getRequestId(), badRequest.getKeys());
        } catch (Conflict conflict) {
            log.error("after: conflict=<{}>", (Object) conflict.getKeys());
            return ErestResponse.conflict(context.getRequestId(), conflict.getKeys());
        } catch (Forbidden forbidden) {
            log.error("after: forbidden=<{}>", (Object) forbidden.getKeys());
            return ErestResponse.forbidden(context.getRequestId(), forbidden.getKeys());
        } catch (NotFound notFound) {
            log.error("after: notFound=<{}>", (Object) notFound.getKeys());
            return ErestResponse.notFound(context.getRequestId(), notFound.getKeys());
        } catch (Panic panic) {
            log.error("after: panic=<{}>", (Object) panic.getKeys());
            return ErestResponse.panic(context.getRequestId(), panic.getKeys());
        }
    }

    public static <R, C extends WriteContext, F> ResponseEntity<R> sync(CommitApi<R, C, F> api, C context, F form) {
        return sync(api, context, form, null);
    }

    public static <R, F> ResponseEntity<R> sync(SimpleCommitApi<R, F> api, WriteContext context, F form, Errors errors) {
        return sync((CommitApi<R, WriteContext, F>) api, context, form, errors);
    }

    public static <R, F> ResponseEntity<R> sync(SimpleCommitApi<R, F> api, WriteContext context, F form) {
        return sync((CommitApi<R, WriteContext, F>) api, context, form, null);
    }
}