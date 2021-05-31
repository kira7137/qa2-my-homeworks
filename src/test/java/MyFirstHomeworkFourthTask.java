import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class MyFirstHomeworkFourthTask {
    private final By ACCEPT_COOKIES_BTN = By.xpath(".//button[@mode='primary']");
    private final By ALL_ARTICLE_LOC = By.xpath(".//span[contains(@itemprop, 'headline name')]"); // для всех заголовков статей
    private WebDriver driver;

    @Test
    public void fourthHomework() {

        //распечатать все заголовки статей с главной страницы, убрав комментарии

        System.setProperty("webdriver.chrome.driver", "c://chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://tvnet.lv");
        driver.findElement(ACCEPT_COOKIES_BTN).click();

        List<WebElement> articleName = driver.findElements(ALL_ARTICLE_LOC);

        for (int i = 0; i < articleName.size(); i++) {
            System.out.println("Article heading: " + articleName.get(i).getText().replaceAll("\\(\\d+\\)$", "").trim());
        }

    }

    @AfterEach
    public void closeBrowser() {
        driver.close();
    }

}
