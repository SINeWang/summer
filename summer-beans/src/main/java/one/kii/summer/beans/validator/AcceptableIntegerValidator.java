package one.kii.summer.beans.validator;

import one.kii.summer.beans.annotations.AcceptableValues;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;


public class AcceptableIntegerValidator implements ConstraintValidator<AcceptableValues, String> {

    private List<String> values = new ArrayList<>();

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            final int v = Integer.valueOf(value.toString());
            return v <= values.size();
        } catch (NumberFormatException e) {
            return values.contains(value.toString().toUpperCase());
        }
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