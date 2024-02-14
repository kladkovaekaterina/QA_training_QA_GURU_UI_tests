import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class GitHubTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browser = "firefox";
        Configuration.pageLoadStrategy = "eager";
        //Configuration.holdBrowserOpen = true;
    }

    @Test
    void softAssertionsJUnit5CodeCheck() {
        open("https://github.com/selenide/selenide");
        $("#wiki-tab").click();
        $(".wiki-rightbar").$(withText("more pages")).click();
        $(".wiki-rightbar").shouldHave(text("SoftAssertions"));
        $$(".js-wiki-sidebar-toggle-display li").findBy(text("SoftAssertions")).$("a").click();
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

}