package pageobject.tickets.pages;

import org.openqa.selenium.By;
import pageobject.BaseFunc;

public class SuccessPage {
    private BaseFunc baseFunc;
    private final By SUCCESS_TXT = By.xpath(".//div[@class = 'finalTxt']");

    public SuccessPage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public String getMessage() {
        return baseFunc.getText(SUCCESS_TXT);
    }
}
