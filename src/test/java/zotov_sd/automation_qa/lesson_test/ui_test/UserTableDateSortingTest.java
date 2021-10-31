package zotov_sd.automation_qa.lesson_test.ui_test;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import zotov_sd.automation_qa.model.user.User;

import java.util.List;

import static zotov_sd.automation_qa.ui.browser.BrowserUtils.getElementsText;
import static zotov_sd.automation_qa.utils.CompareUtils.assertListSortedByDateAsc;
import static zotov_sd.automation_qa.utils.CompareUtils.assertListSortedByDateDesc;


public class UserTableDateSortingTest extends BaseUITest {

    @BeforeMethod
    public void prepareFixtures() {
        User admin = new User() {{
            setIsAdmin(true);
        }}.create();

        openBrowser("/login");
        loginPage.login(admin);
        headerPage.administration.click();
        administrationPage.users.click();
    }

    @Test
    public void testUsersTableDateSorting() {
        userTablePage.button("Создано").click();
        List<String> creationDatesByDesc = getElementsText(userTablePage.creationDates);
        assertListSortedByDateDesc(creationDatesByDesc);

        userTablePage.button("Создано").click();
        List<String> creationDatesByAsc = getElementsText(userTablePage.creationDates);
        assertListSortedByDateAsc(creationDatesByAsc);
    }

}
