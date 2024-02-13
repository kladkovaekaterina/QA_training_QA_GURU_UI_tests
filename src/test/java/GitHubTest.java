import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
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
        open("https://github.com/");
        $("[placeholder='Search or jump to...']").click();
        $("#query-builder-test").setValue("Selenide").pressEnter();
        $$("[data-testid='results-list'] div").first().$("a").click();
        $("#wiki-tab").click();
        $(".markdown-body").shouldHave(text("Soft assertions"));
        $$(".markdown-body li").findBy(text("Soft assertions")).$("a").click();
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
                                """));
    }

}