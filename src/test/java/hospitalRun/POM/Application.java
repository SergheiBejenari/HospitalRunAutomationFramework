package hospitalRun.POM;

import hospitalRun.POM.pages.*;
import hospitalRun.utils.DriverFactory;
import hospitalRun.utils.PropertyReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Application {

    private WebDriver driver;
    private WebDriverWait wait;
    private LogInPage logInPage;
    private NavigationBar navigationBar;
    private MedicationPage medicationPage;
    private PatientListeningPage patientListeningPage;
    private PopUp popUp;
    public final String url;
    private short defaultTimeout;


    public Application() {
        url = PropertyReader.applicationProperties().getProperty("base.url");
        defaultTimeout = 30;
    }

    public void logOut() {
        if (this.navigationBar().isPresent()) {
            this.navigationBar().logOut();
            this.logInPage().waitForDisplayed();
        }
    }

    public WebDriver getDriver() {
        if (driver == null) {
            driver = new DriverFactory().getDriver();
            driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }
        return driver;
    }

    public short getDefaultTimeout() {
        return defaultTimeout;
    }

    public WebDriverWait getWait() {
        if (wait == null) {
            wait = new WebDriverWait(driver, defaultTimeout);
        }
        return wait;
    }

    public void quitDriver() {
        driver.quit();
    }

    public PatientListeningPage patientListeningPage() {
        if (patientListeningPage == null) {
            patientListeningPage = new PatientListeningPage(this);
        }
        return patientListeningPage;
    }

    public MedicationPage medicationPage() {
        if (medicationPage == null) {
            medicationPage = new MedicationPage(this);
        }
        return medicationPage;
    }

    public LogInPage logInPage() {
        if (logInPage == null) {
            logInPage = new LogInPage(this);
        }
        return logInPage;
    }

    public NavigationBar navigationBar() {
        if (navigationBar == null) {
            navigationBar = new NavigationBar(this);
        }
        return navigationBar;
    }

    public PopUp popUp() {
        if (popUp == null) {
            popUp = new PopUp(this);
        }
        return popUp;
    }
}
