package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.GoogleHomePage;
import pageObjects.GoogleSearchPage;

import java.time.Duration;

public class GoogleSearchTests {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "./src/test/java/driver/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.google.pl/");
        driver.manage().window().maximize();
    }

    @Test
    public void acceptCookies() {
        GoogleHomePage googleHomePage = new GoogleHomePage(driver, wait);
        googleHomePage.acceptCookies();
    }

    @Test
    public void autoSuggestionCheck() throws Exception {
        GoogleHomePage googleHomePage = new GoogleHomePage(driver, wait);
        googleHomePage.acceptCookies();
        googleHomePage.checkAutoSuggestion("Face", "Facebook");
    }

    @Test
    public void searchPhrase() {
        GoogleHomePage googleHomePage = new GoogleHomePage(driver, wait);
        googleHomePage.acceptCookies();
        googleHomePage.searchPhrase("Duco");

        GoogleSearchPage googleSearchPage = new GoogleSearchPage(driver, wait);
        googleSearchPage.assertSearchResult("Duco: Homepage");
        googleSearchPage.assertSearchResult("Duco | LinkedIn");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
