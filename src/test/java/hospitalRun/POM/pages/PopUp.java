package hospitalRun.POM.pages;

import hospitalRun.POM.Application;
import hospitalRun.POM.pages.common.BasePage;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Getter
public class PopUp extends BasePage {

    private final By okButton = By.xpath("//div//button[text()='Ok']");
    private final By crossButton = By.cssSelector(".octicon.octicon-x");
    private final By popUpSuccessMessage = By.xpath("//div[text()='The medication record has been saved.']");

    public PopUp(Application app) {
        super(app);
    }

    @Step("Is Pop Up displayed")
    public boolean isDisplayed() {
        return isElementPresent(okButton) && isElementPresent(crossButton);
    }

    @Step("Is PopUp contains Ok button")
    public boolean isOkButtonPresent() {
        return isElementPresent(okButton);
    }

    @Step("Is PopUp contains Cross button")
    public boolean isCrossButtonPresent() {
        return isElementPresent(crossButton);
    }

    @Step("Click Ok button")
    public void clickOkButton() {
        click(okButton);
    }

    public void waitForDisplayed() {
        wait.until(ExpectedConditions.presenceOfElementLocated(okButton));
        wait.until(ExpectedConditions.presenceOfElementLocated(crossButton));
    }
}
