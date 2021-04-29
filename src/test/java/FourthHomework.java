import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class FourthHomework {
    private final By ACCEPT_COOKIES_BTN = By.xpath(".//button[@mode='primary']");
    private final By ALL_ARTICLE_LOC = By.xpath(".//span[contains(@itemprop, 'headline name')]"); // для всех заголовков статей
    @Test
    public void fourthHomework() {

        //распечатать все заголовки статей с главной страницы, убрав комментарии

        System.setProperty("webdriver.chrome.driver", "c://chromedriver.exe");
        WebDriver browserWindow = new ChromeDriver();
        browserWindow.manage().window().maximize();
        browserWindow.get("http://tvnet.lv");
        browserWindow.findElement(ACCEPT_COOKIES_BTN).click();

        List<WebElement> articleName = browserWindow.findElements(ALL_ARTICLE_LOC);
        String toBeRemoved = "(" + ")";
        for (int i = 0; i < articleName.size(); i++) {
            System.out.println("Article heading: " + articleName.get(i).getText());
            if (articleName.equals(toBeRemoved)) {
                System.out.println("Article heading: " + articleName.remove(toBeRemoved));
            }
        }
        browserWindow.close();
    }
}
