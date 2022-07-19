package tests;

import org.junit.Test;
import pages.HomePage;
import pages.SearchResultsPage;

public class FindingHotelTest extends BaseTest {
    @Test
    public void checkingFindHotel() {
        HomePage homePage = new HomePage();
        homePage
            .openPage()
            .inputCityToInput("Барселона")
            .setDate("2022-07-25", "2022-07-31")
            .setGuests()
            .clickSearch();

        SearchResultsPage searchResultsPage = new SearchResultsPage();
        searchResultsPage
            .checkCityText("Барселона")
            .checkStartDateText("понедельник, 25 июля 2022")
            .checkEndDateText("воскресенье, 31 июля 2022")
            .checkGroupText("1 взрослый · 0 детей · 1 номер")
            .checkNightsText("2022-07-25", "2022-07-31")
            .checkResultSize();
    }
}
