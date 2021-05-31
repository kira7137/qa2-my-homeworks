import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
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
    private final By HOME_PAGE_ARTICLE = By.tagName("article"); //все статьи на главной странице
    private final By HOME_PAGE_COMMENTS = By.xpath(".//a[contains(@class, 'comment-count')]"); //все комментарии на гл.стр
    private final By ARTICLE_PAGE_TITLE = By.xpath(".//h1[contains(@class, 'text-size-md-30')]");
    private final By ARTICLE_PAGE_COMMENTS = By.xpath(".//a[contains(@class, 'text-size-19 text-size-md-28')]");
    private final By PAGE_COMMENTS = By.xpath(".//a[contains(@class, 'text-red-ribbon')]");
    private final By COMMENTS = By.xpath(".//span[contains(@class, 'type-cnt')]");
    private final By COMMENT_PAGE_ARTICLE_TITLE = By.xpath(".//h1[contains(@class, 'article-title')]");

    private WebDriver driver; //переменная драйвер на уровне класса

    @Test
    public void titleAndCommentsCountCheck() {
        System.setProperty("webdriver.chrome.driver", "c://chromedriver.exe");
        driver = new ChromeDriver(); //driver= browser window
        driver.manage().window().maximize();
        driver.get("http://delfi.lv");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(ACCEPT_COOKIE_BTN));
        driver.findElement(ACCEPT_COOKIE_BTN).click();

        //--------------
//        List<WebElement> titles = driver.findElements(HOME_PAGE_TITLE);
//        //---------------FOR---------------------
//        for (int i = 0; i < titles.size(); i++) { //size - List function ; i++ --> i= i+1 - iteracija
//            if (!titles.get(i).getText().isEmpty()) { // !true = false; !false = true
//                System.out.println(i + ": " + titles.get(i).getText());//i - порядковый номер статей из списка
//            }
//        }
//        //-----------FOR EACH------------ -для каждого элемента списка
//        for (WebElement we : titles) {
////            if (!we.getText().isEmpty()) {
////                System.out.println(we.getText());
////            } else {
////                System.out.println("--------------");
////            }
//            System.out.println(we.getText().isEmpty() ? "-----------------" : we.getText()); // условие ? true : false (else)
//        }


        //задание: на главной странице и на странице комментариев проверить, что заголовки совпадают и что кол-во коммент.совпадает

        //---------------------HOME PAGE---------------------------------

        List<WebElement> articles = driver.findElements(HOME_PAGE_ARTICLE); // 1. собрать все статьи целиком в список
        WebElement article = articles.get(4); // 2. берем отдельно взятую статью с порядковым номером (4)
        String homePageTitle = article.findElement(HOME_PAGE_TITLE).getText(); // 3.сохранить заголовок статьи и кол-во коммент текущ.статьи в переменную
        int homePageCommentsCount = 0; // 4. создаем переменную
        //узнать- есть ли в текущ.статье комментарии или нет
        if (!article.findElements(HOME_PAGE_COMMENTS).isEmpty()) { // ! -- если список НЕ пустой
            homePageCommentsCount = getCommentsCount(article.findElement(HOME_PAGE_COMMENTS).getText()); //  getCommentsCount - вызов метода
            // article.findElement(HOME_PAGE_COMMENTS).getText() - появится кол-во комментариев, результат -(36) тип String
        }
        //переход на страницу статьи
        article.findElement(HOME_PAGE_TITLE).click();


        //----------------ARTICLE PAGE---------------------
        String articlePageTitle = driver.findElement(ARTICLE_PAGE_TITLE).getText(); //получаем заголовок на странице статьи
        int articlePageCommentsCount = 0;
        //узнать- есть ли в текущ.статье комментарии или нет
        if (!driver.findElements(ARTICLE_PAGE_COMMENTS).isEmpty()) { //driver.findElements - поиск по всему окну
            articlePageCommentsCount = getCommentsCount(driver.findElement(ARTICLE_PAGE_COMMENTS).getText());
            // вызываем метод getCommentsCount, внутри находим элементы и получаем текст
        }

        //проверки тестов/ assertions, assumption
        // assertions - строгие проверки, если они не выполняются, то тест останавливается
        // assumption - предположение. тест выдаст ошибку, но продолжит выполнятся. Используется редко.
        Assertions.assertEquals(homePageTitle, articlePageTitle, "Wrong title!");
        //homepagetitle - expected result
        //articlePage Title - actual result
        //Wrong title - комментарий ошибки
        Assertions.assertEquals(homePageCommentsCount, articlePageCommentsCount, "Wrong comments count!");

        //--------------COMMENTS PAGE--------------------
        driver.findElement(PAGE_COMMENTS).click();

        String commentPageTitle = driver.findElement(COMMENT_PAGE_ARTICLE_TITLE).getText();
        String Comments = driver.findElement(COMMENTS).getText();

//        int commentPageCommentsCount = 0;
//        if (!driver.findElements(COMMENTS).isEmpty()) {
//            driver.findElement(COMMENTS).getText();
//        }

        //Assertions.assertEquals(commentPageTitle, articlePageTitle, "Error with title");
        //Assertions.assertEquals(homePageCommentsCount, commentPageCommentsCount, "Error with comments");



//        int CommentPageCommentsCount = 0;
//        if (!driver.findElements(COMMENTS).isEmpty()) {
//           CommentPageCommentsCount = getCommentsCount(driver.findElement(COMMENTS).getText());
//            System.out.println(CommentPageCommentsCount);
//        }


    }


        // отдельный метод getCommentsCount - избавление от скобок
        private int getCommentsCount (String commentsCountText){ //int- что возвращает функция
            commentsCountText = commentsCountText.substring(1, commentsCountText.length() - 1); // (36) --> 36 (String)
            //отбрасывание скобок. результат- 36, тип String
            // метод commentsCount.length() -1 -- т.к. мы не знаем кол-во цифр в комментах, мы узнаем длину строки и отнимаем 1 символ. и получаем предпоследний.
            return Integer.parseInt(commentsCountText);
            // переделываем строку 36 типа String в тип данных int 36(String) --> 36(int),
            // return- вернуть как результат работы метода
        }

        // закрытие браузерного окнa выносится вне метода
//        @AfterEach //аннотация- после прохождения каждого теста в этом классе
//        public void closeBrowser() {
//            driver.close();
//        }
        // 36(String) --> 36(int), return- вернуть как результат работы метода
    }



//домашнее задание
//надо перейти на стр комментариев, собрать там кол-во комментариев, заголовок и проверить, что они правильные (метод assertEquals)

// получить анонимные и зарегистр коммент, потом избавиться от скобок, их сложит и сделать проверку

// сделать то же самое на твнете

