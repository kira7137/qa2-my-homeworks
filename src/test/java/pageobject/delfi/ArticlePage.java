package pageobject.delfi;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import pageobject.BaseFunc;

public class ArticlePage {
    private final By TITLE = By.xpath(".//h1[contains(@class, 'text-size-md-30')]");
    private final By COMMENTS = By.xpath(".//a[contains(@class, 'text-size-md-28')]");
    private final Logger LOGGER = LogManager.getLogger(this.getClass());
    private final By PAGE_COMMENTS = By.xpath(".//a[contains(@class, 'text-red-ribbon')]");

    private BaseFunc baseFunc;

    public ArticlePage(BaseFunc baseFunc) { //Constructor
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

//    public openCommentsPage(By PAGE_COMMENTS) {
//        LOGGER.info("Opening article comments page");
//        baseFunc.click(PAGE_COMMENTS);
//    }

    public CommentPage openCommentsPage() {
        LOGGER.info("Opening article comments page");
        baseFunc.click(PAGE_COMMENTS);
        return new CommentPage(baseFunc);
    }

}
