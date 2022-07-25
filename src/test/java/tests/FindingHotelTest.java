package tests;

import org.junit.Test;
import pages.HomePage;
import pages.MapModalPage;
import pages.SearchResultsPage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FindingHotelTest extends BaseTest {
    @Test
    public void checkingFindHotel() {
        LocalDate currentDate = LocalDate.now();
        LocalDate startDate = currentDate.plusDays(2);
        LocalDate endDate = currentDate.plusDays(7);

        HomePage homePage = new HomePage();
        homePage
            .openPage()
            .inputCityToInput("Барселона")
            .setDate(startDate.toString(), endDate.toString())
            .setGuests()
            .clickSearch();

        SearchResultsPage searchResultsPage = new SearchResultsPage();
        searchResultsPage
            .checkCityText("Барселона")
            .checkStartDateText(startDate.format(DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy")))
            .checkEndDateText(endDate.format(DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy")))
            .checkGroupText("1 взрослый · 0 детей · 1 номер")
            .checkNightsText("2022-07-25", "2022-07-31")
            .checkResultSize();
    }

    @Test
    public void checkingSortHotelByClass() {
        LocalDate currentDate = LocalDate.now();
        LocalDate startDate = currentDate.plusDays(2);
        LocalDate endDate = currentDate.plusDays(7);

        HomePage homePage = new HomePage();
        homePage
            .openPage()
            .inputCityToInput("Барселона")
            .setDate(startDate.toString(), endDate.toString())
            .setGuests()
            .clickSearch();

        SearchResultsPage searchResultsPage = new SearchResultsPage();
        searchResultsPage
            .setItem5Class()
            .checkResultClass(5);
    }

    @Test
    public void checkingSortHotelByClassOnMap() {
        LocalDate currentDate = LocalDate.now();
        LocalDate startDate = currentDate.plusDays(2);
        LocalDate endDate = currentDate.plusDays(7);

        HomePage homePage = new HomePage();
        homePage
                .openPage()
                .inputCityToInput("Барселона")
                .setDate(startDate.toString(), endDate.toString())
                .setGuests()
                .clickSearch();

        SearchResultsPage searchResultsPage = new SearchResultsPage();
        searchResultsPage
                .setItem5Class()
                .clickButtonMap();

        MapModalPage mapModalPage = new MapModalPage();
        mapModalPage
                .checkRating(5);
    }
}
