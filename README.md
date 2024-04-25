# API de Gerenciamento de Professores e Aulas Particulares
A API permite que professores se cadastrem e sejam listados, além de possibilitar que alunos busquem professores por descrição.

## Rotas
| Rota | Método | Descrição | Requer Autenticação |
| ---- | ------ | --------- | ------------------- |
| /api/professores | GET | Lista os professores | Não |
| /api/professores/{professor_id} | GET | Detalhes do professor | Não |
| /api/professores/{professor_id}/alunos | POST | Cadastra uma solicitação de aula | Não |
| /api/professores | POST | Cadastra um professor | Não |
| /api/professores | PUT | Atualiza os dados do professor logado | Sim |
| /api/professores/foto | POST | Atualiza a foto de um professor | Sim |
| /api/professores/alunos | GET | Lista as solicitações de aula do professor logado | Sim |
| /api/professores | DELETE | Exclui o professor logado | Sim |
| /api/auth/login | POST | Faz login | Não |
| /api/auth/refresh | POST | Atualiza o token de acesso | Não |
| /api/auth/logout | POST | Faz logout | Sim |

## Requisitos
### Funcionais
- **PR01 - Cadastro de professor:** O sistema deve permitir que professores se cadastrem na plataforma informando nome, email, idade, descrição, valor da hora/aula e senha.
- **PR02 - Login:** O sistema deve permitir que professores se autentiquem na plataforma informando email e senha e também possam realizar o processo de logout.
- **PR03 - Atualização de dados pessoais:** O sistema deve permitir que professores atualizem seus dados pessoais, como, nome, email idade, descrição, valor da hora/aula, senha e foto de perfil.
- **PR04 - Listagem de Alunos:** O sistema deve permitir que o professor possa listar todos as solicitações de aulas particulares que recebeu.
- **PR05 - Exclusão de professor:** O sistema deve permitir que o professor possa excluir sua conta.
- **AL01 - Busca de professores:** O sistema deve permitir que alunos busquem professores por nome.
- **AL02 - Detalhes do professor:** O sistema deve permitir que alunos visualizem os detalhes de um professor específico.
- **AL03 - Cadastro de Aula:** O sistema deve permitir que alunos cadastrem aulas particulares informando com um professor informando nome, email, data e horário.
### Não Funcionais
- **NF01 - Segurança:** O sistema deve criptografar as senhas dos usuário a fim de melhorar a segurança da aplicação e manter os dados dos usuários livres de acessos não permitidos.
- **NF02 - Ética:** O sistema não deve exibir qualquer tipo de dados considerado confidencial dos outros usuários.
- **NF03 - Confiabilidade:** O sistema deve estar disponível em, no mínimo, 95% do tempo.

## Casos de Uso
- **PR01** - Cadastro de professor
- **PR02** - Login
- **PR03** - Atualização de dados pessoais
- **PR04** - Listagem de Alunos
- **PR05** - Exclusão de professor
- **AL01** - Busca de professores
- **AL02** - Detalhes do professor
- **AL03** - Cadastro de Aula
