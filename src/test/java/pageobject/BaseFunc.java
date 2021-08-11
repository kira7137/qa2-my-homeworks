package pageobject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BaseFunc {         //osnovnoj klass, otvechaushij toljko za rabotu s brauserom

    private final Logger LOGGER = LogManager.getLogger(this.getClass()); //this - berem tekushij class, universalnij ukazatel na tekushij class
    WebDriver driver;
    WebDriverWait wait;

    public BaseFunc() { //sozdaem Constructor
        //метод, конструирующий объект в момент создания копии объекта, вызывается автоматически
        // содержит код, кот.подгатавливает класс к работе, вызывается принудительно в момент создания копии объекта
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

    public void click(WebElement element) {
        LOGGER.info("Clicking on web element");
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public List<WebElement> findElements(By locator) {
        LOGGER.info("Getting list of elements by: " + locator);
        return driver.findElements(locator);
    }

    public List<WebElement> findElements(WebElement parent, By child) {
        LOGGER.info("Getting all child elements");
        return parent.findElements(child);
    }

    public String getText(WebElement parent, By child) {
        LOGGER.info("Getting text for child element by locator");
        return wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(parent, child)).getText(); //ozidanie dlja pojavlenie elementa
    }

    public String getText(By locator) {
        LOGGER.info("Getting text from web element");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    public String getText(By parent, By child) {
        return wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(parent, child)).getText();
    }

    public void closeBrowser() {
        LOGGER.info("Closing browser window");
        if (driver != null) { //proverka na to chto browser bil otkrit
            driver.close();
        }
    }

    public WebElement findElement(By locator) {
        LOGGER.info("Gettng element by locator" + locator);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void select(By dropdown, String text) {
        LOGGER.info("Selecting " + text + " from dropdown by locator: " + dropdown);
        Select select = new Select(findElement(dropdown));
        select.selectByVisibleText(text);
    }

    public void type(By locator, String text) {
        LOGGER.info("Typing " + text + "info " + locator);
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        input.clear(); //clear- ochistitj ot probelov, udalitj vse chto estj v okne vvoda
        input.sendKeys(text);
    }

    public void type(By locator, int text) { //preobrazovanie v String
        type(locator, String.valueOf(text));
    }

    public void waitForElementsCountToBeMoreThan(By locator, int count) {
        LOGGER.info("Waiting for elements count to be " + count);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(locator, count));
    }
}
