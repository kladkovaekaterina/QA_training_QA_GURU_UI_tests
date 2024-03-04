import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080"; // браузер селениум поднимает без всяких плагинов, адблоков и утилит
        Configuration.browser = "edge"; // по умолчанию хром
        Configuration.pageLoadStrategy = "eager"; // не ждем окончания загрузки страницы
        //Configuration.holdBrowserOpen = true; // браузер не закрывается после прохождения тестов, перед пушем в мастер закомментировать
    }
}