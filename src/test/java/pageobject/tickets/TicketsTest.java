package pageobject.tickets;
import model.Reservation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pageobject.BaseFunc;
import pageobject.tickets.pages.HomePage;
import java.math.BigDecimal;

public class TicketsTest {
    private final String URL = "qaguru.lv:8089/tickets";
    private Reservation reservation = new Reservation();
    private BaseFunc baseFunc = new BaseFunc();

    @BeforeEach
    public void preparedData() {
        reservation.setAfrom("RIX");
        reservation.setAto("SVO");
        reservation.setName("random");
        reservation.setSurname("The Tester");
        reservation.setDiscount("CCCCCC");
        reservation.setAdults(4);
        reservation.setChildren(2);
        reservation.setBugs(2);
        reservation.setFullDate("16-05-2018");
        reservation.setSeat(21);
    }

@Test
    public void checkReservation() {
    baseFunc.openPage(URL);
    HomePage homePage = new HomePage(baseFunc);

    homePage.selectAirports(reservation.getAfrom(), reservation.getAto())
            .checkIfAirportsAre(reservation.getAfrom(), reservation.getAto())
            .submitPassengerInfo(reservation)
            .checkIfNameIs(reservation.getName())
            .checkIfTotalPriceIs(new BigDecimal(3600))
            .book()
            .selectSeat(reservation.getSeat())
            .checkIfSelectedSeatIs(reservation.getSeat())
            .book()
            .checkSuccessMessage();

//    Assertions.assertEquals(reservation.getName(), infoPage.getName(), "Wrong name in info block");
//    Assertions.assertEquals(new BigDecimal(3600), infoPage.getPrice(), "Wrong price in info block");
//    SeatsPage seatsPage = infoPage.book();
//    seatsPage.selectSeat(reservation.getSeat());
//    SuccessPage successPage = seatsPage.book();

}
 @AfterEach
    public void closeBrowser() {
        baseFunc.closeBrowser();
 }
}
