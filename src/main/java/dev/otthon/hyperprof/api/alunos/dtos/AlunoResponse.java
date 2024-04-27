package dev.otthon.hyperprof.api.alunos.dtos;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AlunoResponse {

    private Long id;
    private String nome;
    private String email;
    private LocalDateTime dataAula;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
