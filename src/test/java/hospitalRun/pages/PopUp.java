package hospitalRun.pages;

import hospitalRun.pages.common.BasePage;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.AssertJUnit.*;

@Getter
public class PopUp extends BasePage {
    WebDriverWait wait = new WebDriverWait(driver, 10);

    @FindBy(xpath = "//h4[normalize-space()='Medication Request Saved']")
    private WebElement popUpHeader;

    @FindBy(xpath = "//*[text()='Ok']")
    private WebElement okButton;

    @FindBy(xpath = "//span[@class='octicon octicon-x']")
    private WebElement crossButton;

    @FindBy(xpath = "//div[text()='The medication record has been saved.']")
    private WebElement popUpSuccessMessage;

    public PopUp(WebDriver driver) {
        super(driver);
    }

    @Step("Is Pop Up displayed")
    public void isPopUpDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(this.getPopUpSuccessMessage()));
        assertEquals(this.getPopUpSuccessMessage().getText(), "The medication record has been saved.");
    }

    @Step("Is PopUp contains Ok button")
    public void isPopUpContainsOkButton() {
        assertTrue(this.isElementPresent("//*[text()='Ok']"));
    }

    @Step("Is PopUp contains Cross button")
    public void isPopUpContainsCrossButton() {
        assertTrue(this.isElementPresent("//span[@class='octicon octicon-x']"));
    }

    @Step("Click Ok button")
    public void clickOkButton() {
        this.getOkButton().click();
    }

    @Step("PopUp isn`t displayed")
    public void popUpIsNotDisplayed() {
        assertFalse(this.isElementPresent("//div[text()='The medication record has been saved.']"));
    }

    public boolean isElementPresent(String locator) {
        try {
            driver.findElement(By.xpath(locator));
            return true;
        } catch
        (NoSuchElementException ex) {
            return false;
        }
    }
}
