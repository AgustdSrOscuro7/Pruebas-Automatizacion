package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;

    // Localizadores
    private final By myAccount = By.linkText("My Account");
    private final By loginLink = By.linkText("Login");
    private final By emailField = By.id("input-email");
    private final By passwordField = By.id("input-password");
    private final By loginButton = By.cssSelector("input[value='Login']");
    private final By accountName = By.cssSelector("input#input-firstname.form-control");
    private final By editInformationLink = By.linkText("Edit Account");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Métodos para acciones de la página
    public void navigateToLogin() {
        driver.findElement(myAccount).click(); // Clic en "My Account"
        driver.findElement(loginLink).click(); // Clic en "Login"
    }

    public void login(String email, String password) {
        driver.findElement(emailField).sendKeys(email); // Introducir correo
        driver.findElement(passwordField).sendKeys(password); // Introducir contraseña
        driver.findElement(loginButton).click(); // Clic en "Login"
    }

    public String getAccountName() {
        // Navegar a la página de edición de cuenta
        driver.findElement(editInformationLink).click();

        // Esperar hasta que el campo esté visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(accountName));

        // Retornar el valor del campo "First Name"
        return driver.findElement(accountName).getAttribute("value");
    }
}
