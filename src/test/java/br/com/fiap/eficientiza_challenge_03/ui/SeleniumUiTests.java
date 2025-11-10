package br.com.fiap.eficientiza_challenge_03.ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.extension.TestWatcher;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.Point;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SeleniumUiTests {

    @LocalServerPort
    private int port;

    private WebDriver driver;
    private WebDriverWait wait;
    // delay (ms) between steps to slow down the test for visual inspection
    private long stepDelay = 2000;

    @RegisterExtension
    TestWatcher screenshotOnFailure = new TestWatcher() {
        @Override
        public void testFailed(ExtensionContext context, Throwable cause) {
            try {
                if (driver instanceof TakesScreenshot) {
                    File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                    Path destDir = Paths.get("target", "surefire-reports", "screenshots");
                    Files.createDirectories(destDir);
                    String method = context.getTestMethod().map(m -> m.getName()).orElse("test");
                    Path dest = destDir.resolve(method + ".png");
                    Files.copy(src.toPath(), dest);
                    // save page source
                    Path html = destDir.resolve(method + ".html");
                    Files.writeString(html, driver.getPageSource(), StandardCharsets.UTF_8);
                }
            } catch (Exception ex) {
                // don't fail the test because of screenshot problems
                System.err.println("Falha ao gravar screenshot: " + ex.getMessage());
            }
        }
    };

    private String baseUrl() { return "http://localhost:" + port; }

    @BeforeAll
    static void setupAll() {
        // Will download the correct chromedriver binary for the environment
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setup() {
        ChromeOptions options = new ChromeOptions();
        // Use headless by default so tests run in CI; set system property to "false" to show browser
        boolean headless = Boolean.parseBoolean(System.getProperty("selenium.headless", "true"));
        if (headless) options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1280,800");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // read optional step delay system property
        try {
            stepDelay = Long.parseLong(System.getProperty("selenium.step.delay", "300"));
        } catch (Exception e) {
            stepDelay = 300;
        }

        // bring browser to foreground / visible area and maximize
        try {
            driver.manage().window().setPosition(new Point(0, 0));
            driver.manage().window().setSize(new Dimension(1280, 800));
            driver.manage().window().maximize();
        } catch (Exception ignored) {
        }
        // small pause so user can see the browser appear
        stepSleep();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            // Aguardar 6 segundos antes de fechar para visualização
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            driver.quit();
        }
    }

    private void login(String email, String password) {
        driver.get(baseUrl() + "/login");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
        stepSleep();
        driver.findElement(By.id("email")).sendKeys(email);
        stepSleep();
        driver.findElement(By.id("password")).sendKeys(password);
        stepSleep();
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        stepSleep();
    }

    private void stepSleep() {
        if (stepDelay > 0) {
            try {
                Thread.sleep(stepDelay);
            } catch (InterruptedException ignored) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @Test
    @DisplayName("1 - Login com usuário válido deve redirecionar para home")
    void testLoginSuccess() {
        login("admin@gmail.com", "admin");
        // Check home page content
        wait.until(ExpectedConditions.urlContains("/"));
        assertTrue(driver.getPageSource().contains("Selecione um módulo"));
    }

    @Test
    @DisplayName("2 - Acessar lista de motos e verificar presença de moto inicial")
    void testMotosListContainsInitialData() {
        login("admin@gmail.com", "admin");
        // Ir para /motos
        driver.get(baseUrl() + "/motos");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("table")));
        // A migration insere a placa ABC1234
        assertTrue(driver.getPageSource().contains("ABC1234"), "Tabela deve conter a placa ABC1234");
    }

    @Test
    @DisplayName("3 - Acessar página de histórico de motos (admin)")
    void testAcessarHistoricoMotos() {
        login("admin@gmail.com", "admin");
        // Ir para /historicos-moto
        driver.get(baseUrl() + "/historicos-moto");
        stepSleep();
        // Verificar que a página foi carregada com tabela
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("table")));
        assertTrue(driver.findElements(By.tagName("table")).size() > 0,
                   "Página de histórico deve conter tabela");
    }

    @Test
    @DisplayName("4 - Acessar página de vagas e verificar tabela")
    void testAcessarVagas() {
        login("admin@gmail.com", "admin");
        // Ir para /vagas
        driver.get(baseUrl() + "/vagas");
        stepSleep();
        // Verificar que a página foi carregada com tabela
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("table")));
        assertTrue(driver.findElements(By.tagName("table")).size() > 0, 
                   "Página de vagas deve conter tabela");
    }

    @Test
    @DisplayName("5 - Login com usuário operador e acessar listagem de motos")
    void testLoginOperadorAcessarMotos() {
        login("operador@gmail.com", "operador");
        // Verificar redirecionamento para home
        wait.until(ExpectedConditions.urlContains("/"));
        stepSleep();
        // Operador deve conseguir acessar /motos
        driver.get(baseUrl() + "/motos");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("table")));
        assertTrue(driver.findElements(By.tagName("table")).size() > 0,
                   "Operador deve conseguir acessar página de motos");
    }

    @Test
    @DisplayName("6 - Admin acessa página de gestão de usuários")
    void testAdminAcessarUsuarios() {
        login("admin@gmail.com", "admin");
        // Ir para /usuarios
        driver.get(baseUrl() + "/usuarios");
        stepSleep();
        // Verificar que a página foi carregada com tabela
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("table")));
        assertTrue(driver.getPageSource().contains("admin@gmail.com") || 
                   driver.getPageSource().contains("Usuários"),
                   "Página de usuários deve conter lista de usuários ou título");
    }
}
