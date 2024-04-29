package dev.otthon.hyperprof.api.professores.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ProfessorEmailIsUniqueValidator.class)
public @interface ProfessorEmailIsUnique {

    String message() default "EN: Email already exists | PT: Email já existe";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
