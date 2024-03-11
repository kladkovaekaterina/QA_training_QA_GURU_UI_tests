import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

// HW10
// тест на проверку названия Issue в репозитории через Web-интерфейс
public class AllureTest extends TestBase {

    //1. Чистый Selenide (с Listener)
    //
    //2. Лямбда шаги через step (name, () -> {})
    //
    //3. Шаги с аннотацией @Step
    @Test
    void someTest() {
        open("https://github.com");
        $(".header-search-button").click();
        $("#query-builder-test").setValue("qa-guru/qa_guru_14_10").pressEnter();
        $(linkText("qa-guru/qa_guru_14_10")).click();
        $("#issues-tab").click();
        $(withText("#2")).should(Condition.exist);
    }
}