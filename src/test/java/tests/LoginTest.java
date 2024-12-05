package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ExcelUtils;

import java.util.ArrayList;
import java.util.List;

public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/Users/valek/Downloads/chromedriver-win64/chromedriver-win64/chromedriver.exe"); // Configuración del driver
        driver = new ChromeDriver();
        driver.manage().window().maximize(); // Maximizar ventana
        driver.get("https://opencart.abstracta.us/"); // URL del sitio
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testLogin() {
        // Listas para productos procesados correctamente y fallidos
        List<String> successProducts = new ArrayList<>();
        List<String> failedProducts = new ArrayList<>();

        // Navegar a la página de inicio de sesión
        loginPage.navigateToLogin();

        // Leer credenciales del archivo Excel
        String[] credentials = ExcelUtils.getLoginData("src/test/resources/testdata.xlsx");
        loginPage.login(credentials[0], credentials[1]);

        // Verificar nombre del usuario
        boolean isLoginSuccessful = loginPage.getAccountName().equals("Valeria");

        // Asegurarse de que el nombre del usuario sea correcto
        Assert.assertTrue(isLoginSuccessful, "El usuario no coincide.");

        // Agregar el resultado de la prueba a las listas correspondientes
        if (isLoginSuccessful) {
            successProducts.add("Login Test Exitoso");
        } else {
            failedProducts.add("Login Test Fallido");
        }

        // Generar reporte Excel
        try {
            ExcelUtils.createReportExcel(successProducts, failedProducts, "src/test/resources/test_results.xlsx");
        } catch (Exception e) {
            System.err.println("Error al generar el reporte Excel: " + e.getMessage());
        }

        // Reporte en consola
        System.out.println("=== Reporte de login ===");
        System.out.println("Usuario autenticado: " + loginPage.getAccountName());
        System.out.println("Test " + (isLoginSuccessful ? "Passed ✅" : "Failed ❌"));
    }

    @AfterClass
    public void teardown() {
        // Cerrar el navegador
        driver.quit();
    }
}
