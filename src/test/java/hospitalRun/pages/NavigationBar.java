package hospitalRun.pages;

import hospitalRun.app.Application;
import hospitalRun.pages.common.BasePage;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static hospitalRun.enums.Pages.LOGIN_PAGE;
import static org.testng.AssertJUnit.assertTrue;

@Getter
public class NavigationBar extends BasePage {
    Application app = new Application();
    WebDriverWait wait = new WebDriverWait(driver, 10);

    @FindBy(xpath = "//a[@href='#/medication']")
    private WebElement medicationDropDown;

    @FindBy(linkText = "Requests")
    private WebElement requestsButton;

    @FindBy(linkText = "Completed")
    private WebElement completedButton;

    @FindBy(linkText = "New Request")
    private WebElement newRequestButton;

    @FindBy(linkText = "Return Medication")
    private WebElement returnMedicationButton;

    @FindBy(xpath = "//span[(contains(@class,'mega-octicon octicon-gear'))]")
    private WebElement cogwheelButton;

    @FindBy(xpath = "//a[(contains(@class,'logout'))]")
    private WebElement logOutButton;

    public NavigationBar(WebDriver driver) {
        super(driver);
    }

    @Step("User click to cogwheel button")
    public void clickToCogwheelButton() {
        wait.until(ExpectedConditions.elementToBeClickable(this.getCogwheelButton()));
        this.getCogwheelButton().click();
    }

    @Step("User click to LogOut button")
    public void clickToLogOutButton() {
        this.getLogOutButton().click();
    }

    @Step("User Clicks to Medication item in Left Navigation Menu")
    public void clickMedicationTab() {
        this.getMedicationDropDown().click();
    }

    @Step("Requests item")
    public void isMedicationSectionContainsRequestsItem() {
        this.isMedicationSectionContainsElement(this.getRequestsButton(), "Requests");
    }

    @Step("Completed item")
    public void isMedicationSectionContainsCompletedItem() {
        this.isMedicationSectionContainsElement(this.getCompletedButton(), "Completed");
    }

    @Step("New Request")
    public void isMedicationSectionContainsNewMedicationItem() {
        this.isMedicationSectionContainsElement(this.getNewRequestButton(), "New Request");
    }

    @Step("Return Medication")
    public void isMedicationSectionContainsReturnMedicationItem() {
        this.isMedicationSectionContainsElement(this.getReturnMedicationButton(), "Return Medication");
    }

    @Step("User click to New Medication Request")
    public void clickNewMedicationRequest() {
        this.getNewRequestButton().click();
    }

    @Step("Medication Section contains next Item:")
    public void isMedicationSectionContainsElement(WebElement element, String item) {
        assertTrue(element.getText().contains(item));
    }

    public void logOutIfUserLoggedIn() {
        if (!driver.getCurrentUrl().equals(LOGIN_PAGE.getUrl())) {
            wait.until(ExpectedConditions.elementToBeClickable(this.getCogwheelButton()));
            this.getCogwheelButton().click();
            this.getLogOutButton().click();
        }
    }
}
