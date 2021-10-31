package zotov_sd.automation_qa.test.redmine_ui_test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import zotov_sd.automation_qa.lesson_test.ui_test.BaseUITest;
import zotov_sd.automation_qa.model.user.Status;
import zotov_sd.automation_qa.model.user.User;

import static zotov_sd.automation_qa.ui.browser.BrowserUtils.isElementDisplayed;


public class AuthorizationUnconfirmedUser extends BaseUITest {

    private User user;

    @BeforeMethod
    public void prepareFixtures() {
        user = new User() {{
            setStatus(Status.UNACCEPTED);
        }}.create();

        openBrowser("/login");
    }

    @Test
    public void ConfirmedUserLoginTest() throws InterruptedException {
        loginPage.login(user);

        Assert.assertTrue(loginPage.errorFlash.isDisplayed());
        Assert.assertEquals(loginPage.errorFlash.getText(), "Ваша учётная запись создана и ожидает подтверждения администратора.");

        Assert.assertFalse(isElementDisplayed(headerPage.myPage));

        Assert.assertTrue(headerPage.logIn.isDisplayed());
        Assert.assertEquals(headerPage.logIn.getText(), "Войти");

        Assert.assertTrue(headerPage.registration.isDisplayed());
        Assert.assertEquals(headerPage.registration.getText(), "Регистрация");
    }
}
