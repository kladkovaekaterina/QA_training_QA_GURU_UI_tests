import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class GitHubTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browser = "firefox";
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = true;
    }

    // HW4
    @Test
    void softAssertionsJUnit5CodeCheck() {
        // Откройте страницу Selenide в Github
        open("https://github.com/selenide/selenide");
        // Перейдите в раздел Wiki проекта
        $("#wiki-tab").click();
        // Убедитесь, что в списке страниц (Pages) есть страница SoftAssertions
        $(".wiki-rightbar").$(withText("more pages")).click();
        $(".wiki-rightbar").shouldHave(text("SoftAssertions"));
        // Откройте страницу SoftAssertions
        $$(".js-wiki-sidebar-toggle-display li").findBy(text("SoftAssertions")).$("a").click();
        // Проверьте что внутри есть пример кода для JUnit5
        $(".markdown-body").shouldHave(text("Using JUnit5 extend test class:"));
        $(".markdown-body").shouldHave(text(""" 
                                @ExtendWith({SoftAssertsExtension.class})
                                class Tests {
                                  @Test
                                  void test() {
                                    Configuration.assertionMode = SOFT;
                                    open("page.html");
                                       
                                    $("#first").should(visible).click();
                                    $("#second").should(visible).click();
                                  }
                                }
                                """)); // проверка примера кода многострочным литералом
    }

    // HW5
    @Test
    void enterpriseCheck() {
        open("https://github.com");
        // На главной странице GitHub выберите меню Solutions -> Enterprize с помощью команды hover для Solutions
        $(".header-menu-wrapper").$(byText("Solutions")).hover();
        $(byText("Enterprise")).click(); // setTimeout(function () {debugger}, 4000)
        // Убедитесь что загрузилась нужная страница
        $("#hero-section-brand-heading").shouldHave(text("developer platform"));
    }

}