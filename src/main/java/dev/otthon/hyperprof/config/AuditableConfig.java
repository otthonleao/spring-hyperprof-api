package dev.otthon.hyperprof.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class AuditableConfig {

    /*

    A interface Auditable em JPA (Java Persistence API) é uma forma de incorporar funcionalidades de auditoria em
    entidades persistentes. Ela é geralmente usada em conjunto com frameworks de persistência, como Hibernate, para
    manter um registro de quando uma entidade foi criada e modificada, quem realizou as modificações e quais foram as
    alterações feitas.

    A interface Auditable normalmente define métodos para recuperar informações sobre a criação e última modificação
    de uma entidade. Isso pode incluir campos como data e hora de criação, data e hora da última modificação e
    identificação do usuário que realizou a modificação.

    Por exemplo, uma entidade Produto que implementa Auditable pode ter os seguintes campos adicionais:

    - createdDate: Data e hora de criação do produto.
    - lastModifiedDate: Data e hora da última modificação do produto.
    - createdBy: Identificação do usuário que criou o produto.
    - lastModifiedBy: Identificação do usuário que realizou a última modificação no produto.
    Essas informações podem ser úteis para rastrear mudanças em dados ao longo do tempo e para auditoria
    de atividades no sistema.

    Normalmente, a implementação da interface Auditable é feita em uma classe base genérica que todas as entidades
    persistentes do sistema estendem. Isso permite que as funcionalidades de auditoria sejam facilmente compartilhadas
    entre todas as entidades.

     */
}
