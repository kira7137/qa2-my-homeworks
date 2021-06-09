package pageobject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageobject.pages.ArticlePage;
import pageobject.pages.BaseFunc;
import pageobject.pages.CommentPage;
import pageobject.pages.HomePage;

import java.util.List;

public class DelfiArticleCommentTestPageObject {

    private final Logger LOGGER = LogManager.getLogger(this.getClass());
    private final int ARTICLE_ID = 6; //porjadkovij nomer statji

    private BaseFunc baseFunc;

    @Test
    public void titleAndCommentsCountCheck() {
        LOGGER.info("This test is checking titles and comments count on home/article/comments pages");

        baseFunc = new BaseFunc();  //sozdanie kopii objecta BaseFunc, vizov Constructor' a
        baseFunc.openPage("http://delfi.lv");

        //---------------------HOME PAGE---------------------------------
        HomePage homePage = new HomePage(baseFunc); //baseFunc - peredaem tekushee brauzernoe okno, kot.uze otkrito
        homePage.acceptCookies();

//        //selenium java one page scroll - от рекламы
//        LOGGER.info("Scroll down");
//        JavascriptExecutor jse = (JavascriptExecutor)driver;
//        jse.executeScript("window.scrollBy(0,250)");

       //задание: на главной странице и на странице комментариев проверить, что заголовки совпадают и что кол-во коммент.совпадает

        String homePageTitle = homePage.getTitle(ARTICLE_ID).trim();
        int homePageCommentsCount = homePage.getCommentsCount(ARTICLE_ID);

//        LOGGER.info("Getting article title and comments count");
//        String homePageTitle = article.findElement(HOME_PAGE_TITLE).getText().trim(); // 3.сохранить заголовок статьи и кол-во коммент текущ.статьи в переменную
//        //.trim() - избавиться от пустой строки в конце заголовка
//        int homePageCommentsCount = getCommentsCount(article, HOME_PAGE_COMMENTS);

//        int homePageCommentsCount = 0; // 4. создаем переменную
//        //узнать- есть ли в текущ.статье комментарии или нет
//        if (!article.findElements(HOME_PAGE_COMMENTS).isEmpty()) { // ! -- если список НЕ пустой
//            homePageCommentsCount = getCommentsCount(article.findElement(HOME_PAGE_COMMENTS).getText()); //  getCommentsCount - вызов метода
//            // (article.findElement(HOME_PAGE_COMMENTS).getText() - появится кол-во комментариев, результат -(36) тип String
//        }
//        LOGGER.info("Title is: " + homePageTitle + " and comments count is: " + homePageCommentsCount);
//        //переход на страницу статьи

        ArticlePage articlePage = homePage.openArticle(ARTICLE_ID);

//        LOGGER.info("Opening article");
//        article.findElement(HOME_PAGE_TITLE).click();
//
        //----------------ARTICLE PAGE---------------------

        String articlePageTitle = articlePage.getTitle();
        int articlePageCommentsCount = articlePage.getCommentsCount();

//        String articlePageTitle = driver.findElement(ARTICLE_PAGE_TITLE).getText().trim(); //получаем заголовок на странице статьи
//        int articlePageCommentsCount = getCommentsCount(ARTICLE_PAGE_COMMENTS); //poluchenie kol-va commentov

        Assertions.assertEquals(homePageTitle, articlePageTitle, "Wrong title!");
        Assertions.assertEquals(homePageCommentsCount, articlePageCommentsCount, "Wrong comments count!");

//        articlePage.openCommentsPage();

        CommentPage commentPage = articlePage.openCommentsPage();

//        //--------------COMMENTS PAGE--------------------

        int commentCounts = commentPage.getCommentsCount();
        int commentPageCommentsCount = commentPage.getCommentsCount();

        String commentPageTitle = commentPage.getTitle();

//        List<WebElement> commentCounts = driver.findElements(COMMENTS);
//        int commentPageCommentsCount = removeBrackets(commentCounts.get(0)) + removeBrackets(commentCounts.get(1));
//        driver.findElement(PAGE_COMMENTS).click();
//        String commentPageTitle = driver.findElement(COMMENT_PAGE_ARTICLE_TITLE).getText().trim();

        Assertions.assertEquals(articlePageTitle, commentPageTitle, "Error with title");
        Assertions.assertEquals(homePageCommentsCount, commentPageCommentsCount, "Error with comments");
    }


//    // отдельный метод getCommentsCount - избавление от скобок
//    private int getCommentsCount (By locator) { //int- что возвращает функция
//        int commentsCount = 0;
//        if(!driver.findElements(locator).isEmpty()) {
//            commentsCount = removeBrackets(driver.findElement(locator));
//
//
////            String commentsCountText = driver.findElement(locator).getText();
////            commentsCountText = commentsCountText.substring(1, commentsCountText.length() - 1); // (36) --> 36 (String)
////            //отбрасывание скобок. результат- 36, тип String
////            // метод commentsCount.length() -1 -- т.к. мы не знаем кол-во цифр в комментах, мы узнаем длину строки и отнимаем 1 символ. и получаем предпоследний.
////            commentsCount = Integer.parseInt(commentsCountText);
////            // переделываем строку 36 типа String в тип данных int 36(String) --> 36(int),
//
//        }
//        return commentsCount;            // return- вернуть как результат работы метода
//    }
//
//    private int getCommentsCount (WebElement we, By locator) {
//        int commentsCount = 0;
//        if(!we.findElements(locator).isEmpty()) {
//            commentsCount = removeBrackets(we.findElement(locator));
//
//
////            String commentsCountText = we.findElement(locator).getText();
////            commentsCountText = commentsCountText.substring(1, commentsCountText.length() - 1); // (36) --> 36 (String)
////            //отбрасывание скобок. результат- 36, тип String
////            // метод commentsCount.length() -1 -- т.к. мы не знаем кол-во цифр в комментах, мы узнаем длину строки и отнимаем 1 символ. и получаем предпоследний.
////            commentsCount = Integer.parseInt(commentsCountText);
////            // переделываем строку 36 типа String в тип данных int 36(String) --> 36(int),
//
//        }
//        return commentsCount;            // return- вернуть как результат работы метода
//    }

//    private int removeBrackets(WebElement we) {
//        String commentsCountText = we.getText();
//        commentsCountText = commentsCountText.substring(1, commentsCountText.length() - 1); // (36) --> 36 (String)
//        //отбрасывание скобок. результат- 36, тип String
//        // метод commentsCount.length() -1 -- т.к. мы не знаем кол-во цифр в комментах, мы узнаем длину строки и отнимаем 1 символ. и получаем предпоследний.
//        return Integer.parseInt(commentsCountText);
//        // переделываем строку 36 типа String в тип данных int 36(String) --> 36(int),
//    }

    @AfterEach //аннотация- после прохождения каждого теста в этом классе
    public void closeBrowser() {
        baseFunc.closeBrowser();
    }

    // 36(String) --> 36(int), return- вернуть как результат работы метода
}




