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
import zotov_sd.automation_qa.model.role.Permissions;
import zotov_sd.automation_qa.model.role.Role;
import zotov_sd.automation_qa.model.user.User;

import java.util.Collections;

import static zotov_sd.automation_qa.ui.browser.BrowserUtils.*;

public class VisibilityProjectsUser extends BaseUITest {

    private User user;
    private Project project1;
    private Project project2;
    private Project project3;

    @BeforeMethod(description = "1. Заведен пользователь в системе.\n" +
            "2. Пользователь подтвержден администратором и не заблокирован\n" +
            "3. В системе заведена Роль пользователя с правами на просмотр задач\n" +
            "4. В системе заведен публичный проект (№ 1)\n" +
            "5. В системе заведен приватный проект (№ 2)\n" +
            "6. В системе заведен приватный проект (№ 3)\n" +
            "7. У пользователя нет доступа к проектам №1, №2\n" +
            "8. У пользователя есть доступ к проекту №3 c ролью из п.3 предусловия\n" +
            "9. Открыт браузер.")
    public void prepareFixtures() {

        project1 = new Project() {{
            setIsPublic(true);
        }}.create();

        project2 = new Project() {{
            setIsPublic(false);
        }}.create();

        project3 = new Project() {{
            setIsPublic(false);
        }}.create();

        Role role = new Role() {{
            setPermissions(Collections.singletonList(Permissions.VIEW_ISSUES));
        }}.create();

        user = new User().create();
        user.addProject(project3, Collections.singletonList(role));

        openBrowser();
    }

    @Test(description = "Видимость проектов. Пользователь")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Зотов С.Д.")
    public void VisibilityProjectTest() {
        click(headerPage.logIn, "Логин");
        loginPage.login(user);

        AllureAssert.assertTrue(isElementDisplayed(headerPage.content),
                "Отображается домашняя страница");
        Assert.assertEquals(headerPage.content.getText(), "Домашняя страница");

        click(headerPage.projects, "Проекты");
        AllureAssert.assertTrue(isElementDisplayed(projectTablePage.projectsSelected),
                "Отображается страница Проекты");
        Assert.assertEquals(projectTablePage.projectsSelected.getText(), "Проекты");

        AllureAssert.assertTrue(isProjectDisplayed(project1.getId()),
                "Отображается проект из п.4 предусловия");
        AllureAssert.assertFalse(isProjectDisplayed(project2.getId()),
                "Отображается проект из п.5 предусловия");
        AllureAssert.assertTrue(isProjectDisplayed(project3.getId()),
                "Отображается проект из п.6 предусловия");
    }
}
