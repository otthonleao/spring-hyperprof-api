package dev.otthon.hyperprof.core.repositories;

import dev.otthon.hyperprof.core.models.TokenInvalido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenInvalidoRepository extends JpaRepository<TokenInvalido, Long> {

    // Método para verificar se determinado token já foi cadastrado na lista de tokens inválidos
    boolean existsByToken(String token);

}
