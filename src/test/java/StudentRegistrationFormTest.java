import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.File;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class StudentRegistrationFormTest { // в названиях классов с тестами в конце принято писать Test

    @BeforeAll
    static void beforeAll() {
       Configuration.browserSize = "1920x1080"; // браузер селениум поднимает без всяких плагинов, адблоков и утилит
       Configuration.browser = "firefox"; // по умолчанию хром
       Configuration.baseUrl = "https://demoqa.com"; // базовый урл здесь, путь до конкретной страницы в тестах
       Configuration.pageLoadStrategy = "eager"; // не ждем окончания загрузки страницы
       Configuration.holdBrowserOpen = true; // браузер не закрывается после прохождения тестов, перед пушем в мастер ставить !false!
    }

    @Test
    void fillFormTest() {
        // Open form
        open("/automation-practice-form"); // https://demoqa.com/automation-practice-form
        executeJavaScript("$('#fixedban').remove()"); // джаваскриптовый код для скрытия футера и рекламы
        executeJavaScript("$('footer').remove()"); // джаваскриптовый код для скрытия футера и рекламы

        // Insert data
        // Name
        $("#firstName").setValue("aaa"); // id="firstName", либо $("[id=firstName]"), либо вместо id можно использовать #
        $("#lastName").setValue("bbb");
        // Email
        $("#userEmail").setValue("ccc@ddd.ru");
        // Gender
        $("#genterWrapper").$(byText("Female")).click();
        // Mobile
        $("#userNumber").setValue("1234567890");
        // Date of Birth
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionContainingText("June"); // выбор из ниспадающего списка
        $(".react-datepicker__year-select").selectOptionContainingText("1992");
        $(".react-datepicker__day--004").click();
        // Subjects
        $(".subjects-auto-complete__value-container").click();
        $("#subjectsInput").press("eng");
        $(byText("English")).click();
        $("#subjectsInput").press("com");
        $(byText("Computer Science")).click();
        // Hobbies
        $("#hobbiesWrapper").$(byText("Music")).click();
        // Picture
        $("#uploadPicture").uploadFromClasspath("picture.JPG"); // различные файлы, изображения принято хранить в специальной папке resources, в таком случае картинка будет подтягиваться по имени файла из папки resources
        // Current Address
        $("#currentAddress").setValue("Ulitsa Pushkina, dom Kolotushkina");
        // State and City
        $("#state").click();
        $("#stateCity-wrapper").$(byText("Uttar Pradesh")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Agra")).click();
        // Submit
        $("#submit").click();

        // Check results
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text("aaa bbb"));
        $(".table-responsive").shouldHave(text("ccc@ddd.ru"));
        $(".table-responsive").shouldHave(text("Female"));
        $(".table-responsive").shouldHave(text("1234567890"));
        $(".table-responsive").shouldHave(text("04 June,1992"));
        $(".table-responsive").shouldHave(text("English, Computer Science"));
        $(".table-responsive").shouldHave(text("Music"));
        $(".table-responsive").shouldHave(text("picture.JPG"));
        $(".table-responsive").shouldHave(text("Ulitsa Pushkina, dom Kolotushkina"));
        $(".table-responsive").shouldHave(text("Uttar Pradesh Agra"));
    }
}