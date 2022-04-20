package ru.evo;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

@Owner("anikknik") // ник владельца
@Severity(SeverityLevel.BLOCKER) // Важность в зависимости от влияния
// Разметка для наглядности какая функциональность сломалась
@Feature("Раздел учёта ошибок в репо") // Область доработки. Что именно дорабатывалось
@Story("Возможность вести учёт ошибок в репо") // User Story. Исчерпывающее описание
public class LambdaStepTest {

    private static final String REPOSITORY = "anikknik/params-tests";
    private static final int ISSUE_NUMBER = 2;

    @Test
    @DisplayName("Тест фич Allure-report на примере репо")
    public void testGithubIssue() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу", () -> open("https://github.com"));

        step("Ищем репозиторий " + REPOSITORY, () -> {
            $(".header-search-input").click();
            $(".header-search-input").setValue(REPOSITORY);
            $(".header-search-input").submit();
        });

        step("Переходим по ссылке репозитория", () -> {
            $(linkText(REPOSITORY)).click();
        });

        step("Кликаем вкладку Issue", () -> {
            $(partialLinkText("Issue")).click();
        });

        step("Проверяем наличие Issue с номером " + ISSUE_NUMBER, () -> {
            $(withText("#2")).should(Condition.visible);
            Allure.getLifecycle().addAttachment("Слепок страницы",
                    "text/html",
                    "html",
                    WebDriverRunner.getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8));
        });

    }

}
