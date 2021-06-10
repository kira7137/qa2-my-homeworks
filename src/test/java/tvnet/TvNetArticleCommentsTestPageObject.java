package tvnet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import tvnet.pages.ArticlePage;
import tvnet.pages.BaseFunc;
import tvnet.pages.CommentPage;
import tvnet.pages.HomePage;


public class TvNetArticleCommentsTestPageObject {

    private WebDriver driver;

    private final Logger LOGGER = LogManager.getLogger(this.getClass());
    private final int ARTICLE_ID = 4;

    private BaseFunc baseFunc;

    @Test
    public void titlesCommentsCountCheck() {
        LOGGER.info("Checking titles & comments count on home/article/comment pages");
        baseFunc = new BaseFunc();
        baseFunc.openPage("https://www.tvnet.lv/");

        //---------------------HOME PAGE---------------------------------

        HomePage homePage = new HomePage(baseFunc);
        homePage.acceptCookies();
 //       homePage.scrollDown();

        String homePageTitle = homePage.getTitle(ARTICLE_ID).replaceAll("\\(\\d+\\)$", "").trim();
        int homePageCommentsCount = homePage.getCommentsCount(ARTICLE_ID);

        ArticlePage articlePage = homePage.openArticle(ARTICLE_ID);

        //----------------ARTICLE PAGE---------------------

        String articlePageTitle = articlePage.getTitle().replaceAll("\\(\\d+\\)$", "").trim();

        int articlePageCommentsCount = articlePage.getCommentsCount();

       Assertions.assertEquals(homePageTitle, articlePageTitle, "Wrong title");
       Assertions.assertEquals(homePageCommentsCount, articlePageCommentsCount, "Wrong comments count");

        CommentPage commentPage = articlePage.openCommentsPage();

       //--------------COMMENTS PAGE--------------------

        String commentPageTitle = commentPage.getTitle().replaceAll("\\(\\d+\\)$", "").trim();
        int commentPageCommentsCount = commentPage.getCommentsCount();

//        driver.findElement(ARTICLE_PAGE_COMMENTS).click();
//
//        String commentPageTitle = driver.findElement(COMMENT_PAGE_ARTICLE_TITLE).getText().trim();
//        int commentPageCommentsCount = Integer.parseInt(driver.findElement(COMMENT_PAGE_COMMENTS).getText());

        Assertions.assertEquals(articlePageTitle, commentPageTitle, "Error with title");
        Assertions.assertEquals(homePageCommentsCount, commentPageCommentsCount, "Error with comments");
    }

    @AfterEach
    public void closeBrowser() {
        baseFunc.closeBrowser();
    }

//    private int removeBrackets(WebElement we) {
//        String commentsCountText = we.getText();
//        commentsCountText = commentsCountText.substring(1, commentsCountText.length() - 1);
//        return Integer.parseInt(commentsCountText);
//    }
//
//    private int getCommentsCount (By locator) {
//        int commentsCount = 0;
//        if(!driver.findElements(locator).isEmpty()) {
//            commentsCount = removeBrackets(driver.findElement(locator));
//        }
//        return commentsCount;
//    }
//
//    private int getCommentsCount (WebElement we, By locator) {
//        int commentsCount = 0;
//        if(!we.findElements(locator).isEmpty()) {
//            commentsCount = removeBrackets(we.findElement(locator));
//        }
//        return commentsCount;
//    }

}
