package hospitalRun.testNG;

import hospitalRun.app.Application;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Tests {
    private final Application app = new Application();

    @BeforeMethod
    public void getHomePage() {
        app.logInPage().open();
    }

    @AfterMethod()
    public void logOut() {
        app.navigationBar().logOutIfUserLoggedIn();
        app.logInPage().isHeaderDisplayed();
    }

    @Test
    public void logInTest() {
        app.logInPage().logIn("hr.doctor@hospitalrun.io", "HRt3st12");
        app.patientListeningPage().isPatientListeningPageDisplayed();
    }

    @Test
    void logInTestNegative() {
        app.logInPage().logInInvalidUser("User", "Password1234");
        app.logInPage().isUserStayedOnLoginPage();
        app.logInPage().isErrorMessageDisplayed();
    }

    @Test
    void logOutTest() {
        app.logInPage().logIn("hr.doctor@hospitalrun.io", "HRt3st12");
        app.navigationBar().clickToCogwheelButton();
        app.navigationBar().clickToLogOutButton();
        app.logInPage().isUserLoggedOut();
        app.navigationBar().logOutIfUserLoggedIn();
    }

    @Test
    void medicationRequestTest() {
        app.logInPage().logIn("hr.doctor@hospitalrun.io", "HRt3st12");
        app.navigationBar().clickMedicationTab();

        app.navigationBar().isMedicationSectionContainsRequestsItem();
        app.navigationBar().isMedicationSectionContainsCompletedItem();
        app.navigationBar().isMedicationSectionContainsNewMedicationItem();
        app.navigationBar().isMedicationSectionContainsReturnMedicationItem();
        app.navigationBar().clickNewMedicationRequest();

        app.medicationPage().typeInfoInPatientField();
        app.medicationPage().selectPatient();
        app.medicationPage().clickToVisitField();
        app.medicationPage().selectAvailableDateFromVisitDropDown();
        app.medicationPage().typeInfoInMedicationField();
        app.medicationPage().selectAvailableDateFromMedicationDropDown();
        app.medicationPage().typeInfoInPrescriptionField("Testing prescription");
        app.medicationPage().typeInfoInPrescriptionDate();
        app.medicationPage().typeRandomNumberInQuantityRequestedField(5, 1);
        app.medicationPage().typeRandomNumberInRefillsField(10, 5);
        app.medicationPage().clickToAddButton();

        app.popUp().isPopUpDisplayed();
        app.popUp().isPopUpContainsOkButton();
        app.popUp().isPopUpContainsCrossButton();
        app.popUp().clickOkButton();
        app.popUp().popUpIsNotDisplayed();
        app.medicationPage().isUserStayedOnNewMedicationRequestPage();
    }

    @AfterSuite
    public void quitDriver() {
        app.quitDriver();
    }
}
