package zotov_sd.automation_qa.test.redmine_ui_test.administration;

import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import zotov_sd.automation_qa.allure.AllureAssert;

import static zotov_sd.automation_qa.ui.browser.BrowserUtils.isElementDisplayed;

public class AuthorizationAdministrator extends AdminBaseUITest {


    @BeforeMethod(description = "Создание пользователя с правами Админестратора, открытие браузера")
    public void prepareFixtures() {
        createAdmin();
        openBrowser();
    }

    @Test(description = "Авторизация администратором")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Зотов С.Д.")
    public void AdminLoginTest() {
        loginAdmin();

        assertHomepage();

        Assert.assertTrue(isElementDisplayed(headerPage.enteredAs));
        AllureAssert.assertEquals(headerPage.enteredAs.getText(), "Вошли как " + admin.getLogin(),
                "Вошли как " + admin.getLogin());

        AllureAssert.assertTrue(isElementDisplayed(headerPage.pageTitle),
                "В заголовке страницы отображаются элементы: \"Домашняя страница\", \"Моя страница\", \"Проекты\", \"Администрирование\", \"Помощь\", \"Моя учётная запись\", \"Выйти\"");
        Assert.assertEquals(headerPage.pageTitle.getText(), "Моя учётная запись\n" +
                "Выйти\n" +
                "Вошли как " + admin.getLogin() + "\n" +
                "Домашняя страница\n" +
                "Моя страница\n" +
                "Проекты\n" +
                "Администрирование\n" +
                "Помощь");

        AllureAssert.assertFalse(isElementDisplayed(headerPage.logIn),
                "В заголовке отображается элемент \"Войти\"");
        Assert.assertFalse(headerPage.pageTitle.getText().contains("Войти"));

        AllureAssert.assertFalse(isElementDisplayed(headerPage.registration),
                "В заголовке отображается элемент \"Регестрация\"");
        Assert.assertFalse(headerPage.pageTitle.getText().contains("Регистрация"));

        AllureAssert.assertTrue(isElementDisplayed(headerPage.search),
                " Отображается элемент \"Поиск\"");
    }
}
