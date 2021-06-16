package hospitalRun.pages;

import hospitalRun.pages.common.BasePage;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static hospitalRun.enums.Pages.PATIENT_LISTING_PAGE;
import static org.testng.AssertJUnit.assertEquals;

@Getter
public class PatientListeningPage extends BasePage {
    WebDriverWait wait = new WebDriverWait(driver, 10);

    @FindBy(xpath = "//h1[contains(text(),'Patient Listing')]")
    private WebElement patientListeningPageHeader;

    public PatientListeningPage(WebDriver driver) {
        super(driver);
    }

    @Step("User is logged in and Patient Listing page is displayed")
    public void isPatientListeningPageDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(this.patientListeningPageHeader));
        assertEquals(driver.getCurrentUrl(), PATIENT_LISTING_PAGE.getUrl());
    }
}
