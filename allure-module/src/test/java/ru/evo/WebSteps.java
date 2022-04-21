package ru.evo;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

public class WebSteps {

    @Step("Открываем главную страницу")
    public void mainPage() {
        open("https://github.com");
    }

    @Step("Ищем репозиторий {repo}")
    public void searchForRepository(String repo) {
        $(".header-search-input").click();
        $(".header-search-input").setValue(repo);
        $(".header-search-input").submit();
    }

    @Step("Переходим по ссылке репозитория {repo}")
    public void clickOnRepositoryLink(String repo) {
        $(linkText(repo)).click();
        attachScreenshot();
    }

    @Step("Кликаем вкладку Issue")
    public void openIssueTab() {
        $(partialLinkText("Issue")).click();
    }

    @Step("Проверяем наличие Issue с номером {numb}")
    public void shouldSeeIssueWithNumber(int numb) {
        $(withText("#" + numb)).should(Condition.visible);
    }

    @Attachment(value = "Тест скрин страницы", type = "image/png", fileExtension = "png")
    public byte[] attachScreenshot () {
        return ((TakesScreenshot)WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
