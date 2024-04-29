package dev.otthon.hyperprof.core.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public class FieldsAreEqualsValidator implements ConstraintValidator<FieldsAreEquals, Object> {

    private String field;
    private String fieldMatch;

    // ACESSO AOS VALORES DOS CAMPOS
    @Override
    public void initialize(FieldsAreEquals constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.fieldMatch = constraintAnnotation.fieldMatch();
        validateParameters(); // Verificando se o campo está nulo ou vazio
    }

    @Override
    @SuppressWarnings("null")
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        // SOBREESCREVENDO O ERRO GLOBAL PARA MOSTRAR O ERRO NO ATRIBUTO ESPECÍFICO 'password_confirmation' NO ENDPOINT
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                .addPropertyNode(fieldMatch)
                .addConstraintViolation();

        var fieldPropertyDescriptor = BeanUtils.getPropertyDescriptor(value.getClass(), field);
        var fieldMatchPropertyDescriptor = BeanUtils.getPropertyDescriptor(value.getClass(), fieldMatch);

        if (fieldPropertyDescriptor == null || fieldMatchPropertyDescriptor == null) {
            throw new IllegalArgumentException("Os campos informados não existem na classe");
        }

        try {
            var fieldValue = fieldPropertyDescriptor.getReadMethod().invoke(value);
            var fieldMatchValue = fieldMatchPropertyDescriptor.getReadMethod().invoke(value);

            return Objects.equals(fieldValue, fieldMatchValue);

        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException("Não foi possível acessar os campos informados");
        }

    }

    private void validateParameters() {
        if (field == null || field.isEmpty()) {
            throw new IllegalArgumentException("ERROR: O campo 'field' não pode ser nulo ou vazio");
        }
        if (fieldMatch == null || fieldMatch.isEmpty()) {
            throw new IllegalArgumentException("ERROR: O campo 'fieldMatch' não pode ser nulo ou vazio");
        }

    }
}
