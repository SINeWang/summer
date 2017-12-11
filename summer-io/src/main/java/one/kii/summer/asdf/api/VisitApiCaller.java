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
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class VisitApiCaller {


    private VisitApiCaller() {
    }

    public static <R, C extends ReadContext, F> ResponseEntity<R> sync(VisitApi<R, C, F> api, C context, F form, Errors errors) {
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
            R response = api.visit(context, form);
            log.debug("after: response={}", response);
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
        } catch (NotFound notFound) {
            log.error("after: notFound=<{}>", (Object) notFound.getKeys());
            return ErestResponse.notFound(context.getRequestId(), notFound.getKeys());
        } catch (Panic panic) {
            log.error("after: panic=<{}>", (Object) panic.getKeys());
            return ErestResponse.panic(context.getRequestId(), panic.getKeys());
        }
    }

    public static <R, F> ResponseEntity<R> sync(SimpleVisitApi<R, F> api, ReadContext context, F form, Errors errors) {
        return sync((VisitApi<R, ReadContext, F>) api, context, form, errors);
    }
}
