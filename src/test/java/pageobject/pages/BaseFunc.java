package pageobject.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseFunc {         //osnovnoj klass, otvechaushij za rabotu s brauserom

    private final Logger LOGGER = LogManager.getLogger(this.getClass()); //this - berem tekushij class, universalnij ukazatel na tekushij class
    WebDriver driver;
    WebDriverWait wait;

    public BaseFunc() { //sozdaem Constructor
       LOGGER.info("Starting web browser");
       System.setProperty("webdriver.chrome.driver", "c://chromedriver.exe");
       driver = new ChromeDriver();
       driver.manage().window().maximize();
       wait = new WebDriverWait(driver, 10);
   }
    public void openPage(String url) { //metod dlja perehoda po konkretnoj ssilke; String url - vhodnoj parametr
        LOGGER.info("Opening page by URL: " + url);
        //proverki, prostavlen li https v nachale adresa
        if (!url.startsWith("http://") && !url.startsWith("https://")) { //esli nash url NE nachinaetsja s http i NE nachin. s https...
            url = "http://" + url; //to mi dobavljaem emu http
        }
        driver.get(url);
    }
    public void click(By locator) {
        LOGGER.info("Clicking on element by: " + locator);
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }
}
