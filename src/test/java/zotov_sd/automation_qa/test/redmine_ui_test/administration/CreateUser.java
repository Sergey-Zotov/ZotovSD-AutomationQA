package zotov_sd.automation_qa.test.redmine_ui_test.administration;

import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import zotov_sd.automation_qa.allure.AllureAssert;
import zotov_sd.automation_qa.model.user.Email;
import zotov_sd.automation_qa.model.user.User;
import zotov_sd.automation_qa.ui.browser.BrowserUtils;

import java.util.Collections;

import static zotov_sd.automation_qa.ui.browser.BrowserUtils.click;
import static zotov_sd.automation_qa.ui.browser.BrowserUtils.isElementDisplayed;

public class CreateUser extends AdminBaseUITest {

    private User newUser;

    @BeforeMethod(description = "Заведен пользователь в системе с правами администратора. Открыт браузер")
    public void prepareFixtures() {
        createAdmin();
        openBrowser();
        newUser = new User() {{
            setId(1);
        }};
        newUser.setEmails(Collections.singletonList(new Email(newUser)));
    }

    @Test(description = "Администрирование. Создание пользователя.")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Зотов С.Д.")
    public void createUserTest() {
        loginAdmin();
        assertHomepageIsDisplayed();
        click(headerPage.administration, "Админестрирование");
        assertAdministrationIsDisplayed();
        click(administrationPage.users, "Пользователи");
        click(userTablePage.newUser, "Новый пользавотель");
        createUser();
        checkingCreatedUser();
    }

    @Step("Заполняю обязательные поля и создаю нового пользователя")
    private void createUser() {
        BrowserUtils.sendKeys(createUserPage.userLogin, newUser.getLogin(), "Пользователь");
        BrowserUtils.sendKeys(createUserPage.userLastName, newUser.getLastName(), "Имя");
        BrowserUtils.sendKeys(createUserPage.userFirstName, newUser.getFirstName(), "Фамилия");
        BrowserUtils.sendKeys(createUserPage.userMail, newUser.getEmails().get(0).getAddress(), "Email");
        click(createUserPage.userGeneratePassword, "чекбокс \"Создание пароля\"");
        click(createUserPage.createUser, "кнопку Создать");
        AllureAssert.assertTrue(isElementDisplayed(createUserPage.userCrated), "тображается сообщение Пользователь " + newUser.getLogin() + " создан.");
        Assert.assertEquals(createUserPage.userCrated.getText(), "Пользователь " + newUser.getLogin() + " создан.");
    }

    @Step("Проверка созданного пользователя в БД")
    private void checkingCreatedUser() {
        Integer id = newUser.readUserIdFromTheDatabaseByLogin();
        User copyUserDB = new User().read(id);
        AllureAssert.assertEquals(newUser.getLogin(), copyUserDB.getLogin(), "Пользователя");
        AllureAssert.assertEquals(newUser.getLastName(), copyUserDB.getLastName(), "Имени");
        AllureAssert.assertEquals(newUser.getFirstName(), copyUserDB.getFirstName(), "Фамилии");
        AllureAssert.assertEquals(newUser.getEmails().get(0).getAddress(), copyUserDB.getEmails().get(0).getAddress(), "Email");
    }
}
