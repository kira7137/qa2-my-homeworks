package pageobject.tickets.pages;

import model.Reservation;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageobject.BaseFunc;

import java.math.BigDecimal;
import java.util.List;

public class PassengerInfoPage {
    private final By INFO_TXT = By.xpath(".//span[@class = 'bTxt']");

    private final By RESPONSE_BLOCK = By.id("response");
    private final By BOOK_BTN = By.id("book2");

    private final By NAME_INPUT = By.id("name");
    private final By SURNAME_INPUT = By.id("surname");
    private final By DISCOUNT_INPUT = By.id("discount");
    private final By ADULTS_INPUT = By.id("adults");
    private final By CHILDREN_INPUT = By.id("children");
    private final By BUGS_INPUT = By.id("bugs");
    private final By FLIGHT_INPUT = By.id("flight");
    private final By GETPRICE_LINK = By.xpath(".//div[@id = 'fullForm']/span[@style = 'cursor: pointer;']");

    private BaseFunc baseFunc;

    public PassengerInfoPage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public List<WebElement> getAirports() {
        return baseFunc.findElements(INFO_TXT);
    }

    public PassengerInfoPage checkIfAirportsAre(String from, String to) {
        List<WebElement> airports = getAirports();

        Assertions.assertEquals(from, airports.get(0).getText(), "Wrong departure airport");
        Assertions.assertEquals(to, airports.get(1).getText(), "Wrong arrival airport");

        return this;
    }

    public PassengerInfoPage checkIfNameIs(String name) {
        Assertions.assertEquals(name, getName(), "Wrong name in info block");

        return this;
    }

    public PassengerInfoPage checkIfTotalPriceIs(BigDecimal price) {
        Assertions.assertEquals(price, getPrice(), "Wrong price in info block");

        return this;
    }

    public PassengerInfoPage submitPassengerInfo(Reservation reservation) {
        baseFunc.type(NAME_INPUT, reservation.getName());
        baseFunc.type(SURNAME_INPUT, reservation.getSurname());
        baseFunc.type(DISCOUNT_INPUT, reservation.getDiscount());
        baseFunc.type(ADULTS_INPUT, reservation.getAdults());
        baseFunc.type(CHILDREN_INPUT, reservation.getChildren());
        baseFunc.type(BUGS_INPUT, reservation.getBugs());
        baseFunc.select(FLIGHT_INPUT, reservation.getFullDate());

        baseFunc.click(GETPRICE_LINK);

        return this;
    }

    public String getName() {
        return baseFunc.getText(RESPONSE_BLOCK, INFO_TXT).replaceAll("!", "");
    }

    public BigDecimal getPrice() {
        String fullText = baseFunc.getText(RESPONSE_BLOCK);
        String price = StringUtils.substringBetween(fullText, "for ", " EUR");
        return new BigDecimal(price);
    }

    public SeatsPage book() {
        baseFunc.click(BOOK_BTN);
        return new SeatsPage(baseFunc);
    }
}
