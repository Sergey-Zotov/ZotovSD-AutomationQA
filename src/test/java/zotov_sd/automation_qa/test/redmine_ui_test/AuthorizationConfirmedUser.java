package zotov_sd.automation_qa.test.redmine_ui_test;

import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import zotov_sd.automation_qa.allure.AllureAssert;
import zotov_sd.automation_qa.lesson_test.ui_test.BaseUITest;
import zotov_sd.automation_qa.model.user.Email;
import zotov_sd.automation_qa.model.user.Token;
import zotov_sd.automation_qa.model.user.User;

import java.util.Collections;

import static zotov_sd.automation_qa.ui.browser.BrowserUtils.click;
import static zotov_sd.automation_qa.ui.browser.BrowserUtils.isElementDisplayed;

public class AuthorizationConfirmedUser extends BaseUITest {

    private User user;

    @BeforeMethod(description = " Заведен пользователь в системе. Пользователь подтвержден администратором и не заблокирован. Открыт браузер.")
    public void prepareFixtures() {
        user = new User() {{
            setEmails(Collections.singletonList(new Email(this)));
            setTokens(Collections.singletonList(new Token(this)));
        }}.create();

        openBrowser();
    }

    @Test(description = "Авторизация подтвержденным пользователем")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Зотов С.Д.")
    public void ConfirmedUserLoginTest() {
        click(headerPage.logIn, "Логин");
        loginPage.login(user);

        AllureAssert.assertTrue(isElementDisplayed(headerPage.myHomepage),
                "Отображается Домашняя страеица");
        Assert.assertEquals(headerPage.myHomepage.getText(), "Домашняя страница");

        AllureAssert.assertTrue(isElementDisplayed(headerPage.enteredAs),
                "Отображается элемент \"Вошли как " + user.getLogin() + "\"");
        Assert.assertEquals(headerPage.enteredAs.getText(), "Вошли как " + user.getLogin());

        AllureAssert.assertTrue(isElementDisplayed(headerPage.pageTitle),
                "В заголовке страницы отображаются элементы: \"Домашняя страница\", \"Моя страница\", \"Проекты\", \"Помощь\", \"Моя учётная запись\", \"Выйти\"");
        Assert.assertEquals(headerPage.pageTitle.getText(), "Моя учётная запись\n" +
                "Выйти\n" +
                "Вошли как " + user.getLogin() + "\n" +
                "Домашняя страница\n" +
                "Моя страница\n" +
                "Проекты\n" +
                "Помощь");

        AllureAssert.assertFalse(isElementDisplayed(headerPage.logIn),
                "Отображается элемент \"Войти\"");
        Assert.assertFalse(headerPage.pageTitle.getText().contains("Войти"));

        AllureAssert.assertFalse(isElementDisplayed(headerPage.registration),
                "Отображается элемент \"Регестрация\"");
        Assert.assertFalse(headerPage.pageTitle.getText().contains("Регистрация"));

        AllureAssert.assertFalse(isElementDisplayed(headerPage.administration),
                "Отображается элемент \"Администрирование\"");
        Assert.assertFalse(headerPage.pageTitle.getText().contains("Администрирование"));

        AllureAssert.assertTrue(isElementDisplayed(headerPage.search),
                "Отображается элемент \"Поиск\"");
    }
}
