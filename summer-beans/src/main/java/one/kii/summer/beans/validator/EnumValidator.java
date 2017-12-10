package one.kii.summer.beans.validator;

import one.kii.summer.beans.annotations.EnumReference;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;


/**
 * https://stackoverflow.com/a/21070806/1206735
 */

public class EnumValidator implements ConstraintValidator<EnumReference, String> {

    List<String> values = null;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (!values.contains(value.toUpperCase())) {
            return false;
        }
        return true;
    }

    @Override
    public void initialize(EnumReference constraintAnnotation) {
        values = new ArrayList<>();
        Class<? extends Enum<?>> klass = constraintAnnotation.klass();

        @SuppressWarnings("rawtypes") Enum[] enumValArr = klass.getEnumConstants();

        for (@SuppressWarnings("rawtypes") Enum enumVal : enumValArr) {
            values.add(enumVal.toString().toUpperCase());
        }

    }

}