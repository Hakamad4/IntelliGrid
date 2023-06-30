package com.fiap.intelligrid.config.validator;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CepValidator.class)
public @interface ValidCep {

    String message() default "CEP fora do padrão 00000-000";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
