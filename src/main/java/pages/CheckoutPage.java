package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Constructor
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Configuración de espera explícita
    }

    // Localizadores
    private final By cartButton = By.cssSelector(".btn-inverse");
    private final By viewCartLink = By.cssSelector("div#cart.btn-group.btn-block");
    private final By checkoutButton = By.linkText("Checkout");
    private final By confirmBillingButton = By.cssSelector("input#button-payment-address.btn.btn-primary");
    private final By confirmDeliveryDetailButton = By.cssSelector("input#button-shipping-address.btn.btn-primary");
    private final By confirmDeliveryMethodButton = By.cssSelector("input#button-shipping-method.btn.btn-primary");
    private final By confirmTermsButton = By.cssSelector("input[type='checkbox'][name='agree']");
    private final By confirmPaymentMethodButton = By.cssSelector("input#button-payment-method.btn.btn-primary");
    private final By confirmOrderButton = By.cssSelector("input#button-confirm.btn.btn-primary");
    private final By confirmationMessage = By.cssSelector("div#common-success.container div.row div#content.col-sm-12 h1");

    // Métodos
    public void proceedToCheckout() {
        driver.findElement(cartButton).click(); // Abrir carrito
        driver.findElement(viewCartLink).click(); // Ver carrito
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)); // Esperar a que el botón de checkout sea clickeable
        driver.findElement(checkoutButton).click();
    }

    public void confirmOrder() {
        confirmStep(confirmBillingButton, "Confirmando dirección de facturación...");
        confirmStep(confirmDeliveryDetailButton, "Confirmando detalles de entrega...");
        confirmStep(confirmDeliveryMethodButton, "Confirmando método de entrega...");

        // Aceptar términos y condiciones
        wait.until(ExpectedConditions.elementToBeClickable(confirmTermsButton)).click();

        confirmStep(confirmPaymentMethodButton, "Confirmando método de pago...");
        confirmStep(confirmOrderButton, "Confirmando orden...");

        // Verificar mensaje de confirmación
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationMessage));
    }

    public String getConfirmationMessage() {
        return driver.findElement(confirmationMessage).getText(); // Obtener texto de confirmación
    }

    // Método auxiliar para confirmar cada paso
    private void confirmStep(By element, String message) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        System.out.println(message);
    }
}
