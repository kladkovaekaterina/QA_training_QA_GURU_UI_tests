import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class WebSteps {

    @Step("Открыть главную страницу")
    public void openMainPage() {
        open("https://github.com");
    }

    @Step("Ввести в поисковую строку название репозитория {repository}")
    public void searchRepository(String repository) {
        $(".header-search-button").click();
        $("#query-builder-test").setValue(repository).pressEnter();
    }

    @Step("Кликнуть по ссылке репозитория {repository}")
    public void clickOnRepositoryLink(String repository) {
        $(linkText(repository)).click();
    }

    @Step("Открыть tab Issues")
    public void openTabIssues() {
        $("#issues-tab").click();
    }

    @Step("Проверить наличие Issue с названием {issueName}")
    public void checkIssueExistence(String issueName) {
        $(withText(issueName)).should(Condition.exist);
    }
}