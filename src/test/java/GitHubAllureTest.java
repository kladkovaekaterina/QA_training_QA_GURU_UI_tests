import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

// HW10
@DisplayName("Тест на проверку названия Issue в репозитории через Web-интерфейс")
public class GitHubAllureTest extends TestBase {

    private static final String REPOSITORY = "qa-guru/qa_guru_14_10",
                                ISSUE_NAME = "Issue for Autotest";

    // 1. Чистый Selenide (с Listener)
    @Test
    @DisplayName("Чистый Selenide (с Listener)")
    void checkIssueTabListener() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com");
        $(".header-search-button").click();
        $("#query-builder-test").setValue(REPOSITORY).pressEnter();
        $(linkText(REPOSITORY)).click();
        $("#issues-tab").click();
        $(withText(ISSUE_NAME)).should(Condition.exist);
    }

    // 2. Лямбда шаги через step (name, () -> {})
    @Test
    @DisplayName("Лямбда шаги через step (name, () -> {})")
    void checkIssueTabLambda() {
        step("Открыть главную страницу", () -> {
            open("https://github.com");
        });
        step("Ввести в поисковую строку название репозитория " + REPOSITORY, () -> {
            $(".header-search-button").click();
            $("#query-builder-test").setValue(REPOSITORY).pressEnter();
        });
        step("Кликнуть по ссылке репозитория " + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });
        step("Открыть tab Issues", () -> {
            $("#issues-tab").click();
        });
        step("Проверить наличие Issue с названием " + ISSUE_NAME, () -> {
            $(withText(ISSUE_NAME)).should(Condition.exist);
        });
    }

    // 3. Шаги с аннотацией @Step
    WebSteps steps = new WebSteps();

    @Test
    @DisplayName("Шаги с аннотацией @Step")
    void checkIssueTabAnnotations() {
        steps.openMainPage();
        steps.searchRepository(REPOSITORY);
        steps.clickOnRepositoryLink(REPOSITORY);
        steps.openTabIssues();
        steps.checkIssueExistence(ISSUE_NAME);
        steps.attachScreenshot();
        /* или
        Allure.getLifecycle().addAttachment(
                "Скриншот результатов прохождения автотеста",
                "text/html",
                "html",
                WebDriverRunner.getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8)
        );      */
    }
}