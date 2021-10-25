package zotov_sd.automation_qa.test.redmine_ui_test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import zotov_sd.automation_qa.model.user.User;
import zotov_sd.automation_qa.test.ui_test.BaseUITest;

import static zotov_sd.automation_qa.ui.browser.BrowserUtils.isElementDisplayed;

public class AuthorizationAdministrator extends BaseUITest {

    private User admin;

    @BeforeMethod
    public void prepareFixtures() {
        admin = new User() {{
            setIsAdmin(true);
        }}.create();

        openBrowser();
    }

    @Test
    public void AdminLoginTest() {
        headerPage.logIn.click();
        loginPage.login(admin);
        Assert.assertTrue(headerPage.content.isDisplayed());
        Assert.assertEquals(headerPage.content.getText(), "Домашняя страница");
        Assert.assertTrue(headerPage.enteredAs.isDisplayed());
        Assert.assertEquals(headerPage.enteredAs.getText(), "Вошли как " + admin.getLogin());
        Assert.assertTrue(headerPage.pageTitle.isDisplayed());
        Assert.assertEquals(headerPage.pageTitle.getText(), "Моя учётная запись\n" +
                "Выйти\n" +
                "Вошли как " + admin.getLogin() + "\n" +
                "Домашняя страница\n" +
                "Моя страница\n" +
                "Проекты\n" +
                "Администрирование\n" +
                "Помощь");
        Assert.assertFalse(isElementDisplayed(headerPage.logIn));
        Assert.assertFalse(headerPage.pageTitle.getText().contains("Войти"));
        Assert.assertFalse(isElementDisplayed(headerPage.registration));
        Assert.assertFalse(headerPage.pageTitle.getText().contains("Регистрация"));
        Assert.assertTrue(headerPage.search.isDisplayed());
    }
}
