package zotov_sd.automation_qa.test.db_test;

import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;
import zotov_sd.automation_qa.model.role.Permissions;
import zotov_sd.automation_qa.model.role.Role;

import java.util.Arrays;
import java.util.List;

public class RoleTest {

    @Test(description = "Обновление роли")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Зотов С.Д.")
    public void roleUpdate() {
        Role role = new Role();
        role.setName("ZSD_update");
        role.setId(1717);
        role.update();
        role.delete();
    }

    @Test(description = "Создание роли")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Зотов С.Д.")
    public void roleTest() {
        Role role = new Role();
        List<Permissions> p = Arrays.asList(
                Permissions.EDIT_ISSUE_NOTES,
                Permissions.DELETE_MESSAGES,
                Permissions.ADD_ISSUE_NOTES,
                Permissions.ADD_DOCUMENTS
        );
        role.setPermissions(p);
        role.create();
    }
}
