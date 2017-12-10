package one.kii.summer.beans.validator;

import one.kii.summer.beans.annotations.AcceptableValues;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;


/**
 * https://stackoverflow.com/a/21070806/1206735
 */

public class AcceptableValuesValidator implements ConstraintValidator<AcceptableValues, String> {

    List<String> values = null;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (!values.contains(value.toUpperCase())) {
            return false;
        }
        return true;
    }

    @Override
    public void initialize(AcceptableValues constraintAnnotation) {
        values = new ArrayList<>();
        Class<? extends Enum<?>> range = constraintAnnotation.range();

        @SuppressWarnings("rawtypes") Enum[] enumValArr = range.getEnumConstants();

        for (@SuppressWarnings("rawtypes") Enum enumVal : enumValArr) {
            values.add(enumVal.toString().toUpperCase());
        }

    }

}