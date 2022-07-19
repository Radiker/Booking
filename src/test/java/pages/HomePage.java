package pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.devtools.v85.dom.model.ShadowRootType;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static tests.BaseTest.cfg;

public class HomePage {

    private final By CITY_INPUT = By.cssSelector(".sb-destination__input");
    private final By CITY_LIST = By.cssSelector(".c-autocomplete__list[role=\"listbox\"]>.sb-autocomplete__item");

    private final By CALENDAR_BUTTON = By.cssSelector(".calendar-restructure-sb");
    private final By CALENDAR_DATE = By.cssSelector(".bui-calendar__date");

    private final By GUESTS_INPUT = By.cssSelector(".xp__input-group.xp__guests");
    private final By GUESTS_ADULTS_DESC = By.cssSelector(".bui-stepper__subtract-button[aria-describedby=\"group_adults_desc\"]");

    private final By SEARCH_BUTTON = By.cssSelector(".sb-searchbox__button");

    public HomePage openPage() {
        open(cfg.baseUrl());
        return this;
    }

    public HomePage inputCityToInput(String City) {
        $(CITY_INPUT).sendKeys(City);
        $$(CITY_LIST).shouldHave(sizeGreaterThan(0)).filterBy(text(City)).first().click();
        return this;
    }

    public HomePage setDate(String startDate, String endDate) {
        //$(CALENDAR_BUTTON).click();
        $$(CALENDAR_DATE).shouldHave(sizeGreaterThan(0)).filterBy(attribute("data-date",startDate)).first().click();
        $$(CALENDAR_DATE).shouldHave(sizeGreaterThan(0)).filterBy(attribute("data-date",endDate)).first().click();
        return this;
    }

    public HomePage setGuests() {
        $(GUESTS_INPUT).click();
        $(GUESTS_ADULTS_DESC).click();
        return this;
    }

    public HomePage clickSearch() {
        $(SEARCH_BUTTON).click();
        return this;
    }
}
