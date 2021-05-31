package pageobject.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class CommentPage {
    private final By PAGE_COMMENTS = By.xpath(".//a[contains(@class, 'text-red-ribbon')]");
    private final By COMMENTS = By.xpath(".//span[@class = 'type-cnt']");
    private final By COMMENT_PAGE_ARTICLE_TITLE = By.xpath(".//h1[@class = 'article-title']");
    private final Logger LOGGER = LogManager.getLogger(this.getClass());

}
