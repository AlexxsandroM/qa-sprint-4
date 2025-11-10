# Script para executar os testes automatizados (PowerShell)
param(
    [switch]$Headless = $true
)

if ($Headless) {
    Write-Host "Executando testes em modo headless..."
    $env:SELENIUM_HEADLESS = "true"
    $jvmArg = "-Dselenium.headless=true"
    $stepArg = "-Dselenium.step.delay=500"
} else {
    Write-Host "Executando testes com navegador visível..."
    $env:SELENIUM_HEADLESS = "false"
    $jvmArg = "-Dselenium.headless=false"
    # aumentar delay entre passos para visualizar melhor
    $stepArg = "-Dselenium.step.delay=2000"
}

function Run-MavenWrapperOrGlobal {
    param(
        [string[]]$Args
    )

    if (Test-Path .\mvnw.cmd) {
        Write-Host "Tentando executar o Maven Wrapper (mvnw)..."
        Write-Host "Argumentos que serão passados ao mvnw: $Args"
        try {
            & .\mvnw.cmd @Args 2>&1 | Write-Host
            if ($LASTEXITCODE -eq 0) { return $true }
            Write-Host "mvnw terminou com código de saída $LASTEXITCODE" -ForegroundColor Yellow
        } catch {
            Write-Host "Falha ao executar mvnw: $_" -ForegroundColor Yellow
        }
    } else {
        Write-Host "Maven Wrapper (.\mvnw.cmd) não encontrado." -ForegroundColor Yellow
    }

    # Fallback: tentar usar mvn global se disponível
    $mvnCmd = Get-Command mvn -ErrorAction SilentlyContinue
    if ($mvnCmd) {
        Write-Host "Tentando usar o Maven instalado globalmente (mvn) como fallback..."
        Write-Host "Argumentos que serão passados ao mvn: $Args"
        try {
            mvn @Args 2>&1 | Write-Host
            if ($LASTEXITCODE -eq 0) { return $true }
            Write-Host "mvn terminou com código de saída $LASTEXITCODE" -ForegroundColor Yellow
        } catch {
            Write-Host "Falha ao executar mvn global: $_" -ForegroundColor Yellow
        }
    } else {
        Write-Host "Maven não encontrado no PATH." -ForegroundColor Yellow
    }

    return $false
}

Write-Host "Iniciando execucaoo dos testes via mvn/mvnw..."
if (Test-Path .\mvnw.cmd) {
    Write-Host "Usando mvnw (wrapper) com argumentos: test -DskipTests=false $jvmArg $stepArg"
    & .\mvnw.cmd test "-DskipTests=false" $jvmArg $stepArg 2>&1 | Write-Host
    $rc = $LASTEXITCODE
    if ($rc -eq 0) { Write-Host "Testes executados com sucesso via mvnw."; exit 0 }
    Write-Host "mvnw terminou com código de saida $rc" -ForegroundColor Yellow
}

Write-Host "Tentando usar mvn global com argumentos: test -DskipTests=false $jvmArg $stepArg"
try {
    mvn test "-DskipTests=false" $jvmArg $stepArg 2>&1 | Write-Host
    $rc = $LASTEXITCODE
    if ($rc -eq 0) { Write-Host "Testes executados com sucesso via mvn."; exit 0 }
    Write-Host "mvn terminou com código de saida $rc" -ForegroundColor Yellow
} catch {
    Write-Host "Falha ao executar mvn global: $_" -ForegroundColor Yellow
}

Write-Host "Nao foi possível executar os testes via mvnw nem mvn. Opcoes:" -ForegroundColor Red
Write-Host "  1) Instale o Maven e execute: mvn test" -ForegroundColor Yellow
Write-Host "  2) Restaure o Maven Wrapper (.mvn/wrapper/maven-wrapper.properties e maven-wrapper.jar) no projeto" -ForegroundColor Yellow
Write-Host "  3) Como alternativa temporária, empacote o jar e execute-o manualmente: mvn -DskipTests package ; java -jar target\*.jar (mas os testes JUnit iniciam o app automaticamente e preferem mvn test)." -ForegroundColor Yellow

