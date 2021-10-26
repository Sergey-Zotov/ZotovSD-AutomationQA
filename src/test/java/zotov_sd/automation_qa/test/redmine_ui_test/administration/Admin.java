package zotov_sd.automation_qa.test.redmine_ui_test.administration;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import zotov_sd.automation_qa.model.user.User;
import zotov_sd.automation_qa.test.ui_test.BaseUITest;
import zotov_sd.automation_qa.ui.browser.BrowserUtils;
import zotov_sd.automation_qa.utils.CompareUtils;

import java.util.List;

public class Admin extends BaseUITest {

    private User admin;

    protected void createAdminAndUsers() {
        admin = new User() {{
            setIsAdmin(true);
        }}.create();

        User user1 = new User().create();
        User user2 = new User().create();
        User user3 = new User().create();
        User user4 = new User().create();
    }

    protected void loginAdmin() {
        headerPage.logIn.click();
        loginPage.login(admin);
    }

    protected void assertListSortedByElementsDesc(List<WebElement> elements) {
        List<String> creationElementsByDesc = BrowserUtils.getElementsText(elements);
        CompareUtils.assertListSortedByElementsDesc(creationElementsByDesc);
    }

    protected void assertListSortedByElementsAsc(List<WebElement> elements) {
        List<String> creationElementsByAsc = BrowserUtils.getElementsText(elements);
        CompareUtils.assertListSortedByElementAsc(creationElementsByAsc);
    }

    protected void assertListNoSortedByElements(List<WebElement> elements) {
        List<String> creationElementsByAsc = BrowserUtils.getElementsText(elements);
        CompareUtils.assertListNoSortedByElement(creationElementsByAsc);
    }

    protected void assertHomepage() {
        Assert.assertTrue(headerPage.content.isDisplayed());
        Assert.assertEquals(headerPage.content.getText(), "Домашняя страница");
    }

    protected void assertAdministration() {
        Assert.assertTrue(administrationPage.content.isDisplayed());
        Assert.assertEquals(administrationPage.content.getText(), "Администрирование");
    }

    protected void assertUserTable() {
        Assert.assertTrue(userTablePage.content.isDisplayed());
        Assert.assertEquals(userTablePage.content.getText(), "Пользователи");
    }
}
