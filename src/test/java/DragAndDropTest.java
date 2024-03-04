import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.DragAndDropOptions.to;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

// HW5
public class DragAndDropTest extends TestBase {

    @Test
    void oneWayOfDragAndDropCheck() {
        // Запрограммируйте Drag&Drop с помощью Selenide.actions()
        // Откройте https://the-internet.herokuapp.com/drag_and_drop
        open("https://the-internet.herokuapp.com/drag_and_drop");
        // Перенесите прямоугольник А на место В
        actions().clickAndHold($("#column-a")).moveToElement($("#column-b")).release().perform();
        // или можно так: actions().dragAndDrop($("#column-a"), $("#column-b")).perform();
        // Проверьте, что прямоугольники действительно поменялись
        $("#column-a").shouldHave(text("B"));
    }

    @Test
    void anotherWayOfDragAndDropCheck() {
        // В Selenide есть команда $(element).dragAndDrop($(to-element)), проверьте работает ли тест, если использовать её вместо actions()
        open("https://the-internet.herokuapp.com/drag_and_drop");
        $(byText("A")).dragAndDrop(to($(byText("B"))));
        $("#column-a").shouldHave(text("B"));
    }

}