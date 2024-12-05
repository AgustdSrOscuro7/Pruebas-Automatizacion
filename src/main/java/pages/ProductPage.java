package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Constructor
    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(8)); // Espera explícita de 8 segundos
    }

    // Localizadores
    private final By searchBox = By.name("search");
    private final By searchButton = By.cssSelector(".btn-default.btn-lg");
    private final By productLink = By.cssSelector("h4 a");
    private final By addToCartButton = By.id("button-cart");
    private final By cartSuccessMessage = By.cssSelector(".alert-success");
    private final By quantityField = By.name("quantity"); // Localizador para el campo de cantidad (si existe)

    // Métodos
    public void searchProduct(String productName) {
        WebElement searchField = driver.findElement(searchBox);
        searchField.clear(); // Limpiar caja de búsqueda
        searchField.sendKeys(productName); // Escribir nombre del producto
        driver.findElement(searchButton).click(); // Clic en buscar

        // Esperar hasta que los resultados se carguen
        wait.until(ExpectedConditions.visibilityOfElementLocated(productLink));
    }

    public void addProductToCart(String productName, int quantity) {
        // Buscar todos los productos que coincidan
        List<WebElement> productList = driver.findElements(productLink);
        WebElement selectedProduct = null;

        // Buscar el producto que coincida con el nombre dado
        for (WebElement product : productList) {
            if (product.getText().equalsIgnoreCase(productName)) {
                selectedProduct = product;
                break; // Salir del bucle una vez encontrado el producto
            }
        }

        if (selectedProduct != null) {
            selectedProduct.click(); // Seleccionar el producto

            // Verificar si existe un campo de cantidad
            try {
                WebElement quantityElement = driver.findElement(quantityField);
                if (quantityElement != null) {
                    quantityElement.clear(); // Limpiar el campo de cantidad
                    quantityElement.sendKeys(String.valueOf(quantity)); // Establecer la cantidad deseada
                }
            } catch (Exception e) {
                System.err.println("No se encontró campo de cantidad, usando la cantidad predeterminada.");
            }

            // Agregar el producto al carrito
            wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(cartSuccessMessage)); // Verificar el mensaje de éxito
        } else {
            System.err.println("Producto no encontrado: " + productName);
        }
    }
}
