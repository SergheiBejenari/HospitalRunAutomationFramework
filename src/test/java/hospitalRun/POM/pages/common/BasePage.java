package hospitalRun.POM.pages.common;

import hospitalRun.POM.Application;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected String baseUrl;
    private short defaultTimeout;

    protected BasePage(Application app) {
        this.baseUrl = app.url;
        this.driver = app.getDriver();
        this.wait = app.getWait();
        this.defaultTimeout = app.getDefaultTimeout();
        PageFactory.initElements(driver, this);
    }

    protected boolean isElementPresent(By selector) {
        try {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            driver.findElement(selector);
            driver.manage().timeouts().implicitlyWait(defaultTimeout, TimeUnit.SECONDS);
            return true;
        } catch (NoSuchElementException e) {
            driver.manage().timeouts().implicitlyWait(defaultTimeout, TimeUnit.SECONDS);
            return false;
        }
    }

    protected void type(By locator, String text) {
        driver.findElement(locator).click();
        if (text != null) {
            String existingText = driver.findElement(locator).getAttribute("value");
            if (!text.equals(existingText)) {
                wait.until(elementToBeClickable(locator));
                driver.findElement(locator).clear();
                driver.findElement(locator).sendKeys(text);
            }
        }
    }

    protected void click(By locator) {
        driver.findElement(locator).click();
    }
}
