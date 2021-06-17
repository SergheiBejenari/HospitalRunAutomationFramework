package hospitalRun.POM.pages;

import hospitalRun.POM.Application;
import hospitalRun.POM.pages.common.BasePage;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElements;

@Getter
public class MedicationPage extends BasePage {
    short millis = 500;
    private final By patientField = By.cssSelector("input[id*=patientTypeAhead]");
    private final By visitDropDown = By.cssSelector("select[id*=visit]");
    private final By medicationField = By.cssSelector("input[id*=inventoryItemTypeAhead]");
    private final By prescriptionField = By.cssSelector("textarea[id*=prescription]");
    private final By prescriptionDateField = By.cssSelector("input[id*=prescription]");
    private final By quantityRequestedField = By.cssSelector("input[id*=quantity]");
    private final By refillsField = By.cssSelector("input[id*=refills]");
    private final By addButton = By.cssSelector(".panel-footer .on-white");
    private final By patientOptions = By.cssSelector(".test-patient-input .tt-suggestion");
    @FindBy(css = ".test-medication-input .tt-suggestion")
    private List<WebElement> medicationOptions;

    public MedicationPage(Application app) {
        super(app);
    }

    @Step("Type info in Patient field")
    public void typeIntoPatientField(String text) {
        String[] chars = text.split("(?!^)");
        Actions actions = new Actions(driver);
        actions.pause(1000).click(driver.findElement(patientField)).build().perform();
        for (String aChar : chars) {
            actions.sendKeys(aChar).pause(millis).build().perform();
        }
    }

    @Step("Select patient")
    public void selectPatient(String patient) {
        wait.until(presenceOfElementLocated(patientOptions));
        List<WebElement> options = driver.findElements(patientOptions);
        options.stream()
                .filter(o -> o.getAttribute("innerText").equals(patient))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(String.format("Option with %s text was not found in the list of patient options", patient)))
                .click();
    }

    @Step("click to Visit field")
    public void clickVisitField() {
        click(visitDropDown);
    }

    @Step("Select available date from Visit dropDown")
    public void selectAvailableDateFromVisitDropDown() {
        Select dropDown = new Select(driver.findElement(visitDropDown));
        wait.until(driver -> (dropDown.getOptions().size() > 1));
        click(visitDropDown);
        dropDown.selectByIndex(2);
    }

    @Step("Type info in Medication field")
    public void typeIntoMedicationField(String text) {
        String[] chars = text.split("(?!^)");
        Actions actions = new Actions(driver);
        actions.pause(1000).click(driver.findElement(medicationField)).pause(millis).build().perform();
        for (String aChar : chars) {
            actions.sendKeys(aChar).pause(millis).build().perform();
        }
    }

    @Step("Select available date from Medication dropDown")
    public void selectAvailableDateFromMedicationDropDown() {
        wait.until(visibilityOfAllElements(medicationOptions));
        medicationOptions.stream()
                .filter(o -> o.getAttribute("innerText").contains("available"))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No option with \"available\" text was found in the list of medication options"))
                .click();
    }

    @Step("Type info in Prescription field")
    public void typeIntoPrescriptionField(String prescription) {
        type(prescriptionField, prescription);
    }

    @Step("Type info in Prescription Date one date before current date")
    public void typeIntoPrescriptionDate(String date) {
        driver.findElement(prescriptionDateField).clear();
        type(prescriptionDateField, date);
    }

    @Step("Type random number in range from 1 to 5 into Quantity Requested field")
    public void typeIntoQuantityRequestedField(String text) {
        type(quantityRequestedField, text);
    }

    @Step("Type random number in range from 5 to 10 into Refills field")
    public void typeIntoRefillsField(String text) {
        type(refillsField, text);
    }

    @Step("Click to Add button")
    public void clickToAddButton() {
        click(addButton);
    }

    public boolean isDisplayed() {
        return isElementPresent(patientField)
                && isElementPresent(prescriptionDateField)
                && isElementPresent(quantityRequestedField)
                && isElementPresent(refillsField);
    }
}
