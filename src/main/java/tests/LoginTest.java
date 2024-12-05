package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ExcelUtils;

public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/Users/valek/Downloads/chromedriver-win64/chromedriver-win64/chromedriver.exe");// Configuración del driver
        driver = new ChromeDriver();
        driver.manage().window().maximize(); // Maximizar ventana
        driver.get("https://opencart.abstracta.us/"); // URL del sitio
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testLogin() {
        // Navegar a la página de inicio de sesión
        loginPage.navigateToLogin();

        // Leer credenciales del archivo Excel
        String[] credentials = ExcelUtils.getLoginData("src/test/resources/testdata.xlsx");
        loginPage.login(credentials[0], credentials[1]);

        // Verificar nombre del usuario
        Assert.assertEquals(loginPage.getAccountName(), "Valeria", "El usuario no coincide.");

        // Reporte del test
        System.out.println("=== Reporte de login ===");
        System.out.println("Usuario autenticado: " + loginPage.getAccountName());
        System.out.println("Test Passed ✅");
    }

    @AfterClass
    public void teardown() {
        // Cerrar el navegador
        driver.quit();
    }
}
