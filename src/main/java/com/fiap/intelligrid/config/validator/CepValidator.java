package com.fiap.intelligrid.config.validator;


import io.micrometer.common.util.StringUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class CepValidator implements ConstraintValidator<ValidCep, String> {

    private static final Pattern CEP_PATTERN = Pattern.compile("\\d{5}-\\d{3}");

    @Override
    public void initialize(ValidCep constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return StringUtils.isNotEmpty(value) && CEP_PATTERN.matcher(value).matches();
    }
}
