package pageObjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class GoogleHomePage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public GoogleHomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }
    public void acceptCookies() {
        wait.until(ExpectedConditions.visibilityOf(cookiesBanner));
        acceptCookiesButton.click();

        assertCookiesBannerDisappear();
    }

    private void assertCookiesBannerDisappear() {
        if (cookiesBanner.isDisplayed())
            Assert.fail();
    }

    public void checkAutoSuggestion(String phraseToSearch, String phraseToSuggest) throws Exception {
        String suggestionText = "";
        boolean suggestionFound = false;

        insertSearchPhrase(phraseToSearch);
        List<WebElement> suggestions = driver.findElements(By.xpath("//div[@class='mkHrUc']//span"));
        for (WebElement suggestion : suggestions) {
            suggestionText = suggestion.getText();
            if (suggestionText.equals(phraseToSuggest)) {
                suggestionFound = true;
                Assert.assertTrue(true);
                break;
            }
        }
        if (!suggestionFound) {
            throw new Exception("No suggestion found");
        }
    }

    private void insertSearchPhrase(String phrase) {
        wait.until(ExpectedConditions.visibilityOf(searchInput));
        searchInput.sendKeys(phrase);
    }

    public void searchPhrase(String phrase) {
        insertSearchPhrase(phrase);
        searchInput.sendKeys(Keys.RETURN);
    }

    @FindBy(xpath = "//div[@class='KxvlWc']")
    private WebElement cookiesBanner;
    @FindBy(xpath = "//button[@id='L2AGLb']")
    private WebElement acceptCookiesButton;

    @FindBy(xpath = "//input[@name='q']")
    private WebElement searchInput;
}
