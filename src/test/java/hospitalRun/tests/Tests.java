package hospitalRun.tests;

import hospitalRun.POM.Application;
import hospitalRun.utils.RandomDataGenerator;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.LocalDate;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Tests {
    private final Application app = new Application();

    @BeforeSuite
    public void getHomePage() {
        app.logInPage().open();
    }

    @AfterTest(groups = "negative")
    public void getLoginPage() {
        app.logInPage().open();
    }

    @AfterMethod()
    public void logOut() {
        app.logOut();
    }

    @Test
    public void logInTest() {
        app.logInPage().logIn("hr.doctor@hospitalrun.io", "HRt3st12");
        app.patientListeningPage().waitForDisplayed();
    }

    @Test(groups = "negative")
    void logInTestNegative() {
        app.logInPage().logIn("User", "Password1234");
        assertEquals(app.logInPage().getErrorMessage(), "Username or password is incorrect.");
        app.logInPage().waitForDisplayed();
    }

    @Test
    void logOutTest() {
        app.logInPage().logIn("hr.doctor@hospitalrun.io", "HRt3st12");
        app.navigationBar().clickCogwheelButton();
        app.navigationBar().clickLogOutButton();
        app.logInPage().waitForDisplayed();
    }

    @Test
    void medicationRequestTest() {
        app.logInPage().logIn("hr.doctor@hospitalrun.io", "HRt3st12");
        app.navigationBar().clickMedicationTab();

        assertTrue(app.navigationBar().isRequestsButtonPresent());
        assertTrue(app.navigationBar().isCompletedButtonPresent());
        assertTrue(app.navigationBar().isNewMedicationButtonPresent());
        assertTrue(app.navigationBar().isReturnMedicationButtonPresent());
        app.navigationBar().clickNewMedicationRequest();

        app.medicationPage().typeIntoPatientField("Test Patient");
        app.medicationPage().selectPatient("Test Patient - P00201");
        app.medicationPage().clickVisitField();
        app.medicationPage().selectAvailableDateFromVisitDropDown();
        app.medicationPage().typeIntoMedicationField("Pramoxine");
        app.medicationPage().selectAvailableDateFromMedicationDropDown();
        app.medicationPage().typeIntoPrescriptionField("Testing prescription");
        app.medicationPage().typeIntoPrescriptionDate(String.valueOf(LocalDate.now().minusDays(1)));
        app.medicationPage().typeIntoQuantityRequestedField(Integer.toString(RandomDataGenerator.getRandomNumber(5, 1)));
        app.medicationPage().typeIntoRefillsField(Integer.toString(RandomDataGenerator.getRandomNumber(10, 5)));
        app.medicationPage().clickToAddButton();

        app.popUp().waitForDisplayed();
        assertTrue(app.popUp().isOkButtonPresent());
        assertTrue(app.popUp().isCrossButtonPresent());
        app.popUp().clickOkButton();
        Assert.assertFalse(app.popUp().isDisplayed());
        assertTrue(app.medicationPage().isDisplayed());
    }

    @AfterSuite
    public void quitDriver() {
        app.quitDriver();
    }
}
