package one.kii.summer.beans.annotations;


/**
 * https://stackoverflow.com/a/21070806/1206735
 */

import one.kii.summer.beans.validator.EnumValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.NotNull;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EnumValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@NotNull(message = "value cannot be null")
@ReportAsSingleViolation
public @interface EnumReference {

    Class<? extends Enum<?>> klass();

    String message() default "value is out of range";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
