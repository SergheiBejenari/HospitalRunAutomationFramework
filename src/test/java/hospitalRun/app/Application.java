package hospitalRun.app;

import hospitalRun.driver.DriverFactory;
import hospitalRun.pages.*;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class Application {

    private WebDriver driver;
    private LogInPage logInPage;
    private NavigationBar navigationBar;
    private MedicationPage medicationPage;
    private PatientListeningPage patientListeningPage;
    private PopUp popUp;

    public WebDriver getDriver() {
        if (driver == null) {
            driver = new DriverFactory().getDriver();
            driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }
        return driver;
    }

    public void quitDriver() {
        driver.quit();
    }

    public PatientListeningPage patientListeningPage() {
        if (patientListeningPage == null) {
            patientListeningPage = new PatientListeningPage(this.driver);
        }
        return patientListeningPage;
    }

    public MedicationPage medicationPage() {
        if (medicationPage == null) {
            medicationPage = new MedicationPage(this.driver);
        }
        return medicationPage;
    }

    public LogInPage logInPage() {
        if (logInPage == null) {
            logInPage = new LogInPage(this.getDriver());
        }
        return logInPage;
    }

    public NavigationBar navigationBar() {
        if (navigationBar == null) {
            navigationBar = new NavigationBar(this.getDriver());
        }
        return navigationBar;
    }

    public PopUp popUp() {
        if (popUp == null) {
            popUp = new PopUp(this.getDriver());
        }
        return popUp;
    }
}
