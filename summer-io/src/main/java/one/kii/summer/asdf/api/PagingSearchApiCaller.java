package one.kii.summer.asdf.api;

import lombok.extern.slf4j.Slf4j;
import one.kii.summer.io.context.ReadContext;
import one.kii.summer.io.exception.BadRequest;
import one.kii.summer.io.exception.Panic;
import one.kii.summer.io.receiver.ErestResponse;
import one.kii.summer.io.validator.NotBadRequest;
import one.kii.summer.io.validator.NotBadResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class PagingSearchApiCaller {

    private PagingSearchApiCaller() {
    }

    public static <R, C extends ReadContext, F> ResponseEntity<PagingSearchApi.Receipt<R>> sync(
            PagingSearchApi<R, C, F> api,
            C context,
            F form,
            PagingSearchApi.Paging paging,
            Errors errors) {
        log.debug("before: api={},context={},form={}, paging={}, errors={}", api, context, form, paging, errors);
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
            PagingSearchApi.Receipt<R> response = api.search(context, form, paging);
            log.debug("after: response=<{}>", response);
            NotBadResponse.of(response);
            return ErestResponse.ok(context.getRequestId(), response);
        } catch (ConstraintViolationException e) {
            List<String> keys = new ArrayList<>();
            for (ConstraintViolation violation : e.getConstraintViolations()) {
                keys.add(violation.getPropertyPath().toString());
            }
            return ErestResponse.badRequest(context.getRequestId(), keys.toArray(new String[0]));
        } catch (BadRequest badRequest) {
            log.error("after: badRequest=<{}>", (Object) badRequest.getKeys());
            return ErestResponse.badRequest(context.getRequestId(), badRequest.getKeys());
        } catch (Panic panic) {
            log.error("after: panic=<{}>", (Object) panic.getKeys());
            return ErestResponse.panic(context.getRequestId(), panic.getKeys());
        }
    }

    public static <R, C extends ReadContext, F> ResponseEntity<PagingSearchApi.Receipt<R>> sync
            (PagingSearchApi<R, C, F> api,
             C context,
             F form,
             PagingSearchApi.Paging paging) {
        return sync(api, context, form, paging, null);

    }

    public static <R, F> ResponseEntity<PagingSearchApi.Receipt<R>> sync(
            SimplePagingSearchApi<R, F> api,
            ReadContext context,
            F form,
            PagingSearchApi.Paging paging,
            Errors errors) {
        return sync((PagingSearchApi<R, ReadContext, F>) api, context, form, paging, errors);
    }

    public static <R, F> ResponseEntity<PagingSearchApi.Receipt<R>> sync(
            SimplePagingSearchApi<R, F> api,
            ReadContext context,
            F form,
            PagingSearchApi.Paging paging) {
        return sync((PagingSearchApi<R, ReadContext, F>) api, context, form, paging, null);
    }
}
