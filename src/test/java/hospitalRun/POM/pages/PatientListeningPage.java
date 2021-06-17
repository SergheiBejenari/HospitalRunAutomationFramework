package hospitalRun.POM.pages;

import hospitalRun.POM.Application;
import hospitalRun.POM.pages.common.BasePage;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

@Getter
public class PatientListeningPage extends BasePage {

    @FindBy(css = ".view-top-bar .view-current-title")
    private WebElement patientListeningPageHeader;

    public PatientListeningPage(Application app) {
        super(app);
    }

    @Step("Wait until Patient Listing page is displayed")
    public void waitForDisplayed() {
        wait.until(visibilityOf(this.patientListeningPageHeader));
    }
}
