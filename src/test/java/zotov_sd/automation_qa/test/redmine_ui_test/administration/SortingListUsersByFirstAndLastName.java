package zotov_sd.automation_qa.test.redmine_ui_test.administration;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SortingListUsersByFirstAndLastName extends Admin {

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

        assertListNoSortedByElements(userTablePage.lastNameList);
        assertListNoSortedByElements(userTablePage.firstNameList);

        userTablePage.lastName.click();
        assertListSortedByElementsDesc(userTablePage.lastNameList);
        assertListNoSortedByElements(userTablePage.firstNameList);

        userTablePage.lastName.click();
        assertListSortedByElementsAsc(userTablePage.lastNameList);
        assertListNoSortedByElements(userTablePage.firstNameList);

        userTablePage.firstName.click();
        assertListNoSortedByElements(userTablePage.lastNameList);
        assertListSortedByElementsDesc(userTablePage.firstNameList);

        userTablePage.firstName.click();
        assertListNoSortedByElements(userTablePage.lastNameList);
        assertListSortedByElementsAsc(userTablePage.firstNameList);
    }
}
