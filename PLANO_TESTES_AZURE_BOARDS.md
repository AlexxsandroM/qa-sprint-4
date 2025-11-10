# Plano de Testes Manuais - Sistema Eficientiza
## Azure Boards - Testes de Validação no Nível de Sistema

---

## 📋 TESTE 1: Login com Usuário Administrador

### **1) Teste Planejado**
- **ID:** TC-001
- **Nome:** Validar Login de Usuário Administrador
- **Objetivo:** Verificar se um usuário com perfil ADMIN consegue autenticar-se no sistema e acessar a página inicial
- **Prioridade:** Alta
- **Tipo:** Funcional
- **Módulo:** Autenticação

### **2) Dados de Entrada**
| Campo | Valor | Tipo | Obrigatório |
|-------|-------|------|-------------|
| E-mail | admin@gmail.com | String | Sim |
| Senha | admin | String | Sim |
| Perfil esperado | ADMIN | Enum | N/A |

**Pré-condições:**
- Sistema em execução (porta 8080)
- Banco de dados inicializado com migrations
- Usuário admin cadastrado no sistema

### **3) Dados de Saída Esperados**
| Campo | Valor Esperado | Tipo |
|-------|----------------|------|
| Status HTTP | 302 (Redirect) | Integer |
| URL de destino | http://localhost:8080/ | String |
| Mensagem de boas-vindas | "Selecione um módulo" | String |
| Cookie de sessão | JSESSIONID presente | String |
| Perfil na sessão | ADMIN | Enum |

**Critérios de Sucesso:**
- ✅ Redirecionamento para página inicial (/)
- ✅ Texto "Selecione um módulo" visível na tela
- ✅ Sessão criada com sucesso
- ✅ Sem mensagens de erro

### **4) Procedimento de Teste (Passos)**

**Passo 1:** Acessar o sistema
- Ação: Abrir navegador e navegar para `http://localhost:8080/login`
- Resultado esperado: Página de login é exibida com campos e-mail e senha

**Passo 2:** Preencher credenciais
- Ação: Digitar `admin@gmail.com` no campo "E-mail"
- Resultado esperado: Texto é exibido no campo

**Passo 3:** Preencher senha
- Ação: Digitar `admin` no campo "Senha"
- Resultado esperado: Senha é mascarada (••••••)

**Passo 4:** Submeter formulário
- Ação: Clicar no botão "Entrar"
- Resultado esperado: Requisição POST enviada

**Passo 5:** Validar redirecionamento
- Ação: Aguardar resposta do servidor
- Resultado esperado: Navegador redireciona para http://localhost:8080/

**Passo 6:** Verificar página inicial
- Ação: Observar conteúdo da página
- Resultado esperado: Texto "Selecione um módulo" está presente

**Status:** ✅ PASS | ❌ FAIL | ⏸️ BLOCKED

---

## 📋 TESTE 2: Listagem de Motos com Dados Iniciais

### **1) Teste Planejado**
- **ID:** TC-002
- **Nome:** Validar Listagem de Motos Cadastradas
- **Objetivo:** Verificar se a página de listagem exibe corretamente as motos cadastradas via migrations
- **Prioridade:** Alta
- **Tipo:** Funcional
- **Módulo:** Gestão de Motos

### **2) Dados de Entrada**
| Campo | Valor | Tipo | Obrigatório |
|-------|-------|------|-------------|
| Usuário logado | admin@gmail.com | String | Sim |
| URL de acesso | /motos | String | Sim |
| Dados no banco | Migration V2 aplicada | Boolean | Sim |

**Pré-condições:**
- Usuário ADMIN autenticado
- Migrations V1 e V2 executadas com sucesso
- Moto com placa "ABC1234" inserida no banco

**Dados de teste no banco (Migration V2):**
```sql
INSERT INTO tb_mtt_moto_c3_java (id_moto, ds_placa, nm_modelo, ds_cor, nr_ano, ds_status, ds_vaga)
VALUES (1, 'ABC1234', 'Honda CG 160', 'Vermelha', 2023, 'DISPONIVEL', 'V01');
```

### **3) Dados de Saída Esperados**
| Campo | Valor Esperado | Tipo |
|-------|----------------|------|
| Status HTTP | 200 OK | Integer |
| Elemento HTML | `<table>` presente | HTML Element |
| Placa visível | ABC1234 | String |
| Modelo visível | Honda CG 160 | String |
| Cor visível | Vermelha | String |
| Ano visível | 2023 | Integer |
| Status visível | DISPONIVEL | String |
| Vaga visível | V01 | String |

**Critérios de Sucesso:**
- ✅ Tabela HTML é renderizada
- ✅ Pelo menos 1 linha de dados (além do cabeçalho)
- ✅ Placa "ABC1234" está presente na tabela
- ✅ Todos os campos da moto são exibidos corretamente

### **4) Procedimento de Teste (Passos)**

**Passo 1:** Realizar login
- Ação: Executar procedimento de login (TC-001)
- Resultado esperado: Usuário autenticado com sucesso

**Passo 2:** Navegar para página de motos
- Ação: Clicar no link "Motos" ou acessar `http://localhost:8080/motos`
- Resultado esperado: Requisição GET para /motos é enviada

**Passo 3:** Aguardar carregamento
- Ação: Esperar resposta do servidor
- Resultado esperado: Página carrega com status 200

**Passo 4:** Verificar estrutura da tabela
- Ação: Inspecionar DOM buscando elemento `<table>`
- Resultado esperado: Tabela está presente no HTML

**Passo 5:** Validar cabeçalho da tabela
- Ação: Verificar `<thead>` com colunas: Placa, Modelo, Cor, Ano, Status, Vaga, Ações
- Resultado esperado: Todas as colunas estão presentes

**Passo 6:** Verificar dados iniciais
- Ação: Buscar na tabela a placa "ABC1234"
- Resultado esperado: Placa encontrada na primeira linha de dados

**Passo 7:** Validar dados completos
- Ação: Verificar todos os campos da moto ABC1234
- Resultado esperado: Modelo, cor, ano, status e vaga corretos

**Status:** ✅ PASS | ❌ FAIL | ⏸️ BLOCKED

---

## 📋 TESTE 3: Acesso à Página de Histórico de Motos

### **1) Teste Planejado**
- **ID:** TC-003
- **Nome:** Validar Acesso ao Histórico de Movimentações
- **Objetivo:** Verificar se usuários ADMIN conseguem acessar e visualizar o histórico de movimentações das motos
- **Prioridade:** Média
- **Tipo:** Funcional
- **Módulo:** Histórico de Motos

### **2) Dados de Entrada**
| Campo | Valor | Tipo | Obrigatório |
|-------|-------|------|-------------|
| Usuário logado | admin@gmail.com | String | Sim |
| Perfil do usuário | ADMIN | Enum | Sim |
| URL de acesso | /historicos-moto | String | Sim |

**Pré-condições:**
- Usuário ADMIN autenticado
- Migrations aplicadas (V1-V6)
- Dados de histórico cadastrados no banco

**Dados de teste no banco (Migration V2/V3):**
```sql
INSERT INTO tb_mtt_historico_moto_c3_java (id_historico, id_moto, id_usuario, tp_acao, dt_acao)
VALUES (1, 1, 1, 'ENTRADA', CURRENT_TIMESTAMP);
```

### **3) Dados de Saída Esperados**
| Campo | Valor Esperado | Tipo |
|-------|----------------|------|
| Status HTTP | 200 OK | Integer |
| Elemento HTML | `<table>` presente | HTML Element |
| Colunas da tabela | ID, Moto, Usuário, Ação, Data | String[] |
| Número de registros | >= 0 | Integer |
| Acesso negado | false | Boolean |

**Critérios de Sucesso:**
- ✅ Página carrega sem erro 403 (Forbidden)
- ✅ Tabela de histórico é renderizada
- ✅ Colunas necessárias estão presentes
- ✅ Layout está correto

### **4) Procedimento de Teste (Passos)**

**Passo 1:** Autenticar como ADMIN
- Ação: Realizar login com admin@gmail.com
- Resultado esperado: Sessão ADMIN criada

**Passo 2:** Acessar menu de histórico
- Ação: Navegar para `http://localhost:8080/historicos-moto`
- Resultado esperado: Requisição GET enviada

**Passo 3:** Verificar autorização
- Ação: Aguardar resposta HTTP
- Resultado esperado: Status 200 (não 403 Forbidden)

**Passo 4:** Validar estrutura da página
- Ação: Inspecionar elemento `<table>` no DOM
- Resultado esperado: Tabela presente

**Passo 5:** Verificar cabeçalhos
- Ação: Validar `<thead>` com colunas apropriadas
- Resultado esperado: Colunas de histórico estão presentes

**Passo 6:** Verificar dados (se existirem)
- Ação: Contar linhas na `<tbody>`
- Resultado esperado: Se houver dados, são exibidos corretamente

**Status:** ✅ PASS | ❌ FAIL | ⏸️ BLOCKED

---

## 📋 TESTE 4: Acesso à Página de Gestão de Vagas

### **1) Teste Planejado**
- **ID:** TC-004
- **Nome:** Validar Acesso e Listagem de Vagas
- **Objetivo:** Verificar se a página de vagas exibe corretamente as vagas cadastradas e seus status
- **Prioridade:** Alta
- **Tipo:** Funcional
- **Módulo:** Gestão de Vagas

### **2) Dados de Entrada**
| Campo | Valor | Tipo | Obrigatório |
|-------|-------|------|-------------|
| Usuário logado | admin@gmail.com | String | Sim |
| URL de acesso | /vagas | String | Sim |
| Vagas no banco | >= 1 | Integer | Sim |

**Pré-condições:**
- Usuário ADMIN autenticado
- Migrations V1-V2 aplicadas
- Vagas cadastradas no banco de dados

**Dados de teste no banco (Migration V2):**
```sql
INSERT INTO tb_mtt_vaga_c3_java (id_vaga, ds_vaga, ds_status, id_moto)
VALUES (1, 'V01', 'OCUPADA', 1),
       (2, 'V02', 'LIVRE', NULL),
       (3, 'V03', 'LIVRE', NULL);
```

### **3) Dados de Saída Esperados**
| Campo | Valor Esperado | Tipo |
|-------|----------------|------|
| Status HTTP | 200 OK | Integer |
| Elemento HTML | `<table>` presente | HTML Element |
| Vaga V01 status | OCUPADA | String |
| Vaga V01 moto | ABC1234 (placa) | String |
| Vaga V02 status | LIVRE | String |
| Vaga V03 status | LIVRE | String |
| Total de vagas | >= 3 | Integer |

**Critérios de Sucesso:**
- ✅ Tabela de vagas é exibida
- ✅ Todas as vagas cadastradas aparecem
- ✅ Status de cada vaga está correto
- ✅ Motos associadas são exibidas

### **4) Procedimento de Teste (Passos)**

**Passo 1:** Login no sistema
- Ação: Autenticar com admin@gmail.com
- Resultado esperado: Sessão criada

**Passo 2:** Navegar para vagas
- Ação: Acessar `http://localhost:8080/vagas`
- Resultado esperado: Página de vagas carrega

**Passo 3:** Verificar tabela
- Ação: Localizar elemento `<table>` no DOM
- Resultado esperado: Tabela está presente

**Passo 4:** Validar vaga V01
- Ação: Buscar linha com "V01" na tabela
- Resultado esperado: Status "OCUPADA" e moto associada

**Passo 5:** Validar vagas livres
- Ação: Buscar linhas com status "LIVRE"
- Resultado esperado: V02 e V03 aparecem como livres

**Passo 6:** Verificar total
- Ação: Contar número de linhas na tabela
- Resultado esperado: Pelo menos 3 vagas listadas

**Status:** ✅ PASS | ❌ FAIL | ⏸️ BLOCKED

---

## 📋 TESTE 5: Login com Usuário Operador e Acesso às Motos

### **1) Teste Planejado**
- **ID:** TC-005
- **Nome:** Validar Login e Permissões do Perfil OPERADOR
- **Objetivo:** Verificar se usuário com perfil OPERADOR consegue autenticar-se e acessar funcionalidade de motos
- **Prioridade:** Alta
- **Tipo:** Funcional - Controle de Acesso
- **Módulo:** Autenticação + Autorização

### **2) Dados de Entrada**
| Campo | Valor | Tipo | Obrigatório |
|-------|-------|------|-------------|
| E-mail | operador@gmail.com | String | Sim |
| Senha | operador | String | Sim |
| Perfil esperado | OPERADOR | Enum | N/A |
| URL após login | /motos | String | Sim |

**Pré-condições:**
- Sistema em execução
- Usuário operador cadastrado (Migration V2)
- Permissões de OPERADOR configuradas

**Dados de teste no banco (Migration V2):**
```sql
INSERT INTO tb_mtt_usuario_c3_java (id_usuario, nm_usuario, ds_email, ds_senha, tp_usuario)
VALUES (2, 'Operador', 'operador@gmail.com', '$2a$10$...', 'OPERADOR');
```

### **3) Dados de Saída Esperados**
| Campo | Valor Esperado | Tipo |
|-------|----------------|------|
| Login - Status HTTP | 302 (Redirect) | Integer |
| Login - URL destino | http://localhost:8080/ | String |
| Motos - Status HTTP | 200 OK | Integer |
| Motos - Tabela presente | true | Boolean |
| Motos - Dados visíveis | true | Boolean |
| Perfil na sessão | OPERADOR | Enum |

**Critérios de Sucesso:**
- ✅ Login realizado com sucesso
- ✅ Redirecionamento para home
- ✅ Acesso à página /motos permitido
- ✅ Tabela de motos renderizada
- ✅ Sem erro 403 (Forbidden)

### **4) Procedimento de Teste (Passos)**

**Passo 1:** Acessar página de login
- Ação: Navegar para `http://localhost:8080/login`
- Resultado esperado: Formulário de login exibido

**Passo 2:** Preencher credenciais do operador
- Ação: Digitar `operador@gmail.com` no campo e-mail
- Resultado esperado: Texto inserido corretamente

**Passo 3:** Inserir senha
- Ação: Digitar `operador` no campo senha
- Resultado esperado: Senha mascarada

**Passo 4:** Realizar login
- Ação: Clicar em "Entrar"
- Resultado esperado: Redirecionamento para home

**Passo 5:** Verificar perfil
- Ação: Observar mensagem de boas-vindas ou menu
- Resultado esperado: Nome "Operador" ou perfil exibido

**Passo 6:** Acessar motos
- Ação: Navegar para `/motos`
- Resultado esperado: Página carrega com status 200

**Passo 7:** Validar visualização
- Ação: Verificar tabela de motos
- Resultado esperado: Dados são exibidos (permissão de leitura OK)

**Passo 8:** Verificar limitações (opcional)
- Ação: Tentar acessar `/usuarios`
- Resultado esperado: Acesso negado (403) - apenas ADMIN

**Status:** ✅ PASS | ❌ FAIL | ⏸️ BLOCKED

---

## 📋 TESTE 6: Admin Acessa Gestão de Usuários

### **1) Teste Planejado**
- **ID:** TC-006
- **Nome:** Validar Acesso Restrito à Gestão de Usuários
- **Objetivo:** Verificar se apenas usuários ADMIN conseguem acessar a página de gestão de usuários
- **Prioridade:** Alta
- **Tipo:** Funcional - Segurança
- **Módulo:** Gestão de Usuários

### **2) Dados de Entrada**
| Campo | Valor | Tipo | Obrigatório |
|-------|-------|------|-------------|
| Usuário logado | admin@gmail.com | String | Sim |
| Perfil do usuário | ADMIN | Enum | Sim |
| URL de acesso | /usuarios | String | Sim |

**Pré-condições:**
- Usuário ADMIN autenticado
- Pelo menos 2 usuários no banco (admin e operador)
- Rota /usuarios protegida por @PreAuthorize("hasRole('ADMIN')")

**Dados de teste no banco:**
```sql
-- Usuário 1: Admin
INSERT INTO tb_mtt_usuario_c3_java VALUES (1, 'Admin', 'admin@gmail.com', '...', 'ADMIN');

-- Usuário 2: Operador
INSERT INTO tb_mtt_usuario_c3_java VALUES (2, 'Operador', 'operador@gmail.com', '...', 'OPERADOR');
```

### **3) Dados de Saída Esperados**
| Campo | Valor Esperado | Tipo |
|-------|----------------|------|
| Status HTTP | 200 OK | Integer |
| Acesso autorizado | true | Boolean |
| Elemento HTML | `<table>` presente | HTML Element |
| Usuário admin visível | admin@gmail.com | String |
| Usuário operador visível | operador@gmail.com | String |
| Total de usuários | >= 2 | Integer |
| Botão "Novo Usuário" | presente | Boolean |

**Critérios de Sucesso:**
- ✅ Página carrega sem erro 403
- ✅ Tabela de usuários é exibida
- ✅ Todos os usuários cadastrados aparecem
- ✅ E-mails e perfis estão visíveis
- ✅ Opções de edição/exclusão disponíveis

### **4) Procedimento de Teste (Passos)**

**Passo 1:** Autenticar como ADMIN
- Ação: Login com admin@gmail.com/admin
- Resultado esperado: Sessão ADMIN criada

**Passo 2:** Navegar para gestão de usuários
- Ação: Acessar `http://localhost:8080/usuarios`
- Resultado esperado: Requisição GET enviada

**Passo 3:** Verificar autorização
- Ação: Aguardar resposta do servidor
- Resultado esperado: Status 200 (acesso concedido)

**Passo 4:** Validar estrutura da página
- Ação: Verificar presença de `<table>` no DOM
- Resultado esperado: Tabela de usuários presente

**Passo 5:** Verificar usuário admin
- Ação: Buscar linha com "admin@gmail.com"
- Resultado esperado: Linha encontrada com perfil "ADMIN"

**Passo 6:** Verificar usuário operador
- Ação: Buscar linha com "operador@gmail.com"
- Resultado esperado: Linha encontrada com perfil "OPERADOR"

**Passo 7:** Validar funcionalidades
- Ação: Verificar botões/links de ação (Editar, Excluir, Novo)
- Resultado esperado: Controles administrativos presentes

**Passo 8:** Teste negativo (opcional)
- Ação: Fazer logout, login como operador e tentar acessar /usuarios
- Resultado esperado: Erro 403 Forbidden (acesso negado)

**Status:** ✅ PASS | ❌ FAIL | ⏸️ BLOCKED

---

## 📊 Matriz de Rastreabilidade

| ID Teste | Funcionalidade | Sprint | User Story | Prioridade |
|----------|----------------|--------|------------|------------|
| TC-001 | Autenticação ADMIN | Sprint 3 | US-001: Login de usuários | Alta |
| TC-002 | Listagem de Motos | Sprint 2 | US-002: Visualizar motos | Alta |
| TC-003 | Histórico | Sprint 4 | US-005: Consultar histórico | Média |
| TC-004 | Gestão de Vagas | Sprint 2 | US-003: Gerenciar vagas | Alta |
| TC-005 | Autenticação OPERADOR | Sprint 3 | US-001: Login de usuários | Alta |
| TC-006 | Gestão de Usuários | Sprint 3 | US-004: Administrar usuários | Alta |

---

## 📈 Cobertura de Testes

### Módulos Cobertos:
- ✅ Autenticação (2 testes - perfis ADMIN e OPERADOR)
- ✅ Gestão de Motos (1 teste)
- ✅ Gestão de Vagas (1 teste)
- ✅ Histórico de Movimentações (1 teste)
- ✅ Gestão de Usuários (1 teste)

### Tipos de Teste:
- 🔐 Segurança e Autorização: 3 testes
- 📋 Funcionalidade de Leitura: 4 testes
- ✅ Validação de Dados: 6 testes

### Status Geral:
- Total de testes planejados: **6**
- Prioridade Alta: **5**
- Prioridade Média: **1**

---

## 🎯 Critérios de Aceitação Gerais

1. **Todos os testes devem passar** sem erros
2. **Dados controlados** conforme migrations aplicadas
3. **Perfis de usuário** devem respeitar permissões
4. **Tempo de resposta** < 3 segundos por requisição
5. **UI responsiva** e sem erros de console
6. **Sessões** devem ser mantidas corretamente

---

## 📝 Notas de Execução

**Ambiente de Teste:**
- Sistema: Windows 11
- Navegador: Google Chrome (versão atual)
- Java: 21.0.6
- Spring Boot: 3.5.6
- Banco: H2 in-memory + Oracle

**Pré-requisitos para Execução:**
```bash
# Iniciar aplicação
java -jar target/eficientiza-challenge-03-0.0.1-SNAPSHOT.jar

# OU via Maven
mvn spring-boot:run
```

**Ordem Recomendada de Execução:**
1. TC-001 (Login ADMIN)
2. TC-002 (Listagem Motos)
3. TC-004 (Gestão Vagas)
4. TC-003 (Histórico)
5. TC-006 (Gestão Usuários)
6. TC-005 (Login OPERADOR)

---

## ✅ Checklist de Validação

- [ ] Todos os 6 testes planejados estão documentados
- [ ] Dados de entrada especificados para cada teste
- [ ] Dados de saída esperados definidos
- [ ] Procedimentos (passos) detalhados para cada teste
- [ ] Dados controlados e predefinidos (migrations)
- [ ] Testes alinhados com sprints e user stories
- [ ] Rastreabilidade estabelecida
- [ ] Critérios de aceitação definidos

**Data de Criação:** 09/11/2025  
**Responsável:** Equipe Eficientiza  
**Versão:** 1.0
