package dev.otthon.hyperprof.api.professores.dtos;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import dev.otthon.hyperprof.api.professores.validators.ProfessorEmailIsUnique;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProfessorRequest {

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 100)
    private String nome;

    @Email
    @NotNull
    @NotEmpty
    @Size(min = 3, max = 255)
    @ProfessorEmailIsUnique
    private String email;

    @NotNull
    @Min(18)
    @Max(100)
    private int idade;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 500)
    private String descricao;

    @NotNull
    @Min(10)
    @Max(500)
    private BigDecimal valorHora;

    @NotNull
    @NotEmpty
    @Size(min = 6, max = 255)
    private String password;

    @NotNull
    @NotEmpty
    @Size(min = 6, max = 255)
    private String passwordConfirmation;

}
