package hospitalRun.pages;

import hospitalRun.pages.common.BasePage;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;

import static hospitalRun.enums.Pages.MEDICATION_PAGE;
import static org.testng.AssertJUnit.assertEquals;

@Getter
public class MedicationPage extends BasePage {
    WebDriverWait wait = new WebDriverWait(driver, 10);
    short millis = 500;

    @FindBy(css = ".test-patient-input input.tt-hint")
    private WebElement patientField;

    @FindBy(css = "div:nth-child(5) strong:nth-child(1)")
    private WebElement testPatientLine;

    @FindBy(xpath = "//select[contains(@id,'visit')]")
    private WebElement visitDropDown;

    @FindBy(xpath = "//option[string()='10/19/2020 (Pharmacy)']")
    private WebElement availableDate;

    @FindBy(xpath = "//input[contains(@id,'inventoryItemTypeAhead')]")
    private WebElement visitDateDropDown;

    @FindBy(xpath = "//input[contains(@id,'inventoryItemTypeAhead')]")
    private WebElement medicationField;

    @FindBy(xpath = "//span//div//div//div[contains(string(),'available')]")
    private WebElement availableMedication;

    @FindBy(xpath = "//textarea[contains(@id,'prescription')]")
    private WebElement prescriptionField;

    @FindBy(xpath = "//input[contains(@id,'display_prescriptionDate')]")
    private WebElement prescriptionDateField;

    @FindBy(xpath = "//input[contains(@id,'quantity')]")
    private WebElement quantityRequestedField;

    @FindBy(xpath = "//input[contains(@id,'refills')]")
    private WebElement refillsField;

    @FindBy(xpath = "//button[normalize-space()='Add']")
    private WebElement addButton;

    @FindBy(xpath = "//h1[contains(text(),'New Medication Request')]")
    private WebElement newMedicationHeader;

    public MedicationPage(WebDriver driver) {
        super(driver);
    }

    @Step("Type info in Patient field")
    public void typeInfoInPatientField() {
        new Actions(driver).pause(1000)
                .click(driver.findElement(By.cssSelector("input[id*=patientTypeAhead]"))).pause(millis)
                .sendKeys("T").pause(millis)
                .sendKeys("e").pause(millis)
                .sendKeys("s").pause(millis)
                .sendKeys("t").pause(millis)
                .sendKeys(" ").pause(millis)
                .sendKeys("P").pause(millis)
                .sendKeys("a").pause(millis)
                .sendKeys("t").pause(millis)
                .sendKeys("i").pause(millis)
                .sendKeys("e").pause(millis)
                .sendKeys("n").pause(millis)
                .sendKeys("t").pause(millis)
                .sendKeys(Keys.SPACE).build().perform();
    }

    @Step("Select patient")
    public void selectPatient() {
        wait.until(ExpectedConditions.visibilityOf(this.getTestPatientLine()));
        this.getTestPatientLine().click();
    }

    @Step("click to Visit field")
    public void clickToVisitField() {
        this.getVisitDropDown().click();
    }

    @Step("Select available date from Visit dropDown")
    public void selectAvailableDateFromVisitDropDown() {
        Select dropDown = new Select(this.getVisitDropDown());
        wait.until(driver -> (dropDown.getOptions().size() > 1));
        this.getVisitDropDown().click();
        dropDown.selectByIndex(2);
    }

    @Step("Type info in Medication field")
    public void typeInfoInMedicationField() {
        new Actions(driver).pause(1000)
                .click(driver.findElement(By.cssSelector("input[id*=inventoryItemTypeAhead"))).pause(millis)
                .sendKeys("P").pause(millis)
                .sendKeys("r").pause(millis)
                .sendKeys("a").pause(millis)
                .sendKeys("m").pause(millis)
                .sendKeys("o").pause(millis)
                .sendKeys("x").pause(millis)
                .sendKeys("i").pause(millis)
                .sendKeys("n").pause(millis)
                .sendKeys("e").pause(millis)
                .build().perform();
    }

    @Step("Select available date from Medication dropDown")
    public void selectAvailableDateFromMedicationDropDown() {
        this.getAvailableMedication().click();
    }

    @Step("Type info in Prescription field")
    public void typeInfoInPrescriptionField(String prescription) {
        this.getPrescriptionField().sendKeys(prescription);
    }

    @Step("Type info in Prescription Date one date before current date")
    public void typeInfoInPrescriptionDate() {
        String date = String.valueOf(LocalDate.now().minusDays(1));
        this.getPrescriptionDateField().clear();
        this.getPrescriptionDateField().sendKeys(date);
    }

    @Step("Type random number in range from 1 to 5 into Quantity Requested field")
    public void typeRandomNumberInQuantityRequestedField(int max, int min) {
        this.getRandomNumber(this.getQuantityRequestedField(), max, min);
    }

    @Step("Type random number in range from 5 to 10 into Refills field")
    public void typeRandomNumberInRefillsField(int max, int min) {
        this.getRandomNumber(this.getRefillsField(), max, min);
    }

    @Step("Click to Add button")
    public void clickToAddButton() {
        this.getAddButton().click();
    }

    @Step("Is User stayed on New Medication Request Page")
    public void isUserStayedOnNewMedicationRequestPage() {
        assertEquals(driver.getCurrentUrl(), MEDICATION_PAGE.getUrl());
    }

    public void getRandomNumber(WebElement element, int max, int min) {
        String random = String.valueOf((int) (Math.random() * ((max - min) + 1) + min));
        element.click();
        element.sendKeys(random);
    }
}
