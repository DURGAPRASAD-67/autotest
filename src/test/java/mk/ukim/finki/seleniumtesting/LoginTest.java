package mk.ukim.finki.seleniumtesting;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest {
    private WebDriver driver;

    @BeforeTest
    public void setup() {
        driver = getDriver();
    }

    @Test
    public void shouldOpen() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        assertTrue(loginPage.isLoaded());
    }

    @Test // T1
    public void canNotLoginWithInvalidPassword() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        assertTrue(loginPage.isLoaded());
        loginPage.login("gabdimitrievski111@gmail.com", "wrong_password_test");
        String errorMessage = loginPage.getErrorMessage();
        assertEquals(errorMessage, "The password you’ve entered is incorrect. Forgot Password?");
    }

    @Test // T2
    public void canNotLoginWithoutUserName() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        assertTrue(loginPage.isLoaded());
        loginPage.login("", "");
        String errorMessage = loginPage.getErrorMessage();
        assertEquals(errorMessage, "The email or mobile number you entered isn’t connected to an account. Find your account and log in.");
    }

    @Test // T3
    public void shouldLogin() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        assertTrue(loginPage.isLoaded());
        loginPage.login("gabdimitrievski111@gmail.com", "correct_password");
        assertTrue(new HomePage(driver).isLoaded());
    }

    private WebDriver getDriver() {
        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "./src/chromedriver.exe");

        // Setup Chrome options for headless mode
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");  // Running in headless mode (no UI)
        options.addArguments("--disable-gpu");  // Disable GPU hardware acceleration
        options.addArguments("--window-size=1920x1080");  // Set window size to avoid issues

        return new ChromeDriver(options);  // Return the ChromeDriver with the options
    }

    @AfterTest
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
