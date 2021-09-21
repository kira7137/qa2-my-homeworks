package pageobject.tickets.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import pageobject.BaseFunc;

public class SuccessPage {
    private BaseFunc baseFunc;
    private final String SUCCESS_MSG = "Thank You for flying with us!";
    private final By SUCCESS_TXT = By.xpath(".//div[@class = 'finalTxt']");

    public SuccessPage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public String getMessage() {
        return baseFunc.getText(SUCCESS_TXT);
    }

    public SuccessPage checkSuccessMessage() {
        Assertions.assertEquals(SUCCESS_MSG, getMessage(), "Cant find success msg");
        return this;
    }
}
