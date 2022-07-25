package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class SearchResultsPage {

    private final By CITY_INPUT = By.cssSelector(".ce45093752");
    private final By START_DATE_INPUT = By.cssSelector(".d47738b911[data-testid=\"date-display-field-start\"]");
    private final By END_DATE_INPUT = By.cssSelector(".d47738b911[data-testid=\"date-display-field-end\"]");
    private final By NIGHTS_TEXT = By.cssSelector(".d8eab2cf7f.e2a710e162");
    private final By GROUP_INPUT = By.cssSelector(".d47738b911[data-testid=\"occupancy-config\"]");

    private final By RESULT_LIST = By.cssSelector(".d4924c9e74>[data-testid=\"property-card\"]");
    private final By RATING_STARS = By.cssSelector(".b6dc9a9e69.adc357e4f1");
    private final By CLASS_CLASS_5 = By.cssSelector("[data-filters-item=\"class:class=5\"]");
    private final By OVERLAY_CARD = By.cssSelector(".a1b3f50dcd.f7c6687c3d.bdf0df2d01.c6ff776fac");

    public final By BUTTON_MAP = By.cssSelector("[data-testid=\"map-trigger\"]");

    public SearchResultsPage checkCityText(String cityText) {
        $(CITY_INPUT).shouldBe(visible).shouldHave(attribute("value", cityText));
        return this;
    }

    public SearchResultsPage checkStartDateText(String startDateText) {
        $(START_DATE_INPUT).shouldHave(text(startDateText));
        return this;
    }

    public SearchResultsPage checkEndDateText(String endDateText) {
        $(END_DATE_INPUT).shouldHave(text(endDateText));
        return this;
    }

    public SearchResultsPage checkNightsText(String startDateText, String endDateText) {
        try {
            SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = null;
            startDate = myFormat.parse(startDateText);Date endDate = myFormat.parse(endDateText);
            int expectedDiffInDays = (int)( (startDate.getTime() - endDate.getTime()) / (1000 * 60 * 60 * 24) );
            int actualDiffInDays = Integer.parseInt($$(END_DATE_INPUT).last().getText().split(" ")[0]);
            assertTrue(expectedDiffInDays == actualDiffInDays);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return this;
    }

    public SearchResultsPage checkGroupText(String groupText) {
        $(GROUP_INPUT).shouldHave(text(groupText));
        return this;
    }

    public SearchResultsPage checkResultSize() {
        $$(GROUP_INPUT).shouldHave(sizeGreaterThan(0));
        return this;
    }

    public SearchResultsPage setItem5Class() {
        $(CLASS_CLASS_5).scrollTo().click();
        return this;
    }

    public SearchResultsPage checkResultClass(int Rating) {
        $(OVERLAY_CARD).shouldNotBe(exist);
        for(SelenideElement element: $$(RESULT_LIST)){
            assertEquals(element.findAll(RATING_STARS).size(), Rating);
        }
        return this;
    }

    public SearchResultsPage clickButtonMap() {
        $(OVERLAY_CARD).shouldNotBe(exist);
        $(BUTTON_MAP).scrollTo().click();
        return this;
    }
}
