
# Integrantes

- Alexsandro Macedo: RM557068
- Leonardo Faria Salazar: RM557484
- Guilherme Felipe da Silva Souza: RM558282

# Eficientiza - Sistema de Gerenciamento de Motos

# Links

[Link do video]() - A FAZER

[Link do deploy](https://java-sprint-4-0k9h.onrender.com) - https://java-sprint-4-0k9h.onrender.com

## Descrição

O **Eficientiza** é um sistema de gerenciamento de motos que permite o cadastro, controle e monitoramento de motos e vagas. A aplicação permite que administradores (ADMIN) e operadores (OPERADOR) gerenciem e acompanhem as movimentações das motos no estacionamento, incluindo a visualização do histórico, a gestão das vagas e a administração dos usuários.

# Acesso ao sistema:

Para acessar o sistema pode ser usado as seguintes credenciais:

**Acesso como ADMIN**

- E-mail: admin@gmail.com

- Senha: admin

**Acesso como OPERADOR**

- E-mail: operador@gmail.com

- Senha: operador

### Imagens do sistema
<img width="1364" height="632" alt="image" src="https://github.com/user-attachments/assets/287e072a-362a-40e2-b1de-d8b5f1f6a724" />
<img width="1365" height="632" alt="image" src="https://github.com/user-attachments/assets/82cdf906-cfcf-413c-a54e-7bf720849648" />
<img width="1353" height="634" alt="image" src="https://github.com/user-attachments/assets/bd405154-677a-47b3-85b5-b7ad38d506bc" />
<img width="1352" height="638" alt="image" src="https://github.com/user-attachments/assets/a8f81465-89cc-4eea-8dbe-c4c7fbaadafc" />
<img width="1346" height="633" alt="image" src="https://github.com/user-attachments/assets/7fe0eba0-1eb8-408a-80d9-b8e5cd56b16c" />
<img width="1347" height="633" alt="image" src="https://github.com/user-attachments/assets/a93c2f7a-4e2e-4f6b-8e1d-24c53c64cb02" />







### Funcionalidades:

- **Cadastro de Motos**: Adicionar, editar e excluir motos.
- **Cadastro e Gestão de Vagas**: Monitoramento de vagas, status e a moto associada.
- **Histórico de Movimentações**: Acompanhamento do histórico de entrada e saída das motos.
- **Gestão de Usuários**: Administradores podem gerenciar usuários, atribuindo roles como ADMIN ou OPERADOR.
- **Login e Autenticação**: Sistema de autenticação baseado em **Spring Security**, com validação de senha e roles.

## Tecnologias Utilizadas

- **Spring Boot 3.x**
- **Spring Security** (para autenticação e autorização)
- **Thymeleaf** (para renderização de templates HTML)
- **Spring Data JPA** (para interação com o banco de dados)
- **H2 Database** (ou outro banco de dados configurável, como MySQL ou PostgreSQL)
- **Tailwind CSS** (para a estilização da interface)

## Estrutura do Projeto

A arquitetura do projeto segue o padrão **MVC** (Model-View-Controller), onde a lógica de negócios é separada da apresentação, e a interação com o banco de dados é feita por meio de **Repositories**.

### **Models**

As **models** representam as entidades do sistema e são responsáveis por mapear as tabelas do banco de dados.

- **`Usuario`**: Representa os usuários do sistema com os campos `id`, `nome`, `email`, `senha` e `tipo` (admin ou operador).
- **`Moto`**: Representa as motos, com informações como `placa`, `modelo`, `status`, etc.
- **`Vaga`**: Representa as vagas de estacionamento, com `id`, `status` de ocupação e a `motoId` associada.
- **`HistoricoMoto`**: Representa o histórico de movimentações das motos, incluindo a data de entrada e saída da moto.

### **Services**

Os **services** contêm a lógica de negócios e são responsáveis pela manipulação dos dados da aplicação.

- **`UsuarioService`**: Responsável por autenticar e gerenciar os usuários.
- **`MotoService`**: Manipula as operações de moto (adicionar, editar, excluir).
- **`VagaService`**: Realiza operações sobre as vagas de estacionamento.
- **`HistoricoMotoService`**: Registra e manipula as movimentações das motos.

### **Controllers**

Os **controllers** são responsáveis por mapear as requisições HTTP e interagir com os services para fornecer os dados corretos para as views.

- **`UsuarioController`**: Controla a página de login e a gestão de usuários.
- **`MotoController`**: Gerencia a listagem e os formulários para o cadastro de motos.
- **`VagaController`**: Gerencia a listagem e o controle de vagas.
- **`HistoricoMotoController`**: Controla o histórico de movimentações das motos.

### **Segurança**

A segurança é gerenciada pelo **Spring Security**. Ele utiliza autenticação baseada em **usuário e senha** com dois tipos de roles:
- **ADMIN**: Pode acessar todas as funcionalidades do sistema.
- **OPERADOR**: Tem acesso restrito, podendo visualizar e editar motos e vagas, mas não pode acessar páginas restritas como o gerenciamento de usuários.

### **Estrutura de Banco de Dados**

A aplicação utiliza um banco de dados relacional, e a estrutura de tabelas é composta por:

- **tb_mtt_usuario_c3_java**: Tabela de usuários, contendo informações como nome, e-mail, senha e tipo de usuário.
- **tb_mtt_moto_c3_java**: Tabela que armazena informações sobre as motos, como placa e modelo.
- **tb_mtt_vaga_c3_java**: Tabela de vagas de estacionamento, com o status de ocupação e a referência à moto associada.
- **tb_mtt_historico_moto_c3_java**: Tabela que registra o histórico de movimentações das motos no estacionamento.

## Instalação e Execução

### Pré-requisitos

Antes de executar o projeto, você precisará ter as seguintes ferramentas instaladas:

- **Java 17** ou superior
- **Maven** ou **Gradle** para gerenciar dependências
- **Banco de Dados** (H2, MySQL ou PostgreSQL)

### Passo 1: Clone o repositório

```bash
git clone https://github.com/seu-repositorio/eficientiza.git
```

### Passo 2: Navegue até o diretório do projeto

```bash
cd eficientiza
```

### Passo 3: Instalar as dependências e compilar o projeto

Se estiver usando **Maven**:

```bash
mvn clean install
```

Se estiver usando **Gradle**:

```bash
gradle build
```

### Passo 4: Configuração do banco de dados

O **Eficientiza** já vem configurado para usar o **H2 Database** para desenvolvimento, mas você pode alterar para outro banco de dados no arquivo **`application.properties`**.

Exemplo para usar **MySQL**:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/eficientiza
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
```

### Passo 5: Executar a aplicação

Para rodar a aplicação, execute o comando:

Se estiver usando **Maven**:

```bash
mvn spring-boot:run
```

Se estiver usando **Gradle**:

```bash
gradle bootRun
```

### Passo 6: Acessando a aplicação

A aplicação estará disponível em **http://localhost:8080**. Você pode acessar as seguintes URLs:

- **`/login`**: Página de login
- **`/home`**: Página inicial após login
- **`/motos`**: Lista de motos
- **`/vagas`**: Lista de vagas
- **`/usuarios`**: Gestão de usuários (somente para ADMIN)
- **`/historico-motos`**: Gestão de historico (somente para ADMIN)

### Estrutura de Arquivos

```plaintext
src/
├── main/
│   ├── java/
│   │   └── br/com/fiap/eficientiza_challenge_03/
│   │       ├── controller/  # Controllers
│   │       ├── model/       # Entidades (Models)
│   │       ├── repository/  # Repositórios JPA
│   │       ├── service/     # Services
│   │       └── config/      # Configurações do Spring Security
│   └── resources/
│       ├── db.migration/    # Versionamento do banco de dados com Flyway
│       ├── templates/       # Páginas Thymeleaf
│       ├── application.properties  # Configurações do banco de dados
│       └── static/          # Arquivos estáticos (CSS, JS, Imagens)
└── test/                   # Testes unitários e integração
```

## 🧪 Testes Automatizados com Selenium

Este projeto implementa **6 casos de teste automatizados** utilizando **Selenium WebDriver** e **JUnit 5** para validar os principais fluxos do sistema Eficientiza.

---

### 📋 **Caso de Teste 1: Login com Usuário Válido**

**Objetivo:** Validar que um usuário com credenciais corretas consegue autenticar-se no sistema.

**Pré-condições:**
- Sistema iniciado e acessível
- Usuário administrador cadastrado no banco de dados

**Passos do Teste:**
1. Acessar a URL da página de login (`/login`)
2. Localizar o campo de entrada de e-mail pelo ID `email`
3. Preencher o campo com o valor `admin@gmail.com`
4. Localizar o campo de entrada de senha pelo ID `password`
5. Preencher o campo com o valor `admin`
6. Localizar e clicar no botão "Entrar"
7. Aguardar o redirecionamento automático para a página inicial (`/`)

**Resultado Esperado:** 
- ✅ Sistema redireciona para a página inicial
- ✅ Página contém o texto "Selecione um módulo" (indicando sucesso no login)

**Validações Realizadas:**
- Redirecionamento correto após login
- Presença do elemento de boas-vindas na home

---

### 📋 **Caso de Teste 2: Listagem de Motos com Dados Iniciais**

**Objetivo:** Verificar que a página de listagem de motos carrega corretamente e exibe os dados iniciais cadastrados.

**Pré-condições:**
- Usuário autenticado no sistema
- Banco de dados contém dados iniciais (migrations aplicadas)
- Moto com placa "ABC1234" cadastrada

**Passos do Teste:**
1. Realizar login com credenciais válidas (`admin@gmail.com` / `admin`)
2. Navegar para a URL `/motos`
3. Aguardar o carregamento completo da página
4. Localizar elemento `<table>` no DOM

**Resultado Esperado:**
- ✅ Página `/motos` carrega sem erros
- ✅ Tabela HTML é renderizada corretamente
- ✅ Conteúdo da página contém a placa "ABC1234" (dado inicial)

**Validações Realizadas:**
- Presença do elemento `<table>`
- Existência da placa inicial "ABC1234" no HTML

---

### 📋 **Caso de Teste 3: Acesso à Página de Histórico de Motos**

**Objetivo:** Validar que usuários autenticados conseguem acessar a funcionalidade de histórico de movimentações.

**Pré-condições:**
- Usuário com perfil ADMIN autenticado
- Rota `/historicos-moto` configurada e acessível

**Passos do Teste:**
1. Realizar login como administrador
2. Navegar diretamente para `/historicos-moto`
3. Aguardar delay configurável (para visualização)
4. Verificar presença de elemento `<table>` no DOM

**Resultado Esperado:**
- ✅ Página carrega sem erro 403 (Forbidden) ou 404 (Not Found)
- ✅ Tabela de histórico é renderizada
- ✅ Usuário tem permissão para visualizar os dados

**Validações Realizadas:**
- Navegação bem-sucedida para a rota protegida
- Presença de pelo menos uma `<table>` no HTML

---

### 📋 **Caso de Teste 4: Acesso à Página de Gestão de Vagas**

**Objetivo:** Verificar que a funcionalidade de gestão de vagas está acessível e operacional.

**Pré-condições:**
- Usuário ADMIN autenticado
- Vagas cadastradas no banco de dados

**Passos do Teste:**
1. Autenticar como administrador
2. Navegar para `/vagas`
3. Aguardar carregamento da página
4. Verificar renderização da tabela de vagas

**Resultado Esperado:**
- ✅ Página de vagas carrega corretamente
- ✅ Tabela contendo as vagas é exibida
- ✅ Dados das vagas (ID, status, moto associada) estão visíveis

**Validações Realizadas:**
- Acesso autorizado à rota `/vagas`
- Presença de estrutura tabular no HTML
- Contagem de elementos `<table>` maior que zero

---

### � **Caso de Teste 5: Login com Usuário Operador e Acesso às Motos**

**Objetivo:** Validar que um usuário com perfil OPERADOR consegue autenticar-se e acessar a funcionalidade de listagem de motos.

**Pré-condições:**
- Sistema iniciado e acessível
- Usuário operador cadastrado (`operador@gmail.com` / `operador`)
- Permissões de operador configuradas corretamente

**Passos do Teste:**
1. Acessar a página de login (`/login`)
2. Preencher e-mail com `operador@gmail.com`
3. Preencher senha com `operador`
4. Clicar no botão "Entrar"
5. Aguardar redirecionamento para home
6. Navegar para `/motos`
7. Verificar carregamento da tabela

**Resultado Esperado:**
- ✅ Login realizado com sucesso
- ✅ Operador consegue acessar página de motos
- ✅ Tabela de motos é exibida corretamente

**Validações Realizadas:**
- Autenticação com perfil OPERADOR funcional
- Permissões de leitura para motos ativas
- Renderização da interface para usuário não-admin

---

### 📋 **Caso de Teste 6: Admin Acessa Gestão de Usuários**

**Objetivo:** Verificar que apenas usuários com perfil ADMIN conseguem acessar a página de gestão de usuários.

**Pré-condições:**
- Usuário administrador autenticado
- Rota `/usuarios` protegida por controle de acesso
- Usuários cadastrados no sistema

**Passos do Teste:**
1. Realizar login como administrador
2. Navegar para `/usuarios`
3. Aguardar carregamento da página
4. Verificar presença da tabela de usuários
5. Validar existência de dados de usuários (como `admin@gmail.com`)

**Resultado Esperado:**
- ✅ Página de usuários carrega sem erro de autorização
- ✅ Tabela contendo lista de usuários é exibida
- ✅ Dados sensíveis (e-mails, tipos) estão visíveis apenas para admin

**Validações Realizadas:**
- Controle de acesso baseado em role (ADMIN)
- Renderização completa da interface de gestão
- Presença de dados críticos do sistema

---

### �🚀 Como Executar os Testes

#### **Pré-requisitos**
Antes de executar os testes, certifique-se de ter instalado:
- ☕ **Java 17 ou superior** - [Download aqui](https://www.oracle.com/java/technologies/downloads/)
- 📦 **Maven 3.6+** - [Guia de instalação](https://maven.apache.org/install.html)
- 🌐 **Google Chrome** (versão atual) - [Download aqui](https://www.google.com/chrome/)
- 💻 **Terminal** - PowerShell (Windows), Terminal (Mac/Linux)

> **Nota:** O ChromeDriver é baixado automaticamente pelo WebDriverManager, não é necessário instalá-lo manualmente.

---

#### **🎯 Executar TODOS os Testes (Recomendado para demonstração)**

##### **Windows (PowerShell):**

Abra o PowerShell na pasta do projeto e execute:

**Com navegador visível (perfeito para gravar vídeo):**
```powershell
mvn test -Dselenium.headless=false -Dselenium.step.delay=2000
```

**Sem mostrar o navegador (mais rápido):**
```powershell
mvn test -Dselenium.headless=true -Dselenium.step.delay=500
```

##### **Mac/Linux (Terminal):**

Abra o Terminal na pasta do projeto e execute:

**Com navegador visível:**
```bash
mvn test -Dselenium.headless=false -Dselenium.step.delay=2000
```

**Sem mostrar o navegador:**
```bash
mvn test -Dselenium.headless=true -Dselenium.step.delay=500
```

##### **Usando o script PowerShell (apenas Windows):**

```powershell
.\run-tests.ps1 -Headless:$false
```

---

#### **🎯 Executar Testes INDIVIDUAIS**

Para executar apenas um teste específico, use o parâmetro `-Dtest=`:

##### **Teste 1: Login com Usuário Válido**
```bash
mvn test -Dtest=SeleniumUiTests#testLoginSuccess -Dselenium.headless=false -Dselenium.step.delay=2000
```

##### **Teste 2: Listagem de Motos**
```bash
mvn test -Dtest=SeleniumUiTests#testMotosListContainsInitialData -Dselenium.headless=false -Dselenium.step.delay=2000
```

##### **Teste 3: Acesso ao Histórico de Motos**
```bash
mvn test -Dtest=SeleniumUiTests#testAcessarHistoricoMotos -Dselenium.headless=false -Dselenium.step.delay=2000
```

##### **Teste 4: Acesso à Página de Vagas**
```bash
mvn test -Dtest=SeleniumUiTests#testAcessarVagas -Dselenium.headless=false -Dselenium.step.delay=2000
```

##### **Teste 5: Login com Operador**
```bash
mvn test -Dtest=SeleniumUiTests#testLoginOperadorAcessarMotos -Dselenium.headless=false -Dselenium.step.delay=2000
```

##### **Teste 6: Admin Acessa Gestão de Usuários**
```bash
mvn test -Dtest=SeleniumUiTests#testAdminAcessarUsuarios -Dselenium.headless=false -Dselenium.step.delay=2000
```

---

#### **📝 Explicação dos Parâmetros**

| Parâmetro | Valores | Descrição |
|-----------|---------|-----------|
| `-Dselenium.headless` | `true` / `false` | `false` = mostra o navegador, `true` = roda em background |
| `-Dselenium.step.delay` | Número em milissegundos | Tempo de espera entre cada ação (ex: `2000` = 2 segundos) |
| `-Dtest` | Nome do teste | Executa apenas o teste especificado |

---

#### **🎬 Dicas para Gravação de Vídeo**

1. **Use o modo visível** com delay de 2 segundos:
   ```bash
   mvn test -Dselenium.headless=false -Dselenium.step.delay=2000
   ```

2. **Posicione o terminal e navegador lado a lado** para mostrar os dois na gravação

3. **Cada teste aguarda 6 segundos antes de fechar** para você ver o resultado final

4. **Tempo total de execução:** aproximadamente 2 minutos para os 6 testes

5. **Testes cobrem diferentes perfis:** ADMIN e OPERADOR para demonstrar controle de acesso

---

#### **❌ Solução de Problemas Comuns**

**Erro: "mvn não é reconhecido como comando"**
- Solução: Instale o Maven ou use `.\mvnw.cmd` (Windows) ou `./mvnw` (Mac/Linux)

**Erro: "ChromeDriver version mismatch"**
- Solução: Atualize o Google Chrome para a versão mais recente

**Erro: "Port already in use"**
- Solução: Feche qualquer aplicação Java rodando na porta 8080

**Testes falham sem motivo aparente**
- Solução: Aumente o delay para `3000` ou `4000` ms se sua máquina for mais lenta

---

### 📊 Arquitetura dos Testes

**Localização:** `src/test/java/br/com/fiap/eficientiza_challenge_03/ui/SeleniumUiTests.java`

**Nome dos Testes Implementados:**
1. `testLoginSuccess` - Login com usuário válido (ADMIN)
2. `testMotosListContainsInitialData` - Listagem de motos com dados iniciais
3. `testAcessarHistoricoMotos` - Acesso ao histórico de movimentações
4. `testAcessarVagas` - Acesso à página de vagas
5. `testLoginOperadorAcessarMotos` - Login com perfil OPERADOR
6. `testAdminAcessarUsuarios` - Gestão de usuários (apenas ADMIN)

**Recursos Técnicos:**
- 🔧 `@SpringBootTest` com porta aleatória (isolamento de testes)
- 🌐 WebDriverManager para gerenciamento automático do ChromeDriver
- 📸 Captura automática de screenshots em falhas
- 💾 Salvamento do HTML da página em caso de erro
- ⏱️ Delay configurável via system property (2s entre ações, 6s antes de fechar)
- 🧹 Cleanup automático do WebDriver após cada teste
- 🔐 Testes de autenticação com diferentes perfis (ADMIN e OPERADOR)

**Estrutura de Hooks:**
```java
@BeforeEach → Inicializa WebDriver + Posiciona janela
@AfterEach  → Aguarda 6s + Encerra WebDriver
@RegisterExtension → Captura screenshots em falhas
```

---

#### **✅ Resultado Esperado ao Executar**

Quando você executar os testes, verá no terminal:

```
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running br.com.fiap.eficientiza_challenge_03.ui.SeleniumUiTests

✓ testLoginSuccess (passou)
✓ testMotosListContainsInitialData (passou)
✓ testAcessarHistoricoMotos (passou)
✓ testAcessarVagas (passou)
✓ testLoginOperadorAcessarMotos (passou)
✓ testAdminAcessarUsuarios (passou)

[INFO] Tests run: 6, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```
✓ testAcessarVagas (passou)

[INFO] Tests run: 4, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

---

### 📸 Artefatos de Teste

**Screenshots e HTML de falhas:**
```
target/surefire-reports/screenshots/
├── testLoginSuccess-2025-11-09.png
└── testLoginSuccess-2025-11-09.html
```

**Relatórios Surefire:**
```
target/surefire-reports/
├── TEST-*.xml
└── *.txt
```

## Procedures

## 🧩 1. `prc_listar_ocupacoes_json`

### 📝 Descrição
Gera um **array JSON** contendo todas as ocupações registradas no sistema – incluindo informações de estações, vagas, motos e usuários.  
A procedure monta manualmente a estrutura JSON em CLOB e retorna pelo parâmetro de saída `p_json_out`.

### 🧠 Estrutura
```sql
CREATE OR REPLACE PROCEDURE prc_listar_ocupacoes_json(
    p_estacao_id     IN NUMBER,
    p_somente_ativas IN CHAR,
    p_limit          IN PLS_INTEGER,
    p_json_out       OUT CLOB
)
```

### 📤 Exemplo de Saída
```json
[{
  "id_ocupacao": 3,
  "dt_entrada": "2025-09-20T10:30:00",
  "dt_saida": "",
  "id_vaga": 3,
  "ds_vaga": "V03",
  "id_estacao": 1,
  "nm_estacao": "Estacao X01",
  "id_moto": 3,
  "ds_placa": "AAA1B03",
  "nm_modelo": "Modelo 03",
  "id_usuario": 3,
  "nm_usuario": "Usuario 03"
}]
```

### ⚡ Exemplo de Execução
```sql
DECLARE
  v_json CLOB;
BEGIN
  prc_listar_ocupacoes_json(
    p_estacao_id     => NULL,
    p_somente_ativas => 'S',
    p_limit          => 10,
    p_json_out       => v_json
  );
  DBMS_OUTPUT.PUT_LINE(v_json);
END;
```

### 💻 Consumo no Java
A aplicação consome esta procedure através do **OcupacaoSpRepository** e **OcupacaoService**.

```java
String json = ocupacaoSpRepository.listarOcupacoesJson(estacaoId, somenteAtivas, limit);
List<OcupacaoDto> lista = objectMapper.readValue(json, new TypeReference<>() {});
```

---

## 📊 2. `prc_resumo_ocupacao_minutos`

### 📝 Descrição
Produz um **resumo agregado de minutos ocupados** por combinação de **(Estação, Vaga)**.  
Realiza soma manual dos tempos de entrada/saída das ocupações e exibe o resultado via `DBMS_OUTPUT`.

### 🧠 Estrutura
```sql
CREATE OR REPLACE PROCEDURE prc_resumo_ocupacao_minutos IS
  CURSOR c_fato IS
    SELECT
      e.id_estacao      AS cat1_estacao,
      v.id_vaga         AS cat2_vaga,
      (NVL(ov.dt_saida, SYSDATE) - ov.dt_entrada) * 24 * 60 AS minutos
    FROM tb_mtt_ocupacao_vaga ov
    JOIN tb_mtt_vaga v    ON v.id_vaga = ov.id_vaga
    JOIN tb_mtt_estacao e ON e.id_estacao = v.id_estacao
    ORDER BY e.id_estacao, v.id_vaga;
BEGIN
  DBMS_OUTPUT.PUT_LINE('CAT1_ESTACAO | CAT2_VAGA | MINUTOS');
  FOR r IN c_fato LOOP
    DBMS_OUTPUT.PUT_LINE(r.cat1_estacao || ' | ' || r.cat2_vaga || ' | ' || TO_CHAR(ROUND(NVL(r.minutos,0),2)));
  END LOOP;
EXCEPTION
  WHEN NO_DATA_FOUND THEN DBMS_OUTPUT.PUT_LINE('Sem dados suficientes.');
  WHEN VALUE_ERROR THEN DBMS_OUTPUT.PUT_LINE('Erro de conversão/valor.');
  WHEN OTHERS THEN DBMS_OUTPUT.PUT_LINE('Erro inesperado: '||SQLERRM);
END;
```

### 📤 Exemplo de Saída
```
CAT1_ESTACAO | CAT2_VAGA | MINUTOS
1 | 3 | 68284.22
1 | 4 | 25912.89
2 | 1 | 1500.50
```

### 💻 Consumo no Java
O `ResumoOcupacaoRepository` executa a procedure via JDBC e lê as linhas do `DBMS_OUTPUT`.

```java
try (CallableStatement cs = con.prepareCall("{call prc_resumo_ocupacao_minutos}")) {
    cs.execute();
}
```

Cada linha é mapeada para o DTO:
```java
public record LinhaResumo(Integer estacao, Integer vaga, BigDecimal minutos) {}
```

---

## 🧭 Resumo Geral

| Procedure | Tipo de Saída | Uso Principal | Consumo Java | Exibição |
|------------|----------------|----------------|----------------|------------|
| `prc_listar_ocupacoes_json` | JSON (CLOB) | Listar ocupações detalhadas | `OcupacaoSpRepository` + `OcupacaoService` | Thymeleaf – Listar Ocupações |
| `prc_resumo_ocupacao_minutos` | DBMS_OUTPUT | Resumo por estação/vaga | `ResumoOcupacaoRepository` + `ResumoOcupacaoService` | Thymeleaf – Resumo de Ocupações |

