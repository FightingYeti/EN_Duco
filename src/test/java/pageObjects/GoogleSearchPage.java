package pageObjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class GoogleSearchPage {
    private final WebDriver driver;

    public GoogleSearchPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void assertSearchResult(String resultToFind) {
        List<WebElement> result = driver.findElements(By.xpath("//h3[text()='"+ resultToFind +"']"));
        if (result.size()==0)
            Assert.fail();
    }
}
