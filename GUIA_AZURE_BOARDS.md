# 📘 Guia: Como Cadastrar os Testes no Azure Boards

## Passo a Passo para Configurar o Plano de Testes

---

## 1️⃣ Acessar Azure DevOps

1. Acesse: https://dev.azure.com/
2. Faça login com sua conta Microsoft
3. Selecione ou crie uma **Organization**
4. Selecione ou crie um **Project** chamado "Eficientiza" ou "Java-Sprint-4"

---

## 2️⃣ Configurar Azure Boards

### Criar Área de Trabalho

1. No menu lateral, clique em **Boards** → **Boards**
2. Clique em **⚙️ Project Settings** (canto inferior esquerdo)
3. Em **Boards**, selecione **Project configuration**
4. Crie as seguintes **Areas** (Áreas):
   - `Eficientiza/Autenticação`
   - `Eficientiza/Gestão de Motos`
   - `Eficientiza/Gestão de Vagas`
   - `Eficientiza/Histórico`
   - `Eficientiza/Gestão de Usuários`

### Criar Iterations (Sprints)

1. Em **Project configuration**, clique em **Iterations**
2. Crie as sprints:
   - `Sprint 2 - Gestão Básica`
   - `Sprint 3 - Autenticação e Usuários`
   - `Sprint 4 - Histórico e Relatórios`

---

## 3️⃣ Criar Test Plans (Planos de Teste)

1. No menu lateral, clique em **Test Plans**
2. Clique em **+ New Test Plan**
3. Preencha:
   - **Name:** Plano de Testes Manuais - Sistema Eficientiza
   - **Area Path:** Eficientiza
   - **Iteration:** Sprint 4
4. Clique em **Create**

---

## 4️⃣ Criar Test Suite (Suíte de Testes)

1. Dentro do Test Plan criado, clique em **+ New Suite** → **Static suite**
2. Nome: "Testes de Validação - Nível de Sistema"
3. Crie sub-suites para organizar:
   - "Autenticação e Autorização"
   - "Gestão de Motos"
   - "Gestão de Vagas"
   - "Histórico"
   - "Gestão de Usuários"

---

## 5️⃣ Cadastrar os 6 Test Cases (Casos de Teste)

### 🔹 TESTE 1: Login com Usuário Administrador (TC-001)

1. Na suite "Autenticação e Autorização", clique em **+ New Test Case**
2. Preencha os campos:

**Title (Título):**
```
TC-001 - Validar Login de Usuário Administrador
```

**Priority (Prioridade):** `1 - High`

**Assigned To:** Seu nome

**Area:** `Eficientiza/Autenticação`

**Iteration:** `Sprint 3`

**Steps (Passos):**

```
Step 1: Acessar o sistema
Action: Abrir navegador e navegar para http://localhost:8080/login
Expected Result: Página de login é exibida com campos e-mail e senha

Step 2: Preencher credenciais
Action: Digitar admin@gmail.com no campo "E-mail"
Expected Result: Texto é exibido no campo

Step 3: Preencher senha
Action: Digitar admin no campo "Senha"
Expected Result: Senha é mascarada (••••••)

Step 4: Submeter formulário
Action: Clicar no botão "Entrar"
Expected Result: Requisição POST enviada

Step 5: Validar redirecionamento
Action: Aguardar resposta do servidor
Expected Result: Navegador redireciona para http://localhost:8080/

Step 6: Verificar página inicial
Action: Observar conteúdo da página
Expected Result: Texto "Selecione um módulo" está presente
```

**Test Data (Dados de Teste):**
```
E-mail: admin@gmail.com
Senha: admin
Perfil: ADMIN
```

**Expected Result (Resultado Esperado):**
```
- Status HTTP: 302 (Redirect)
- URL destino: http://localhost:8080/
- Texto: "Selecione um módulo"
- Sessão criada com JSESSIONID
```

3. Clique em **Save & Close**

---

### 🔹 TESTE 2: Listagem de Motos (TC-002)

**Title:**
```
TC-002 - Validar Listagem de Motos Cadastradas
```

**Priority:** `1 - High`

**Area:** `Eficientiza/Gestão de Motos`

**Iteration:** `Sprint 2`

**Steps:**
```
Step 1: Realizar login
Action: Executar procedimento de login (TC-001)
Expected Result: Usuário autenticado com sucesso

Step 2: Navegar para página de motos
Action: Acessar http://localhost:8080/motos
Expected Result: Requisição GET para /motos é enviada

Step 3: Aguardar carregamento
Action: Esperar resposta do servidor
Expected Result: Página carrega com status 200

Step 4: Verificar estrutura da tabela
Action: Inspecionar DOM buscando elemento <table>
Expected Result: Tabela está presente no HTML

Step 5: Validar cabeçalho da tabela
Action: Verificar <thead> com colunas: Placa, Modelo, Cor, Ano, Status, Vaga, Ações
Expected Result: Todas as colunas estão presentes

Step 6: Verificar dados iniciais
Action: Buscar na tabela a placa "ABC1234"
Expected Result: Placa encontrada na primeira linha de dados

Step 7: Validar dados completos
Action: Verificar todos os campos da moto ABC1234
Expected Result: Modelo (Honda CG 160), cor (Vermelha), ano (2023), status (DISPONIVEL) e vaga (V01) corretos
```

**Test Data:**
```
Usuário: admin@gmail.com
URL: /motos
Placa esperada: ABC1234
Modelo esperado: Honda CG 160
Cor esperada: Vermelha
Ano esperado: 2023
Status esperado: DISPONIVEL
Vaga esperada: V01
```

**Expected Result:**
```
- Status HTTP: 200 OK
- Elemento <table> presente
- Placa ABC1234 visível
- Todos os dados da moto exibidos corretamente
```

---

### 🔹 TESTE 3: Histórico de Motos (TC-003)

**Title:**
```
TC-003 - Validar Acesso ao Histórico de Movimentações
```

**Priority:** `2 - Medium`

**Area:** `Eficientiza/Histórico`

**Iteration:** `Sprint 4`

**Steps:**
```
Step 1: Autenticar como ADMIN
Action: Realizar login com admin@gmail.com
Expected Result: Sessão ADMIN criada

Step 2: Acessar menu de histórico
Action: Navegar para http://localhost:8080/historicos-moto
Expected Result: Requisição GET enviada

Step 3: Verificar autorização
Action: Aguardar resposta HTTP
Expected Result: Status 200 (não 403 Forbidden)

Step 4: Validar estrutura da página
Action: Inspecionar elemento <table> no DOM
Expected Result: Tabela presente

Step 5: Verificar cabeçalhos
Action: Validar <thead> com colunas apropriadas
Expected Result: Colunas de histórico estão presentes

Step 6: Verificar dados (se existirem)
Action: Contar linhas na <tbody>
Expected Result: Se houver dados, são exibidos corretamente
```

**Test Data:**
```
Usuário: admin@gmail.com
Perfil: ADMIN
URL: /historicos-moto
```

**Expected Result:**
```
- Status HTTP: 200 OK
- Acesso autorizado (sem 403)
- Tabela de histórico renderizada
- Colunas: ID, Moto, Usuário, Ação, Data
```

---

### 🔹 TESTE 4: Gestão de Vagas (TC-004)

**Title:**
```
TC-004 - Validar Acesso e Listagem de Vagas
```

**Priority:** `1 - High`

**Area:** `Eficientiza/Gestão de Vagas`

**Iteration:** `Sprint 2`

**Steps:**
```
Step 1: Login no sistema
Action: Autenticar com admin@gmail.com
Expected Result: Sessão criada

Step 2: Navegar para vagas
Action: Acessar http://localhost:8080/vagas
Expected Result: Página de vagas carrega

Step 3: Verificar tabela
Action: Localizar elemento <table> no DOM
Expected Result: Tabela está presente

Step 4: Validar vaga V01
Action: Buscar linha com "V01" na tabela
Expected Result: Status "OCUPADA" e moto associada (ABC1234)

Step 5: Validar vagas livres
Action: Buscar linhas com status "LIVRE"
Expected Result: V02 e V03 aparecem como livres

Step 6: Verificar total
Action: Contar número de linhas na tabela
Expected Result: Pelo menos 3 vagas listadas
```

**Test Data:**
```
Usuário: admin@gmail.com
URL: /vagas
Vaga V01: OCUPADA com moto ABC1234
Vaga V02: LIVRE
Vaga V03: LIVRE
```

**Expected Result:**
```
- Status HTTP: 200 OK
- Tabela presente
- V01: OCUPADA com ABC1234
- V02 e V03: LIVRE
- Total >= 3 vagas
```

---

### 🔹 TESTE 5: Login Operador (TC-005)

**Title:**
```
TC-005 - Validar Login e Permissões do Perfil OPERADOR
```

**Priority:** `1 - High`

**Area:** `Eficientiza/Autenticação`

**Iteration:** `Sprint 3`

**Steps:**
```
Step 1: Acessar página de login
Action: Navegar para http://localhost:8080/login
Expected Result: Formulário de login exibido

Step 2: Preencher credenciais do operador
Action: Digitar operador@gmail.com no campo e-mail
Expected Result: Texto inserido corretamente

Step 3: Inserir senha
Action: Digitar operador no campo senha
Expected Result: Senha mascarada

Step 4: Realizar login
Action: Clicar em "Entrar"
Expected Result: Redirecionamento para home

Step 5: Verificar perfil
Action: Observar mensagem de boas-vindas ou menu
Expected Result: Nome "Operador" ou perfil exibido

Step 6: Acessar motos
Action: Navegar para /motos
Expected Result: Página carrega com status 200

Step 7: Validar visualização
Action: Verificar tabela de motos
Expected Result: Dados são exibidos (permissão de leitura OK)

Step 8: Verificar limitações
Action: Tentar acessar /usuarios
Expected Result: Acesso negado (403) - apenas ADMIN
```

**Test Data:**
```
E-mail: operador@gmail.com
Senha: operador
Perfil: OPERADOR
URL permitida: /motos (200 OK)
URL bloqueada: /usuarios (403 Forbidden)
```

**Expected Result:**
```
- Login bem-sucedido
- Acesso a /motos: permitido
- Acesso a /usuarios: negado (403)
- Perfil OPERADOR na sessão
```

---

### 🔹 TESTE 6: Gestão de Usuários (TC-006)

**Title:**
```
TC-006 - Validar Acesso Restrito à Gestão de Usuários
```

**Priority:** `1 - High`

**Area:** `Eficientiza/Gestão de Usuários`

**Iteration:** `Sprint 3`

**Steps:**
```
Step 1: Autenticar como ADMIN
Action: Login com admin@gmail.com/admin
Expected Result: Sessão ADMIN criada

Step 2: Navegar para gestão de usuários
Action: Acessar http://localhost:8080/usuarios
Expected Result: Requisição GET enviada

Step 3: Verificar autorização
Action: Aguardar resposta do servidor
Expected Result: Status 200 (acesso concedido)

Step 4: Validar estrutura da página
Action: Verificar presença de <table> no DOM
Expected Result: Tabela de usuários presente

Step 5: Verificar usuário admin
Action: Buscar linha com "admin@gmail.com"
Expected Result: Linha encontrada com perfil "ADMIN"

Step 6: Verificar usuário operador
Action: Buscar linha com "operador@gmail.com"
Expected Result: Linha encontrada com perfil "OPERADOR"

Step 7: Validar funcionalidades
Action: Verificar botões/links de ação (Editar, Excluir, Novo)
Expected Result: Controles administrativos presentes

Step 8: Teste negativo
Action: Logout, login como operador e tentar acessar /usuarios
Expected Result: Erro 403 Forbidden (acesso negado)
```

**Test Data:**
```
Usuário: admin@gmail.com
Perfil: ADMIN
URL: /usuarios
Usuários esperados:
  - admin@gmail.com (ADMIN)
  - operador@gmail.com (OPERADOR)
```

**Expected Result:**
```
- Status HTTP: 200 OK para ADMIN
- Status HTTP: 403 Forbidden para OPERADOR
- Tabela com >= 2 usuários
- Todos os e-mails e perfis visíveis
- Botões de ação presentes
```

---

## 6️⃣ Executar os Testes

1. Vá para **Test Plans** → Seu plano de testes
2. Clique em **Run** ao lado de cada test case
3. O Azure abrirá o **Test Runner**
4. Execute cada passo e marque como:
   - ✅ **Pass** (verde) - Passou
   - ❌ **Fail** (vermelho) - Falhou
   - ⏸️ **Blocked** (cinza) - Bloqueado
   - ⏭️ **Not Applicable** - Não aplicável

5. Ao finalizar, clique em **Save and Close**

---

## 7️⃣ Adicionar Resultados e Evidências

### Para cada teste executado:

1. Após marcar como Pass/Fail, adicione:
   - **Comments:** Observações sobre a execução
   - **Attachments:** Screenshots das telas testadas
   - **Bug:** Se falhar, crie um Bug linkado

2. Para adicionar screenshot:
   - Clique em **📎 Attach file**
   - Selecione a imagem capturada
   - Adicione descrição

---

## 8️⃣ Gerar Relatórios

1. No Test Plan, vá para **Charts**
2. Crie gráficos:
   - **Test Results Trend** (Tendência)
   - **Test Case Readiness** (Prontidão)
   - **Test Execution** (Execução)

3. Exporte relatório:
   - Menu **...** → **Export to PDF**

---

## 📊 Estrutura Final no Azure Boards

```
📁 Eficientiza (Project)
  └── 📋 Test Plans
      └── Plano de Testes Manuais - Sistema Eficientiza
          └── 📂 Testes de Validação - Nível de Sistema
              ├── 📂 Autenticação e Autorização
              │   ├── ✅ TC-001 - Login ADMIN
              │   └── ✅ TC-005 - Login OPERADOR
              ├── 📂 Gestão de Motos
              │   └── ✅ TC-002 - Listagem de Motos
              ├── 📂 Gestão de Vagas
              │   └── ✅ TC-004 - Listagem de Vagas
              ├── 📂 Histórico
              │   └── ✅ TC-003 - Histórico de Motos
              └── 📂 Gestão de Usuários
                  └── ✅ TC-006 - Admin Usuários
```

---

## ✅ Checklist Final

- [ ] Organization e Project criados no Azure DevOps
- [ ] Areas e Iterations configuradas
- [ ] Test Plan criado
- [ ] Test Suites organizadas
- [ ] 6 Test Cases cadastrados com:
  - [ ] Título descritivo
  - [ ] Priority definida
  - [ ] Steps detalhados
  - [ ] Test Data especificado
  - [ ] Expected Results definidos
- [ ] Testes executados e marcados (Pass/Fail)
- [ ] Screenshots anexados como evidência
- [ ] Relatório gerado

---

## 🎯 Dicas Importantes

1. **Use Tags** para organizar: `#autenticacao`, `#crud`, `#seguranca`
2. **Link com User Stories** sempre que possível
3. **Mantenha atualizado** após cada execução
4. **Adicione sempre screenshots** como evidência
5. **Documente falhas** com detalhes no Bug Tracker

---

**Documentação Azure DevOps:** https://learn.microsoft.com/en-us/azure/devops/test/

**Suporte:** Entre em contato com o time se precisar de ajuda!
