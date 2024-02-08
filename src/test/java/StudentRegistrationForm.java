import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.File;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class StudentRegistrationForm {

    @BeforeAll
    static void beforeAll() {
       Configuration.browserSize = "1920x1080";
       Configuration.browser = "firefox"; // по умолчанию хром
       Configuration.baseUrl = "https://demoqa.com"; // базовый урл здесь, путь до конкретной страницы в тестах
       Configuration.pageLoadStrategy = "eager"; // не ждем окончания загрузки страницы
       Configuration.holdBrowserOpen = true; // браузер не закрывается после прохождения тестов
    }

    @Test
    void fillFormTest() {
        open("/automation-practice-form"); // https://demoqa.com/automation-practice-form
        // Name
        $("#firstName").setValue("aaa"); // id="firstName", либо $("[id=firstName]"), либо вместо id можно использовать #
        $("#lastName").setValue("bbb");
        // Email
        $("#userEmail").setValue("ccc@ddd.ru");
        // Gender
        $("label[for='gender-radio-2']").click();
        // Mobile
        $("#userNumber").setValue("1234567890");
        // Date of Birth
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionContainingText("June"); // выбор из ниспадающего списка
        $(".react-datepicker__year-select").selectOptionContainingText("1992");
        $("div[aria-label='Choose Thursday, June 4th, 1992']").click();
        // Subjects
        $(".subjects-auto-complete__value-container").click();
        $("#subjectsInput").press("eng");
        $(byText("English")).click();
        $("#subjectsInput").press("com");
        $(byText("Computer Science")).click();
        // Hobbies
        $("label[for='hobbies-checkbox-3']").click();
        // Picture
        $("#uploadPicture").uploadFile(new File("File for tests/1.JPG"));
        // Current Address
        $("#currentAddress").setValue("Ulitsa Pushkina, dom Kolotushkina");
        // State and City
        $("#state").click();
        $("#react-select-3-option-2").click();
        $("#city").click();
        $("#react-select-4-option-1").click();
        // Submit
        $("#submit").click();
        // Check results
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));

    }
}