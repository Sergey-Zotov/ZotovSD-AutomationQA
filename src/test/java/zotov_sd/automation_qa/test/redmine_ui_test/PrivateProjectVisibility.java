package zotov_sd.automation_qa.test.redmine_ui_test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import zotov_sd.automation_qa.model.project.Project;
import zotov_sd.automation_qa.model.project.Status;
import zotov_sd.automation_qa.model.user.User;
import zotov_sd.automation_qa.test.ui_test.BaseUITest;
import zotov_sd.automation_qa.ui.browser.BrowserUtils;

public class PrivateProjectVisibility extends BaseUITest {

    private User admin;
    private Project project;

    @BeforeMethod
    public void prepareFixtures() {
        admin = new User() {{
            setIsAdmin(true);
        }}.create();

        project = new Project() {{
            setStatus(Status.CLOSED);
        }}.create();

        openBrowser();
    }

    @Test
    public void SearchProjectAdministratorTest() {
        headerPage.logIn.click();
        loginPage.login(admin);
        Assert.assertTrue(headerPage.content.isDisplayed());
        Assert.assertEquals(headerPage.content.getText(), "Домашняя страница");
        headerPage.projects.click();
        Assert.assertTrue(projectTablePage.projectsSelected.isDisplayed());
        Assert.assertEquals(projectTablePage.projectsSelected.getText(), "Проекты");
        projectTablePage.projectStatus.click();
        projectTablePage.statusClosed.click();
        projectTablePage.applying.click();
        Assert.assertTrue(BrowserUtils.isProjectDisplayed(project.getId()));
    }
}
