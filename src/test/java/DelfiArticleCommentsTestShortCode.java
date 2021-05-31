import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class DelfiArticleCommentsTestShortCode {

    private final By ACCEPT_COOKIE_BTN = By.xpath(".//button[@mode = 'primary']");
    private final By HOME_PAGE_TITLE = By.xpath(".//h1[contains(@class, 'headline__title')]");
    private final By HOME_PAGE_ARTICLE = By.tagName("article"); //все статьи на главной странице
    private final By HOME_PAGE_COMMENTS = By.xpath(".//a[contains(@class, 'comment-count')]"); //все комментарии на гл.стр
    private final By ARTICLE_PAGE_TITLE = By.xpath(".//h1[contains(@class, 'text-size-md-30')]");
    private final By ARTICLE_PAGE_COMMENTS = By.xpath(".//a[contains(@class, 'text-size-md-28')]");
    private final By PAGE_COMMENTS = By.xpath(".//a[contains(@class, 'text-red-ribbon')]");
    private final By COMMENTS = By.xpath(".//span[@class = 'type-cnt']");
    private final By COMMENT_PAGE_ARTICLE_TITLE = By.xpath(".//h1[@class = 'article-title']");

    private final Logger LOGGER = LogManager.getLogger(DelfiArticleCommentsTestShortCode.class);

    private WebDriver driver; //переменная драйвер на уровне класса

    @Test
    public void titleAndCommentsCountCheck() {
        LOGGER.info("This test is checking titles and comments count on home/article/comments pages");

        LOGGER.info("Setting driver location");
        System.setProperty("webdriver.chrome.driver", "c://chromedriver.exe");

        LOGGER.info("Opening browser window");
        driver = new ChromeDriver(); //driver= browser window
        driver.manage().window().maximize();

        LOGGER.info("Open website");
        driver.get("http://delfi.lv");

        //---------------------HOME PAGE---------------------------------

        WebDriverWait wait = new WebDriverWait(driver, 10);

        LOGGER.info("Waiting for accept cookie btn");
        wait.until(ExpectedConditions.elementToBeClickable(ACCEPT_COOKIE_BTN));

        LOGGER.info("Accepting cookies");
        driver.findElement(ACCEPT_COOKIE_BTN).click();

        //selenium java one page scroll - от рекламы
        LOGGER.info("Scroll down");
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,250)");

        //задание: на главной странице и на странице комментариев проверить, что заголовки совпадают и что кол-во коммент.совпадает

        LOGGER.info("Get list of articles");
        List<WebElement> articles = driver.findElements(HOME_PAGE_ARTICLE); // 1. собрать все статьи целиком в список
        LOGGER.info("Get article");
        WebElement article = articles.get(3); // 2. берем отдельно взятую статью с порядковым номером (4)

       LOGGER.info("Getting article title and comments count");
        String homePageTitle = article.findElement(HOME_PAGE_TITLE).getText().trim(); // 3.сохранить заголовок статьи и кол-во коммент текущ.статьи в переменную
        //.trim() - избавиться от пустой строки в конце заголовка
        int homePageCommentsCount = getCommentsCount(article, HOME_PAGE_COMMENTS);

//        int homePageCommentsCount = 0; // 4. создаем переменную
//        //узнать- есть ли в текущ.статье комментарии или нет
//        if (!article.findElements(HOME_PAGE_COMMENTS).isEmpty()) { // ! -- если список НЕ пустой
//            homePageCommentsCount = getCommentsCount(article.findElement(HOME_PAGE_COMMENTS).getText()); //  getCommentsCount - вызов метода
//            // (article.findElement(HOME_PAGE_COMMENTS).getText() - появится кол-во комментариев, результат -(36) тип String
//        }
        LOGGER.info("Title is: " + homePageTitle + " and comments count is: " + homePageCommentsCount);
        //переход на страницу статьи
        LOGGER.info("Opening article");
        article.findElement(HOME_PAGE_TITLE).click();

        //----------------ARTICLE PAGE---------------------
        LOGGER.info("Get article page title");
        String articlePageTitle = driver.findElement(ARTICLE_PAGE_TITLE).getText().trim(); //получаем заголовок на странице статьи
        int articlePageCommentsCount = getCommentsCount(ARTICLE_PAGE_COMMENTS);

        Assertions.assertEquals(homePageTitle, articlePageTitle, "Wrong title!");
        Assertions.assertEquals(homePageCommentsCount, articlePageCommentsCount, "Wrong comments count!");

        //--------------COMMENTS PAGE--------------------
        List<WebElement> commentCounts = driver.findElements(COMMENTS);
        int commentPageCommentsCount = removeBrackets(commentCounts.get(0)) + removeBrackets(commentCounts.get(1));

        driver.findElement(PAGE_COMMENTS).click();

        String commentPageTitle = driver.findElement(COMMENT_PAGE_ARTICLE_TITLE).getText().trim();

        Assertions.assertEquals(articlePageTitle, commentPageTitle, "Error with title");
        Assertions.assertEquals(homePageCommentsCount, commentPageCommentsCount, "Error with comments");
    }

    // отдельный метод getCommentsCount - избавление от скобок
    private int getCommentsCount (By locator) { //int- что возвращает функция
        int commentsCount = 0;
        if(!driver.findElements(locator).isEmpty()) {
            commentsCount = removeBrackets(driver.findElement(locator));

//            String commentsCountText = driver.findElement(locator).getText();
//            commentsCountText = commentsCountText.substring(1, commentsCountText.length() - 1); // (36) --> 36 (String)
//            //отбрасывание скобок. результат- 36, тип String
//            // метод commentsCount.length() -1 -- т.к. мы не знаем кол-во цифр в комментах, мы узнаем длину строки и отнимаем 1 символ. и получаем предпоследний.
//            commentsCount = Integer.parseInt(commentsCountText);
//            // переделываем строку 36 типа String в тип данных int 36(String) --> 36(int),
        }
        return commentsCount;            // return- вернуть как результат работы метода
    }

    private int getCommentsCount (WebElement we, By locator) {
        int commentsCount = 0;
        if(!we.findElements(locator).isEmpty()) {
            commentsCount = removeBrackets(we.findElement(locator));

//            String commentsCountText = we.findElement(locator).getText();
//            commentsCountText = commentsCountText.substring(1, commentsCountText.length() - 1); // (36) --> 36 (String)
//            //отбрасывание скобок. результат- 36, тип String
//            // метод commentsCount.length() -1 -- т.к. мы не знаем кол-во цифр в комментах, мы узнаем длину строки и отнимаем 1 символ. и получаем предпоследний.
//            commentsCount = Integer.parseInt(commentsCountText);
//            // переделываем строку 36 типа String в тип данных int 36(String) --> 36(int),
        }
        return commentsCount;            // return- вернуть как результат работы метода
    }

    private int removeBrackets(WebElement we) {
        String commentsCountText = we.getText();
        commentsCountText = commentsCountText.substring(1, commentsCountText.length() - 1); // (36) --> 36 (String)
        //отбрасывание скобок. результат- 36, тип String
        // метод commentsCount.length() -1 -- т.к. мы не знаем кол-во цифр в комментах, мы узнаем длину строки и отнимаем 1 символ. и получаем предпоследний.
        return Integer.parseInt(commentsCountText);
        // переделываем строку 36 типа String в тип данных int 36(String) --> 36(int),
    }

    @AfterEach //аннотация- после прохождения каждого теста в этом классе
    public void closeBrowser() {
        driver.close();
    }

    // 36(String) --> 36(int), return- вернуть как результат работы метода
}



//домашнее задание
//надо перейти на стр комментариев, собрать там кол-во комментариев, заголовок и проверить, что они правильные (метод assertEquals)

// получить анонимные и зарегистр коммент, потом избавиться от скобок, их сложит и сделать проверку

// сделать то же самое на твнете



