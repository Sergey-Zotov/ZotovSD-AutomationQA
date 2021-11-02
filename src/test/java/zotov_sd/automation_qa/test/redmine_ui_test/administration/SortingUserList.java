package zotov_sd.automation_qa.test.redmine_ui_test.administration;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SortingUserList extends AdminBaseUITest {

    @BeforeMethod
        public void prepareFixtures() {
            createAdmin();
            createUsers();
            openBrowser();
        }

        @Test
        public void sortingUserListTest() {
            loginAdmin();
            assertHomepage();

            headerPage.administration.click();
            assertAdministration();

            administrationPage.users.click();
            assertUserTable();

        assertListSortedByElementsDesc(userTablePage.userNameList);

        userTablePage.users.click();
        assertListSortedByElementsAsc(userTablePage.userNameList);
    }
}
