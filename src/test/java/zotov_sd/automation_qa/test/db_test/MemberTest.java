package zotov_sd.automation_qa.test.db_test;

import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;
import zotov_sd.automation_qa.model.project.Project;
import zotov_sd.automation_qa.model.role.Role;
import zotov_sd.automation_qa.model.user.User;

import java.util.Arrays;
import java.util.List;

public class MemberTest {

    @Test(description = "Добавление пользователя с ролями в проект")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Зотов С.Д.")
    public void userAddProjectAndRoles() {
        Project project = new Project();
        project.setIsPublic(true);
        User user = new User();
        Role role1 = new Role();
        Role role2 = new Role();
        role1.create();
        role2.create();
        List<Role> roles = Arrays.asList(role1, role2);
        project.create();
        user.create();
        user.addProject(project, roles);
    }

    @Test(description = "Добавление в проект пользователя с ролями")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Зотов С.Д.")
    public void addUserWithRoles() {
        Project project = new Project();
        User user = new User();
        Role role1 = new Role();
        Role role2 = new Role();
        role1.create();
        role2.create();
        List<Role> roles = Arrays.asList(role1, role2);
        project.create();
        user.create();
        project.addUserWithRoles(user, roles);
    }
}
