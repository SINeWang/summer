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

    private List<String> values = new ArrayList<>();

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return values.contains(value.toUpperCase());
    }

    @Override
    public void initialize(AcceptableValues annotation) {
        Class<? extends Enum<?>> refer = annotation.refer();

        annotation.refer().getName();
        @SuppressWarnings("rawtypes") Enum[] constants = refer.getEnumConstants();

        for (@SuppressWarnings("rawtypes") Enum each : constants) {
            values.add(each.toString().toUpperCase());
        }

    }

}