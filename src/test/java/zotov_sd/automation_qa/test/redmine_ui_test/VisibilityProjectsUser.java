package zotov_sd.automation_qa.test.redmine_ui_test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import zotov_sd.automation_qa.model.project.Project;
import zotov_sd.automation_qa.model.role.Permissions;
import zotov_sd.automation_qa.model.role.Role;
import zotov_sd.automation_qa.model.user.User;
import zotov_sd.automation_qa.test.ui_test.BaseUITest;
import zotov_sd.automation_qa.ui.browser.BrowserUtils;

import java.util.Collections;

public class VisibilityProjectsUser extends BaseUITest {

    private User user;
    private Project project1;
    private Project project2;
    private Project project3;

    @BeforeMethod
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

    @Test
    public void VisibilityProjectTest() {
        headerPage.logIn.click();
        loginPage.login(user);

        Assert.assertTrue(headerPage.content.isDisplayed());
        Assert.assertEquals(headerPage.content.getText(), "Домашняя страница");

        headerPage.projects.click();
        Assert.assertTrue(BrowserUtils.isProjectDisplayed(project1.getId()));
        Assert.assertFalse(BrowserUtils.isProjectDisplayed(project2.getId()));
        Assert.assertTrue(BrowserUtils.isProjectDisplayed(project3.getId()));
    }
}
