package zotov_sd.automation_qa.test.redmine_ui_test;

import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import zotov_sd.automation_qa.allure.AllureAssert;
import zotov_sd.automation_qa.lesson_test.ui_test.BaseUITest;
import zotov_sd.automation_qa.model.user.Status;
import zotov_sd.automation_qa.model.user.User;

import static zotov_sd.automation_qa.ui.browser.BrowserUtils.isElementDisplayed;


public class AuthorizationUnconfirmedUser extends BaseUITest {

    private User user;

    @BeforeMethod(description = "Заведен пользователь в системе. Пользователь не подтвержден администратором и не заблокирован. Открыт браузер.")
    public void prepareFixtures() {
        user = new User() {{
            setStatus(Status.UNACCEPTED);
        }}.create();

        openBrowser("/login");
    }

    @Test(description = "Авторизация неподтвержденным пользователем")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Зотов С.Д.")
    public void ConfirmedUserLoginTest() throws InterruptedException {
        loginPage.login(user);

        AllureAssert.assertTrue(isElementDisplayed(loginPage.errorFlash),
                "Отображается ошибка с текстом \"Ваша учётная запись создана и ожидает подтверждения администратора.\"");
        Assert.assertEquals(loginPage.errorFlash.getText(), "Ваша учётная запись создана и ожидает подтверждения администратора.");

        AllureAssert.assertFalse(isElementDisplayed(headerPage.myPage),
                "В заголовке страницы отображается элемент \"Моя страница\"");

        AllureAssert.assertTrue(isElementDisplayed(headerPage.logIn),
                "В заголовке страницы отображается элемент \"Войти\"");
        Assert.assertEquals(headerPage.logIn.getText(), "Войти");

        AllureAssert.assertTrue(isElementDisplayed(headerPage.registration),
                "В заголовке страницы отображается элемент \"Регистрация\"");
        Assert.assertEquals(headerPage.registration.getText(), "Регистрация");
    }
}
