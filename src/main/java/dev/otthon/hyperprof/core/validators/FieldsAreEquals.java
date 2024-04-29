package dev.otthon.hyperprof.core.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FieldsAreEqualsValidator.class)
public @interface FieldsAreEquals {

    String message() default "Deve ser igual ao campo '{field}'";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    // PARÂMETROS DE COMPARAÇÃO
    String field();
    String fieldMatch();

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        FieldsAreEquals[] value();
    }

}
