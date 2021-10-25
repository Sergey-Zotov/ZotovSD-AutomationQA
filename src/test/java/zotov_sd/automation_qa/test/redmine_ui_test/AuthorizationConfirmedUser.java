package zotov_sd.automation_qa.test.redmine_ui_test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import zotov_sd.automation_qa.model.user.Email;
import zotov_sd.automation_qa.model.user.Token;
import zotov_sd.automation_qa.model.user.User;
import zotov_sd.automation_qa.test.ui_test.BaseUITest;

import java.util.Collections;

import static zotov_sd.automation_qa.ui.browser.BrowserUtils.isElementDisplayed;

public class AuthorizationConfirmedUser extends BaseUITest {

    private User user;

    @BeforeMethod
    public void prepareFixtures() {
        user = new User() {{
            setEmails(Collections.singletonList(new Email(this)));
            setTokens(Collections.singletonList(new Token(this)));
        }}.create();

        openBrowser();
    }

    @Test
    public void ConfirmedUserLoginTest() {
        headerPage.logIn.click();
        loginPage.login(user);
        Assert.assertTrue(headerPage.content.isDisplayed());
        Assert.assertEquals(headerPage.content.getText(), "Домашняя страница");
        Assert.assertTrue(headerPage.enteredAs.isDisplayed());
        Assert.assertEquals(headerPage.enteredAs.getText(), "Вошли как " + user.getLogin());
        Assert.assertTrue(headerPage.pageTitle.isDisplayed());
        Assert.assertEquals(headerPage.pageTitle.getText(), "Моя учётная запись\n" +
                "Выйти\n" +
                "Вошли как " + user.getLogin() + "\n" +
                "Домашняя страница\n" +
                "Моя страница\n" +
                "Проекты\n" +
                "Помощь");
        Assert.assertFalse(isElementDisplayed(headerPage.logIn));
        Assert.assertFalse(headerPage.pageTitle.getText().contains("Войти"));
        Assert.assertFalse(isElementDisplayed(headerPage.registration));
        Assert.assertFalse(headerPage.pageTitle.getText().contains("Регистрация"));
        Assert.assertFalse(isElementDisplayed(headerPage.administration));
        Assert.assertFalse(headerPage.pageTitle.getText().contains("Администрирование"));
        Assert.assertTrue(headerPage.search.isDisplayed());
    }
}
