package zotov_sd.automation_qa.test.redmine_ui_test;

import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import zotov_sd.automation_qa.allure.AllureAssert;
import zotov_sd.automation_qa.lesson_test.ui_test.BaseUITest;
import zotov_sd.automation_qa.model.project.Project;
import zotov_sd.automation_qa.model.project.StatusProject;
import zotov_sd.automation_qa.model.user.User;

import static zotov_sd.automation_qa.ui.browser.BrowserUtils.*;

public class PrivateProjectVisibility extends BaseUITest {

    private User admin;
    private Project project;

    @BeforeMethod(description = "Заведен пользователь в системе с правами администратора. Существует приватный проект, не привязанный к пользователю. Открыт браузер.")
    public void prepareFixtures() {
        admin = new User() {{
            setIsAdmin(true);
        }}.create();

        project = new Project() {{
            setStatus(StatusProject.CLOSED);
        }}.create();

        openBrowser();
    }

    @Test(description = "Видимость проекта. Приватный проект. Администратор")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Зотов С.Д.")
    public void SearchProjectAdministratorTest() {
        click(headerPage.logIn, "Логин");
        loginPage.login(admin);

        AllureAssert.assertTrue(isElementDisplayed(headerPage.myHomepage),
                "Отображается домашняя страница");
        Assert.assertEquals(headerPage.myHomepage.getText(), "Домашняя страница");

        click(headerPage.projects, "Проэкты");
        AllureAssert.assertTrue(isElementDisplayed(projectTablePage.projectsSelected),
                " Отображается страница \"Проекты\"");
        Assert.assertEquals(projectTablePage.projectsSelected.getText(), "Проекты");

        click(projectTablePage.projectStatus, "Статус проектов");
        click(projectTablePage.statusClosed, "Закрытые");
        click(projectTablePage.applying, "Применить");
        AllureAssert.assertTrue(isProjectDisplayed(project.getId()),
                "Отображается проект из предусловия");
    }
}
