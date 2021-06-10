package tvnet.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class CommentPage {
    private final By COMMENT_PAGE_ARTICLE_TITLE = By.xpath(".//h1[@class = 'article-headline']");
    private final By COMMENT_PAGE_COMMENTS = By.xpath(".//span[@class = 'flex flex--align-items-center flex--justify-content-center article-comments-heading__count section-bg-color']");
    private final Logger LOGGER = LogManager.getLogger(this.getClass());
    private BaseFunc baseFunc;

    public CommentPage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public String getTitle() {
        LOGGER.info("Getting article title");
        return baseFunc.getText(COMMENT_PAGE_ARTICLE_TITLE).replaceAll("\\(\\d+\\)$", "").trim();
    }

    public int getCommentsCount() {
        LOGGER.info("Getting article comments count");
        int commentPageCommentsCount = Integer.parseInt(baseFunc.findElement(COMMENT_PAGE_COMMENTS).getText());
        return commentPageCommentsCount;
    }
}
