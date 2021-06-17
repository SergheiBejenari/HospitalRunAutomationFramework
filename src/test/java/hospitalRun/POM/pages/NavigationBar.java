package hospitalRun.POM.pages;

import hospitalRun.POM.Application;
import hospitalRun.POM.pages.common.BasePage;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Getter
public class NavigationBar extends BasePage {

    private final By medicationDropDown = By.xpath("//a[@href='#/medication']//span[contains(@class,'mega-octicon octicon-file-text')]");
    private final By requestsButton = By.xpath("//a[@href= '#/medication'][contains(@class,'category-sub-item')]//span[contains(@class,'octicon octicon-chevron-right')]");
    private final By completedButton = By.xpath("//a[@href= '#/medication/completed'][contains(@class,'nav-link ember-view')]//span[@class='octicon octicon-chevron-right']");
    private final By newRequestButton = By.xpath("//a[@href= '#/medication/edit/new']");
    private final By returnMedicationButton = By.xpath("//a[@href= '#/medication/return/new']");

    @FindBy(css = ".mega-octicon.octicon-gear")
    private WebElement cogwheelButton;

    private final By logOutButton = By.cssSelector(".logout");

    public NavigationBar(Application app) {
        super(app);
    }

    @Step("User click to cogwheel button")
    public void clickCogwheelButton() {
        this.getCogwheelButton().click();
    }

    @Step("User click to LogOut button")
    public void clickLogOutButton() {
        driver.findElement(logOutButton).click();
    }

    @Step("User Clicks to Medication item in Left Navigation Menu")
    public void clickMedicationTab() {
        click(medicationDropDown);
    }

    @Step("Requests item")
    public boolean isRequestsButtonPresent() {
        return isElementPresent(requestsButton);
    }

    @Step("Completed item")
    public boolean isCompletedButtonPresent() {
        return isElementPresent(completedButton);
    }

    @Step("New Request")
    public boolean isNewMedicationButtonPresent() {
        return isElementPresent(newRequestButton);
    }

    @Step("Return Medication")
    public boolean isReturnMedicationButtonPresent() {
        return isElementPresent(returnMedicationButton);
    }

    @Step("User click to New Medication Request")
    public void clickNewMedicationRequest() {
        click(newRequestButton);
    }

    public void logOut() {
        wait.until(ExpectedConditions.elementToBeClickable(this.getCogwheelButton()));
        this.getCogwheelButton().click();
        driver.findElement(logOutButton).click();
    }

    public boolean isPresent() {
        return isElementPresent(medicationDropDown);
    }
}
