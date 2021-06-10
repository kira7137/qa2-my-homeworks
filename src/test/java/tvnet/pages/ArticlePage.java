package tvnet.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ArticlePage {
    private final By TITLE = By.xpath(".//h1[@class = 'article-headline']");
    private final By COMMENTS = By.xpath(".//span[contains (@class, 'article-share__item--count')]");
    private final Logger LOGGER = LogManager.getLogger(this.getClass());

    private BaseFunc baseFunc;

    public ArticlePage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public String getTitle() {
        LOGGER.info("Getting article title");
        return baseFunc.getText(TITLE).trim();
    }

    public int getCommentsCount() {
        LOGGER.info("Getting article comments count");
        if (baseFunc.findElements(COMMENTS).isEmpty()) {
            return 0;
        } else {
            String commentsCountToParse = baseFunc.getText(COMMENTS);
            commentsCountToParse = commentsCountToParse.substring(1, commentsCountToParse.length() - 1);
            return Integer.parseInt(commentsCountToParse);
        }
    }

//    public WebElement getArticleById(int id) {
//        LOGGER.info("Getting article Nr. " + (id + 1));
//        List<WebElement> articles = baseFunc.findElements(TITLE);
//        Assertions.assertFalse(articles.isEmpty(), "There are no articles");
//        Assertions.assertTrue(articles.size() > id, "Article amount is less than id");
//        return articles.get(id);
//    }
//
//    public int getCommentsCount(int id) {
//        LOGGER.info("Getting comments for article Nr.: " + (id + 1));
//        if (baseFunc.findElements(getArticleById(id), COMMENTS).isEmpty()) {
//            return 0;
//        } else {
//            String commentsCountToParse = baseFunc.getText(getArticleById(id), COMMENTS);
//            commentsCountToParse = commentsCountToParse.substring(1, commentsCountToParse.length() - 1);
//            return Integer.parseInt(commentsCountToParse);
//        }
//    }



    public CommentPage openCommentsPage() {
        LOGGER.info("Opening article comments page");
        baseFunc.click(COMMENTS);
        return new CommentPage(baseFunc);
    }
}
