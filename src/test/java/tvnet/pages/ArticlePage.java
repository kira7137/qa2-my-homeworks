package tvnet.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class ArticlePage {
    private final By TITLE = By.xpath(".//h1[@class = 'article-headline']");
    private final By COMMENTS = By.xpath(".//span[contains (@class, 'article-share__item')]");
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
            int commentPageCommentsCount = Integer.parseInt(baseFunc.findElement(COMMENTS).getText());
            return commentPageCommentsCount;
        }
    }

//    public int getCommentsCount() {
//        LOGGER.info("Getting article comments count");
//       int commentPageCommentsCount = Integer.parseInt(baseFunc.findElement(COMMENTS).getText());
//        return commentPageCommentsCount;
//    }

    public CommentPage openCommentsPage() {
        LOGGER.info("Opening article comments page");
        baseFunc.click(COMMENTS);
        return new CommentPage(baseFunc);
    }
}
