import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class SoftAssertionsSearchTest {

    private String text = """
            @ExtendWith({SoftAssertsExtension.class})
            class Tests {
              @Test
              void test() {
                Configuration.assertionMode = SOFT;
                open("page.html");
            
                $("#first").should(visible).click();
                $("#second").should(visible).click();
              }
            """;

    @BeforeAll
    static void setup() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
    }


    @Test
    void findJUnit5Code() {
        open("https://github.com/selenide/selenide");
        $("#wiki-tab").click();
        $(".js-wiki-more-pages-link").click();
        $("a[href='/selenide/selenide/wiki/SoftAssertions']").shouldHave(text("Soft assertions")).click();
        $$(".markdown-heading").findBy(text("3. Using JUnit5 extend test class:")).sibling(0).$("pre")
                .shouldHave(text(text));
    }

}
