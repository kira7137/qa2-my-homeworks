import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class DelfiArticleCommentsTest {
    private final By ACCEPT_COOKIE_BTN = By.xpath(".//button[@mode='primary']");
    private final By HOME_PAGE_TITLE = By.xpath(".//h1[contains(@class, 'headline__title')]");

    @Test
    public void titleAndCommentsCountCheck() {
        System.setProperty("webdriver.chrome.driver", "c://chromedriver.exe");
        WebDriver driver = new ChromeDriver(); //driver= browser window
        driver.manage().window().maximize();
        driver.get("http://delfi.lv");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(ACCEPT_COOKIE_BTN));
        driver.findElement(ACCEPT_COOKIE_BTN).click();

        List<WebElement> titles = driver.findElements(HOME_PAGE_TITLE);
        for (int i = 0; i < titles.size(); i++) { //size - List function ; i++ --> i= i+1 - iteracija
            if (!titles.get(i).getText().isEmpty()) { // !true = false; !false = true
                System.out.println(i + ": " + titles.get(i).getText());//i - порядковый номер статей из списка
            }
        }

        //-----------FOR EACH------------ -для каждого элемента списка
        for (WebElement we : titles) {
//            if (!we.getText().isEmpty()) {
//                System.out.println(we.getText());
//            } else {
//                System.out.println("--------------");
//            }
            System.out.println(we.getText().isEmpty() ? "-----------------" : we.getText()); // условие ? true : false (else)
        }
    }
}
