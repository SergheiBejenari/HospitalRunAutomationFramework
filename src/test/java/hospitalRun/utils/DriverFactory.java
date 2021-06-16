package hospitalRun.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;

public class DriverFactory {

    public WebDriver getDriver() {
        String browserName = System.getProperty("browser", "chrome");
        switch (browserName.toLowerCase()) {
            case BrowserType.CHROME -> {
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            }
            case BrowserType.FIREFOX -> {
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            }
            default -> throw new RuntimeException("ATF is not configured for " + browserName);
        }
    }
}
