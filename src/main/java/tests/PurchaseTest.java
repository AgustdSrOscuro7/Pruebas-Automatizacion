package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.CheckoutPage;
import pages.ProductPage;
import pages.LoginPage;
import utils.ExcelUtils;

import java.util.List;

public class PurchaseTest {
    private WebDriver driver;
    private ProductPage productPage;
    private CheckoutPage checkoutPage;
    private LoginPage loginPage;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/Users/valek/Downloads/chromedriver-win64/chromedriver-win64/chromedriver.exe"); // Configuración del driver
        driver = new ChromeDriver();
        driver.manage().window().maximize(); // Maximizar ventana
        driver.get("https://opencart.abstracta.us/"); // URL del sitio

        // Inicialización de páginas
        productPage = new ProductPage(driver);
        checkoutPage = new CheckoutPage(driver);
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testPurchaseFlow() {
        // Paso 1: Iniciar sesión
        String[] credentials = ExcelUtils.getLoginData("src/test/resources/testdata.xlsx");
        loginPage.navigateToLogin();
        loginPage.login(credentials[0], credentials[1]);
        System.out.println("Usuario autenticado: " + loginPage.getAccountName());

        // Paso 2: Procesar la compra
        List<String[]> products = ExcelUtils.getProducts("src/test/resources/testdata.xlsx");
        System.out.println("=== Reporte de compra ===");

        for (String[] product : products) {
            String productName = product[0];
            int quantity = Integer.parseInt(product[1]);
            System.out.println("Procesando producto: " + productName + " | Cantidad: " + quantity);

            try {
                productPage.searchProduct(productName); // Buscar producto
                productPage.addProductToCart(productName, quantity); // Agregar al carrito con la cantidad
                System.out.println("Producto agregado exitosamente: " + productName);
            } catch (Exception e) {
                System.err.println("Error al procesar el producto: " + productName + " | Detalles: " + e.getMessage());
            }
        }

        // Paso 3: Proceder al checkout
        try {
            checkoutPage.proceedToCheckout(); // Proceder al checkout
            checkoutPage.confirmOrder(); // Confirmar orden

            String confirmation = checkoutPage.getConfirmationMessage();
            Assert.assertEquals(confirmation, "Your order has been placed!", "Mensaje de confirmación incorrecto");
            System.out.println("Compra completada exitosamente. ✅");
        } catch (Exception e) {
            System.err.println("Error durante el proceso de checkout: " + e.getMessage());
        }
        System.out.println("===========================");
    }

    @AfterClass
    public void teardown() {
        // Cerrar el navegador
        driver.quit();
    }
}
