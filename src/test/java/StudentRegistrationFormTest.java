import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

// HW3
public class StudentRegistrationFormTest extends TestBase { // в названиях классов с тестами в конце принято писать Test

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com"; // абсолютный урл
    }

    @Test
    void fillFormTest() {
        // Open form
        open("/automation-practice-form"); // относительный урл, https://demoqa.com/automation-practice-form
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
        $(".react-datepicker__month-select").selectOptionContainingText("June"); // выбор из ниспадающего списка, если в DOM есть select -> option
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
        $("#uploadPicture").uploadFromClasspath("picture.JPG"); // различные файлы, изображения принято хранить в специальной папке resources, в таком случае картинка будет подтягиваться по имени файла из папки resources, применимо для DOM type=file
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