package zotov_sd.automation_qa.test.redmine_ui_test.administration;

import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static zotov_sd.automation_qa.ui.browser.BrowserUtils.click;

public class SortingUserList extends AdminBaseUITest {

    @BeforeMethod(description = "1. Заведен пользователь в системе с правами администратора\n" +
            "2. Заведено несколько пользователей в системе 3. Открыт браузер.")
    public void prepareFixtures() {
        createAdmin();
        createUsers(5);
        openBrowser();
    }

    @Test(description = "Администрирование. Сортировка списка пользователей по пользователю")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Зотов С.Д.")
    public void sortingUserListTest() {
        loginAdmin();
        assertHomepageIsDisplayed();

        click(headerPage.administration, "Администрирование");
        assertAdministrationIsDisplayed();

        click(administrationPage.users, "Пользователи");
        assertUserTableIsDisplayed();

        assertListSortedByElementsDesc(userTablePage.userNameList, "логину пользователей");

        click(userTablePage.users, "Пользователь");
        assertListSortedByElementsAsc(userTablePage.userNameList, "логину пользователей");
    }
}
