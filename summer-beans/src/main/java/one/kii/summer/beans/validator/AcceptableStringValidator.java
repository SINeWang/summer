package one.kii.summer.beans.validator;

import one.kii.summer.beans.annotations.AcceptableValues;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class AcceptableStringValidator implements ConstraintValidator<AcceptableValues, Integer> {

    private int max;

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value <= max;
    }

    @Override
    public void initialize(AcceptableValues annotation) {
        Class<? extends Enum<?>> refer = annotation.refer();

        annotation.refer().getName();
        @SuppressWarnings("rawtypes") Enum[] constants = refer.getEnumConstants();

        max = constants.length;

    }

}