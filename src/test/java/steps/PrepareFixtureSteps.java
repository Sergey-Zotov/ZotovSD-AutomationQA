package steps;

import cucumber.api.java.ru.Дано;
import cucumber.api.java.ru.И;
import cucumber.api.java.ru.Пусть;
import io.cucumber.datatable.DataTable;
import zotov_sd.automation_qa.allure.AllureAssert;
import zotov_sd.automation_qa.context.Context;
import zotov_sd.automation_qa.cucumber.validators.ProjectParametersValidator;
import zotov_sd.automation_qa.cucumber.validators.UserParametersValidator;
import zotov_sd.automation_qa.db.requests.model_request.MemberRequest;
import zotov_sd.automation_qa.db.requests.model_request.MemberRoleRequest;
import zotov_sd.automation_qa.model.project.Project;
import zotov_sd.automation_qa.model.role.Permissions;
import zotov_sd.automation_qa.model.role.Role;
import zotov_sd.automation_qa.model.user.*;
import zotov_sd.automation_qa.model.project.StatusProject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class PrepareFixtureSteps {

    @Пусть("В системе есть роль \"(.+)\" с параметрами:")
    public void createRoles(String rolesStashId, DataTable dataTable) {
        List<String> list = dataTable.asList();
        List<Permissions> permissions = new ArrayList<>();
        list.forEach(per -> {
            Permissions permission = Permissions.of(per);
            permissions.add(permission);
        });
        Role role = new Role() {{
            setPermissions(getPermissions());
        }}.create();
        Context.getStash().put(rolesStashId, Collections.singletonList(role));
    }

    @И("Имеется список E-Mail адресов \"(.+)\":")
    public void createEmails(String emailsStashId, DataTable dataTable) {
        // TODO: EmailValidator

        List<Map<String, String>> maps = dataTable.asMaps();
        List<Email> emails = new ArrayList<>();

        maps.forEach(map -> {
            String address = map.get("Адрес");
            Boolean isDefault = Boolean.parseBoolean(map.get("По умолчанию"));
            Boolean notify = Boolean.parseBoolean(map.get("Уведомления"));
            Email email = new Email()
                    .setAddress(address)
                    .setIsDefault(isDefault)
                    .setNotify(notify);
            emails.add(email);
        });

        Context.getStash().put(emailsStashId, emails);
    }

    @Дано("В системе есть пользователь \"(.+)\" с параметрами:")
    public void createAdminUser(String userStashId, Map<String, String> parameters) {
        UserParametersValidator.validateUserParameters(parameters.keySet());
        User user = new User();
        List<Permissions> permissions = new ArrayList<>();
        if (parameters.containsKey("Администратор")) {
            Boolean isAdmin = Boolean.parseBoolean(parameters.get("Администратор"));
            user.setIsAdmin(isAdmin);
        }
        if (parameters.containsKey("Статус")) {
            String statusDescription = parameters.get("Статус");
            Status status = Status.of(statusDescription);
            user.setStatus(status);
        }
        if (parameters.containsKey("Уведомления о новых событиях")) {
            String mailNotificationDescription = parameters.get("Уведомления о новых событиях");
            MailNotification mn = MailNotification.of(mailNotificationDescription);
            user.setMailNotification(mn);
        }
        if (parameters.containsKey("E-Mail")) {
            String emailsStashId = parameters.get("E-Mail");
            List<Email> emails = Context.getStash().get(emailsStashId, List.class);
            user.setEmails(emails);
        }

        user.setTokens(Collections.singletonList(new Token(user)));
        user.create();

        if (parameters.containsKey("Проект")) {
            String projectStashId = parameters.get("Проект");
            Project project = Context.getStash().get(projectStashId, Project.class);
            MemberRequest.create(project, user);
        }
        if (parameters.containsKey("Роль")) {
            String rolesStashId = parameters.get("Роль");
            List<Role> roles = Context.getStash().get(rolesStashId, List.class);
            roles.forEach(role -> MemberRoleRequest.create(role, user));

        }

        Context.getStash().put(userStashId, user);
    }

    @Пусть("В системе есть проект \"(.+)\" с параметрами:")
    public void createProject(String projectStashId, Map<String, String> parameters) {
        ProjectParametersValidator.validateProjectParameters(parameters.keySet());
        Project project = new Project();
        if (parameters.containsKey("Статус")) {
            String statusDescription = parameters.get("Статус");
            Boolean isPublic = statusDescription.equals("Публичный");
            project.setIsPublic(isPublic);
        }
        project.create();
        Context.getStash().put(projectStashId, project);
    }

    @Пусть("В системе есть {int} пользователей")
    public void createUsers(int count) {
        for (int i = 0; i < count; i++) {
            new User().create();
        }
    }

    @Пусть("Есть пользователь \"(.+)\"")
    public void testUser(String testUserStashId) {
        User user = new User(){{
            setId(1);
        }};
        user.setEmails(Collections.singletonList(new Email(user)));
        Context.getStash().put(testUserStashId, user);
    }

    @И("В базе данных в талице пользователи отображается \"(.+)\"")
    public void checkingCreatedUser(String userStashId) {
        User newUser = Context.getStash().get(userStashId, User.class);
        Integer id = newUser.readUserIdFromTheDatabaseByLogin();
        User copyUserDB = new User().read(id);
        AllureAssert.assertEquals(newUser.getLogin(), copyUserDB.getLogin(), "Пользователя");
        AllureAssert.assertEquals(newUser.getLastName(), copyUserDB.getLastName(), "Имени");
        AllureAssert.assertEquals(newUser.getFirstName(), copyUserDB.getFirstName(), "Фамилии");
        AllureAssert.assertEquals(newUser.getEmails().get(0).getAddress(), copyUserDB.getEmails().get(0).getAddress(), "Email");
    }
}
