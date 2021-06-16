package hospitalRun.pages;

import hospitalRun.pages.common.BasePage;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static hospitalRun.enums.Pages.HOME_PAGE;
import static hospitalRun.enums.Pages.LOGIN_PAGE;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

@Getter
public class LogInPage extends BasePage {

    WebDriverWait wait = new WebDriverWait(driver, 60);

    @FindBy(css = "#identification")
    private WebElement userNameField;

    @FindBy(css = "#password")
    private WebElement passwordField;

    @FindBy(xpath = "//button[contains(text(),'Sign in')]")
    private WebElement signInButton;

    @FindBy(xpath = "//div[contains(text(),'Username or password is incorrect.')]")
    private WebElement errorMessage;

    @FindBy(xpath = "//h2[contains(text(),'please sign in')]")
    private WebElement headerText;

    public LogInPage(WebDriver driver) {
        super(driver);
    }

    @Step("Users accessed to Sign In Page")
    public void open() {
        driver.get(HOME_PAGE.getUrl());
    }

    @Step("Fill Username and Password fields with correct credentials")
    public void logIn(String userName, String password) {
        userNameField.sendKeys(userName);
        passwordField.sendKeys(password);
        signInButton.click();
    }

    @Step("Fill Username and Password fields with invalid credentials")
    public void logInInvalidUser(String userName, String password) {
        this.logIn(userName, password);
    }

    @Step("User is stayed on Login Page")
    public void isUserStayedOnLoginPage() {
        assertEquals(driver.getCurrentUrl(), LOGIN_PAGE.getUrl());
    }

    @Step("Error message is displayed")
    public void isErrorMessageDisplayed() {
        assertTrue("Error message is displayed", this.getErrorMessage()
                .getText().contains("Username or password is incorrect"));
    }

    @Step("User is logged out and Login Page is displayed")
    public void isUserLoggedOut() {
        wait.until(ExpectedConditions.visibilityOf(this.getHeaderText()));
        assertEquals(driver.getCurrentUrl(), LOGIN_PAGE.getUrl());
    }

    public void isHeaderDisplayed() {
        wait.until(ExpectedConditions.visibilityOfAllElements(this.getHeaderText()));
    }
}
