
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.xml.bind.Element;
import javax.xml.soap.Text;
import java.awt.*;
import java.util.Iterator;
import java.util.List;

public class MyFirstHomework {


    private final By ACCEPT_COOKIES_BTN = By.xpath(".//button[@mode='primary']");
    private final By OPEN_FIRST_ARTICLE = By.xpath(".//span[@class='list-article__headline']");
    //private final By OPEN_COMMENT_PAGE = By.cssSelector("img[src='/v5/img/icons/comment-v2.svg']"); //попробовать xpath??
    private final By OPEN_COMMENT_PAGE = By.xpath(".//img[@src='/v5/img/icons/comment-v2.svg']");
    private final By FIRST_ARTICLE_HEADER = By.xpath("//span[@itemprop='headline name']");


    //LOCATORS 3d homework:
    private final By ALL_ARTICLE_LOC = By.xpath(".//span[contains(@itemprop, 'headline name')]"); // для всех заголовков статей
    private final By ARTICLES_WITH_COMMENTS = By.xpath(".//span[@class='list-article__comment section-font-color']"); //для всех элементов с комментами
    private final By TVNET_LOGO_ALT = By.xpath(".//a[@class='flex header-logo flex--align-items-center']"); //для лого
    private final By LANG_BTN_RUS = By.xpath(".//*[contains( text(), 'RUS')]"); //для ссылки переключения стр на рус язык

    @Test
    public void firstHomework() {

        System.setProperty("webdriver.chrome.driver", "c://chromedriver.exe");
        WebDriver browserWindow = new ChromeDriver();
        browserWindow.manage().window().maximize();
        browserWindow.get("http://tvnet.lv");
        browserWindow.findElement(ACCEPT_COOKIES_BTN).click();
        browserWindow.findElement(OPEN_FIRST_ARTICLE).click();
        browserWindow.findElement(OPEN_COMMENT_PAGE).click();

        browserWindow.close();

    }

    @Test
    public void secondHomework() {
        //распечатать в консоль заголовок 1й статьи

        System.setProperty("webdriver.chrome.driver", "c://chromedriver.exe");
        WebDriver browserWindow = new ChromeDriver();
        browserWindow.manage().window().maximize();

        browserWindow.get("http://tvnet.lv");
        browserWindow.findElement(ACCEPT_COOKIES_BTN).click();
        String text = browserWindow.findElement(FIRST_ARTICLE_HEADER).getText();
        System.out.println("Article heading is: " + text);

        browserWindow.close(); }


    @Test
    public void fourthHomework() {
        //распечатать все заголовки статей с главной страницы БЕЗ комментариев

        System.setProperty("webdriver.chrome.driver", "c://chromedriver.exe");
        WebDriver browserWindow = new ChromeDriver();
        browserWindow.manage().window().maximize();
        browserWindow.get("http://tvnet.lv");
        browserWindow.findElement(ACCEPT_COOKIES_BTN).click();

        List<WebElement> articleName = browserWindow.findElements(ALL_ARTICLE_LOC);
        String toBeRemoved = "(";
        for (int i = 0; i < articleName.size(); i++) {
            System.out.println("Article heading: " + articleName.get(i).getText().replaceAll("\\(\\d+\\)", " "));
        }
        browserWindow.close();
    }



        //if - else используем , Remove comment
        // method Replace

    @Test
    public void fifthHomework() {
        //распечатать все заголовки статей с главной страницы + кол-во комментариев

        System.setProperty("webdriver.chrome.driver", "c://chromedriver.exe");
        WebDriver browserWindow = new ChromeDriver();
        browserWindow.manage().window().maximize();
        browserWindow.get("http://tvnet.lv");
        browserWindow.findElement(ACCEPT_COOKIES_BTN).click();

        List<WebElement> articleName = browserWindow.findElements(ALL_ARTICLE_LOC);
        for (int i = 0; i < articleName.size(); i++) {
            System.out.println("Article heading: " + articleName.get(i).getText());
        }
        browserWindow.close();

    }

}
