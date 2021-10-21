package zotov_sd.automation_qa.test.redmine_ui_test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import zotov_sd.automation_qa.model.user.User;
import zotov_sd.automation_qa.test.ui_test.BaseUITest;

public class AdministratorAuthorization extends BaseUITest {

    private User admin;

    @BeforeMethod
    public void prepareFixtures() {
        admin = new User() {{
            setIsAdmin(true);
        }}.create();

        openBrowser("/login");
    }

    @Test
    public void positiveAdminLoginTest() {
        loginPage.login(admin);
        Assert.assertEquals(headerPage.myAccount.getText(), "Моя учётная запись");
    }
}
