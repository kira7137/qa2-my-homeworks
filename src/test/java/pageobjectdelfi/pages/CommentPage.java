package pageobjectdelfi.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CommentPage {
    private final By COMMENTS = By.xpath(".//span[@class = 'type-cnt']");
    private final By COMMENT_PAGE_ARTICLE_TITLE = By.xpath(".//h1[@class = 'article-title']");
    private final Logger LOGGER = LogManager.getLogger(this.getClass());
    private BaseFunc baseFunc;

    public CommentPage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public List<WebElement> commentCounts() {
        LOGGER.info("Getting registered & anonymous comments for article");
        List<WebElement> commentCounts = baseFunc.findElements(COMMENTS);
        return commentCounts;
    }

    public int getCommentsCount() {
        LOGGER.info("Getting article comments count, remove brackets & calculate all comments");
        int commentPageCommentsCount = removeBrackets(commentCounts().get(0)) + removeBrackets(commentCounts().get(1));
        return commentPageCommentsCount;
    }

        private int removeBrackets(WebElement we) {
        String commentsCountText = we.getText();
        commentsCountText = commentsCountText.substring(1, commentsCountText.length() - 1); // (36) --> 36 (String)
        //отбрасывание скобок. результат- 36, тип String
        // метод commentsCount.length() -1 -- т.к. мы не знаем кол-во цифр в комментах, мы узнаем длину строки и отнимаем 1 символ. и получаем предпоследний.
        return Integer.parseInt(commentsCountText);
        // переделываем строку 36 типа String в тип данных int 36(String) --> 36(int),
    }

    public String getTitle() {
        LOGGER.info("Getting article title");
        return baseFunc.getText(COMMENT_PAGE_ARTICLE_TITLE);
    }
}
