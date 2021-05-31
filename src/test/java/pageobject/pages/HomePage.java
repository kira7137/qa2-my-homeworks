package pageobject.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class HomePage { //TOLJKO glavnaja stranica i ee lokatori
    private final By ACCEPT_COOKIE_BTN = By.xpath(".//button[@mode = 'primary']");
    private final By TITLE = By.xpath(".//h1[contains(@class, 'headline__title')]");
    private final By ARTICLE = By.tagName("article"); //все статьи на главной странице
    private final By COMMENTS = By.xpath(".//a[contains(@class, 'comment-count')]"); //все комментарии на гл.стр

    private final Logger LOGGER = LogManager.getLogger(this.getClass());
    private BaseFunc baseFunc;

    public HomePage(BaseFunc baseFunc) { //sozdanie Constructor' a; chtobi page zaprashival brauzernoe okno
        this.baseFunc = baseFunc; //peremennaja na urovne klassa, t.k. u nas baseFunc dve
    }

    public void acceptCookies() {
        LOGGER.info("Accepting cookies");
        baseFunc.click(ACCEPT_COOKIE_BTN); //chtobi page zaprashival brauzernoe okno
    }
}
