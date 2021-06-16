package hospitalRun.POM.pages;

import hospitalRun.POM.Application;
import hospitalRun.POM.pages.common.BasePage;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.testng.AssertJUnit.assertEquals;

@Getter
public class LogInPage extends BasePage {

    private final By userNameField = By.cssSelector("#identification");
    private final By passwordField = By.cssSelector("#password");
    private final By signInButton = By.xpath("//button[contains(text(),'Sign in')]");
    private final By errorMessageSel = By.cssSelector(".form-signin-alert");

    public LogInPage(Application app) {
        super(app);
    }

    @Step("Users accessed to Sign In Page")
    public void open() {
        driver.get(baseUrl);
    }

    @Step("Fill Username and Password fields with correct credentials")
    public void logIn(String userName, String password) {
        type(userNameField, userName);
        type(passwordField, password);
        click(signInButton);
    }

    @Step("Pay attention to error message displayed")
    public String getErrorMessage() {
        return driver.findElement(errorMessageSel).getAttribute("innerText");
    }

    public void waitForDisplayed() {
        wait.until(ExpectedConditions.presenceOfElementLocated(userNameField));
        wait.until(ExpectedConditions.presenceOfElementLocated(passwordField));
        wait.until(ExpectedConditions.presenceOfElementLocated(signInButton));
    }
}
