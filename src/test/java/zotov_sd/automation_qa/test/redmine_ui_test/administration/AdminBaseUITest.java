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

    @Step("Создание {0} пользователей в БД")
    protected void createUsers(Integer count) {
        for (int i = 0; i < count; i++) {
            new User().create();
        }
    }

    protected void loginAdmin() {
        click(headerPage.logIn, "Логин");
        loginPage.login(admin);
    }

    @Step("Таблица отсортирована по {1} по убыванию")
    protected void assertListSortedByElementsDesc(List<WebElement> elements, String description) {
        List<String> creationElementsByDesc = BrowserUtils.getElementsText(elements);
        CompareUtils.assertListSortedByElementsDesc(creationElementsByDesc);
    }

    @Step("Таблица отсортирована по {1} по возростанию")
    protected void assertListSortedByElementsAsc(List<WebElement> elements, String description) {
        List<String> creationElementsByAsc = BrowserUtils.getElementsText(elements);
        CompareUtils.assertListSortedByElementAsc(creationElementsByAsc);
    }

    @Step("Таблица не отсортирована по {1}")
    protected void assertListNoSortedByElements(List<WebElement> elements, String description) {
        List<String> creationElementsByAsc = BrowserUtils.getElementsText(elements);
        CompareUtils.assertListNoSortedByElement(creationElementsByAsc);
    }

    @Step("Отображается домашняя страница")
    protected void assertHomepageIsDisplayed() {
        Assert.assertTrue(isElementDisplayed(headerPage.myHomepage));
        Assert.assertEquals(headerPage.myHomepage.getText(), "Домашняя страница");
    }

    @Step("Отображается страница \"Администрирование\"")
    protected void assertAdministrationIsDisplayed() {
        Assert.assertTrue(isElementDisplayed(administrationPage.content));
        Assert.assertEquals(administrationPage.content.getText(), "Администрирование");
    }

    @Step("Отображается таблица с пользователями")
    protected void assertUserTableIsDisplayed() {
        Assert.assertTrue(isElementDisplayed(userTablePage.content));
        Assert.assertEquals(userTablePage.content.getText(), "Пользователи");
    }
}
