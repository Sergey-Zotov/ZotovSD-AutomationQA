package zotov_sd.automation_qa.test.redmine_ui_test.administration;

import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static zotov_sd.automation_qa.ui.browser.BrowserUtils.click;

public class SortingListUsersByFirstAndLastName extends AdminBaseUITest {

    @BeforeMethod(description = "1. Заведен пользователь в системе с правами администратора " +
            "2. Заведено несколько пользователей в системе 3. Открыт браузер.")
    public void prepareFixtures() {
        createAdmin();
        createUsers();
        openBrowser();
    }

    @Test(description = "Администрирование. Сортировка списка пользователей по имени и фамилии")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Зотов С.Д.")
    public void sortingUserListTest() {
        loginAdmin();
        assertHomepage();

        click(headerPage.administration, "Администрирование");
        assertAdministration();

        click(administrationPage.users, "Пользователи");
        assertUserTable();

        assertListNoSortedByElements(userTablePage.lastNameList, "Фамилии");
        assertListNoSortedByElements(userTablePage.firstNameList, "Имени");

        click(userTablePage.lastName, "Фамилия");
        assertListSortedByElementsDesc(userTablePage.lastNameList, "Фамилии");
        assertListNoSortedByElements(userTablePage.firstNameList, "Имени");

        click(userTablePage.lastName, "Фамилия");
        assertListSortedByElementsAsc(userTablePage.lastNameList, "Фамилии");
        assertListNoSortedByElements(userTablePage.firstNameList, "Имени");

        click(userTablePage.firstName, "Имени");
        assertListNoSortedByElements(userTablePage.lastNameList, "Фамилии");
        assertListSortedByElementsDesc(userTablePage.firstNameList, "Имени");

        click(userTablePage.firstName, "Имени");
        assertListNoSortedByElements(userTablePage.lastNameList, "Фамилии");
        assertListSortedByElementsAsc(userTablePage.firstNameList, "Имени");
    }
}
