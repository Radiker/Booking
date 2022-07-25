package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static junit.framework.TestCase.assertEquals;

public class MapModalPage {

    private final By MAP_MODAL = By.cssSelector(".map_with_list__container");
    private final By MAP_LEFT_CARDS = By.cssSelector(".map_left_cards");
    private final By CLASSIFICATION_RATING = By.cssSelector(".c-accommodation-classification-rating");
    private final By RATING_STARS = By.cssSelector(".bui-rating__item");


    public MapModalPage checkRating(int rating) {
        $(MAP_MODAL).shouldHave(Condition.exist);
        for(SelenideElement element: $(MAP_LEFT_CARDS).findAll(CLASSIFICATION_RATING)){
            assertEquals(element.findAll(RATING_STARS).size(), rating);
        }
        return this;
    }
}
