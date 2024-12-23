package mk.ukim.finki.seleniumtesting;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {
    public final WebDriverWait wait;
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        // Updated for Selenium 4 - WebDriverWait constructor now uses Duration
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
}
