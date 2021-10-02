package zotov_sd.automation_qa.test;

import org.testng.annotations.Test;
import zotov_sd.automation_qa.model.role.Permissions;
import zotov_sd.automation_qa.model.role.Role;

import java.util.Arrays;
import java.util.List;

public class RoleTest {

    @Test
    public void roleUpdate() {
        Role role = new Role();
        role.setName("ZSD_update");
        role.setId(1717);
        role.update();
        role.delete();
    }

    @Test
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
