import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

// HW8
public class ParameterizedExampleTest extends TestBase {

    static Stream<Arguments> websiteShouldDisplayCorrectButtons() {
        return Stream.of(
                Arguments.of(Language.EN, List.of("Quick Summary", "Full Specification", "Contribute")),
                Arguments.of(Language.RU, List.of("Главное", "Спецификация", "GitHub"))
        );
    }

    @ParameterizedTest(name = "При смене языка на {0} должны меняться названия кнопок на {1}")
    @MethodSource
    void websiteShouldDisplayCorrectButtons(Language language, List<String> expectedButtons) {
        open("https://www.conventionalcommits.org/");
        $$(".dropdown__label").find(text("Languages")).click();
        $$(".dropdown__option").find(text(language.fullName)).click();
        $$(".welcome__actions a").filter(visible).shouldHave(texts(expectedButtons));
    }

    @ParameterizedTest(name = "Поиск по ключевому слову {0} должен выдать несколько результатов")
    @ValueSource(strings = {"Кошелек", "Перевод"})
    void searchResultsShouldNotBeEmpty(String searchQuery) {
        open("https://qiwi.com/");
        $(".css-9uy14h").click();
        $("[type=search]").setValue(searchQuery).pressEnter();
        $$(".css-2imjyh div[title]").shouldBe(sizeGreaterThan(0));
    }

    @ParameterizedTest(name = "Заголовок должен содержать имя карточки {1}")
    @CsvFileSource(resources = "/searchResultsShouldContainSpecificData.csv", numLinesToSkip = 1)
    void searchResultsShouldContainSpecificData(String searchQuery, String expectedText) {
        open("https://qiwi.com/");
        $(".css-9uy14h").click();
        $("[type=search]").setValue(searchQuery).pressEnter();
        $$(".css-2imjyh div[title]").find(text(expectedText)).click();
        $(".content-block-title-self-335").shouldHave(text(expectedText));
    }
}