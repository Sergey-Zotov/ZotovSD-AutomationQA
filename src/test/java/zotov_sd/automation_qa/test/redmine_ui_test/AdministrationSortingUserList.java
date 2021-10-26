package zotov_sd.automation_qa.test.redmine_ui_test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import zotov_sd.automation_qa.model.user.User;
import zotov_sd.automation_qa.test.ui_test.BaseUITest;
import zotov_sd.automation_qa.ui.browser.BrowserUtils;
import zotov_sd.automation_qa.utils.CompareUtils;

import java.util.List;

public class AdministrationSortingUserList extends BaseUITest {

    private User admin;

    @BeforeMethod
    public void prepareFixtures() {
        admin = new User() {{
            setIsAdmin(true);
        }}.create();

        User user1 = new User().create();
        User user2 = new User().create();
        User user3 = new User().create();
        User user4 = new User().create();

        openBrowser();
    }

    @Test
    public void sortingUserListTest() {
        headerPage.logIn.click();
        loginPage.login(admin);

        Assert.assertTrue(headerPage.content.isDisplayed());
        Assert.assertEquals(headerPage.content.getText(), "Домашняя страница");

        headerPage.administration.click();
        Assert.assertTrue(administrationPage.content.isDisplayed());
        Assert.assertEquals(administrationPage.content.getText(), "Администрирование");

        administrationPage.users.click();
        Assert.assertTrue(userTablePage.content.isDisplayed());
        Assert.assertEquals(userTablePage.content.getText(), "Пользователи");

        List<String> creationUserByDesc = BrowserUtils.getElementsText(userTablePage.userNameList);
        CompareUtils.assertListSortedByUserDesc(creationUserByDesc);

        userTablePage.users.click();
        List<String> creationUserByAsc = BrowserUtils.getElementsText(userTablePage.userNameList);
        CompareUtils.assertListSortedByUserAsc(creationUserByAsc);
    }
}
