import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Driver;
import java.util.List;

public class TvNetArticleCommentsTest {

    private final By ACCEPT_COOKIES_BTN = By.xpath(".//button[@mode = 'primary']");
    //private final By HOME_PAGE_ARTICLE = By.xpath(".//article[@class = 'list-article']"); //для поиска всех статей на стр надо выделять целый БЛОК статьи
    private final By HOME_PAGE_ARTICLE = By.tagName("article"); //для поиска всех статей на стр надо выделять целый БЛОК статьи
    private final By HOME_PAGE_TITLE = By.xpath(".//span[@class = 'list-article__headline']");
    private final By HOME_PAGE_COMMENTS = By.xpath(".//span[@class = 'list-article__comment section-font-color']");
    private final By ARTICLE_PAGE_TITLE = By.xpath(".//h1[@class = 'article-headline']");
    private final By ARTICLE_PAGE_COMMENTS = By.xpath(".//span[contains (@class, 'article-share__item--count')]");
    private final By COMMENT_PAGE_ARTICLE_TITLE = By.xpath(".//h1[@class = 'article-headline']");
    private final By COMMENT_PAGE_COMMENTS = By.xpath(".//span[@class = 'flex flex--align-items-center flex--justify-content-center article-comments-heading__count section-bg-color']");

    private WebDriver driver;
    @Test
    public void titlesCommentsCountCheck() {

        System.setProperty("webdriver.chrome.driver", "c://chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.tvnet.lv/");
        WebDriverWait wait = new WebDriverWait(driver, 20);
        driver.findElement(ACCEPT_COOKIES_BTN).click();
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,650)");

        //---------------------HOME PAGE---------------------------------

       List<WebElement> articles = driver.findElements(HOME_PAGE_ARTICLE);
        WebElement article = articles.get(2);
        String homePageTitle = article.findElement(HOME_PAGE_TITLE).getText().replaceAll("\\(\\d+\\)$", "").trim();
        int homePageCommentsCount = getCommentsCount(article, HOME_PAGE_COMMENTS);
        article.findElement(HOME_PAGE_TITLE).click();

        //----------------ARTICLE PAGE---------------------

       String articlePageTitle = driver.findElement(ARTICLE_PAGE_TITLE).getText().trim();
       int articlePageCommentsCount = Integer.parseInt(driver.findElement(ARTICLE_PAGE_COMMENTS).getText());

       Assertions.assertEquals(homePageTitle, articlePageTitle, "Wrong title");
       Assertions.assertEquals(homePageCommentsCount, articlePageCommentsCount, "Wrong comments count");

       //--------------COMMENTS PAGE--------------------

        driver.findElement(ARTICLE_PAGE_COMMENTS).click();

        String commentPageTitle = driver.findElement(COMMENT_PAGE_ARTICLE_TITLE).getText().trim();
        int commentPageCommentsCount = Integer.parseInt(driver.findElement(COMMENT_PAGE_COMMENTS).getText());

        Assertions.assertEquals(articlePageTitle, commentPageTitle, "Error with title");
        Assertions.assertEquals(homePageCommentsCount, commentPageCommentsCount, "Error with comments");
    }


    private int removeBrackets(WebElement we) {
        String commentsCountText = we.getText();
        commentsCountText = commentsCountText.substring(1, commentsCountText.length() - 1);
        return Integer.parseInt(commentsCountText);
    }

    private int getCommentsCount (By locator) {
        int commentsCount = 0;
        if(!driver.findElements(locator).isEmpty()) {
            commentsCount = removeBrackets(driver.findElement(locator));
        }
        return commentsCount;
    }

    private int getCommentsCount (WebElement we, By locator) {
        int commentsCount = 0;
        if(!we.findElements(locator).isEmpty()) {
            commentsCount = removeBrackets(we.findElement(locator));
        }
        return commentsCount;
    }
}
