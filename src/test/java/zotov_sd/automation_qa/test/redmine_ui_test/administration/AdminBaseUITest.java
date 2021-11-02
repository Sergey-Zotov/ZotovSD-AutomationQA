package zotov_sd.automation_qa.test.redmine_ui_test.administration;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import zotov_sd.automation_qa.lesson_test.ui_test.BaseUITest;
import zotov_sd.automation_qa.model.user.User;
import zotov_sd.automation_qa.ui.browser.BrowserUtils;
import zotov_sd.automation_qa.utils.CompareUtils;

import java.util.List;

import static zotov_sd.automation_qa.ui.browser.BrowserUtils.click;
import static zotov_sd.automation_qa.ui.browser.BrowserUtils.isElementDisplayed;

public class AdminBaseUITest extends BaseUITest {

    protected User admin;

    @Step("Создание пользователя с правами Администратора в БД")
    protected void createAdmin() {
        admin = new User() {{
            setIsAdmin(true);
        }}.create();
    }

    @Step("Создание 4 пользователей в БД")
    protected void createUsers() {
        User user1 = new User().create();
        User user2 = new User().create();
        User user3 = new User().create();
        User user4 = new User().create();
    }

    protected void loginAdmin() {
        click(headerPage.logIn, "Логин");
        loginPage.login(admin);
    }

    @Step("Проверка на отсортированность списка по убыванию")
    protected void assertListSortedByElementsDesc(List<WebElement> elements) {
        List<String> creationElementsByDesc = BrowserUtils.getElementsText(elements);
        CompareUtils.assertListSortedByElementsDesc(creationElementsByDesc);
    }

    @Step("Проверка на отсортированность списка по возростанию")
    protected void assertListSortedByElementsAsc(List<WebElement> elements) {
        List<String> creationElementsByAsc = BrowserUtils.getElementsText(elements);
        CompareUtils.assertListSortedByElementAsc(creationElementsByAsc);
    }

    @Step("Проверка не отсортированности списка")
    protected void assertListNoSortedByElements(List<WebElement> elements) {
        List<String> creationElementsByAsc = BrowserUtils.getElementsText(elements);
        CompareUtils.assertListNoSortedByElement(creationElementsByAsc);
    }

    @Step("Проверка отоброжения \"Домашняя страница\"")
    protected void assertHomepage() {
        Assert.assertTrue(isElementDisplayed(headerPage.content));
        Assert.assertEquals(headerPage.content.getText(), "Домашняя страница");
    }

    @Step("Проверка наличия кнопки \"Администрирование\"")
    protected void assertAdministration() {
        Assert.assertTrue(isElementDisplayed(administrationPage.content));
        Assert.assertEquals(administrationPage.content.getText(), "Администрирование");
    }

    @Step("Проверка наличия кнопки \"Пользователи\"")
    protected void assertUserTable() {
        Assert.assertTrue(isElementDisplayed(userTablePage.content));
        Assert.assertEquals(userTablePage.content.getText(), "Пользователи");
    }
}
