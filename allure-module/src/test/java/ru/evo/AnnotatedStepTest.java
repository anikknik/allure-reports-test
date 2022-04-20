package ru.evo;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

public class AnnotatedStepTest {

    private static final String REPOSITORY = "anikknik/params-tests";
    private static final int ISSUE_NUMBER = 2;

    @Test
    public void testGithubIssue() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        WebSteps steps = new WebSteps();

        steps.MainPage();
        steps.SearchForRepository(REPOSITORY);
        steps.ClickOnRepositoryLink(REPOSITORY);
        steps.OpenIssueTab();
        steps.ShouldSeeIssueWithNumber(ISSUE_NUMBER);
    }
}
