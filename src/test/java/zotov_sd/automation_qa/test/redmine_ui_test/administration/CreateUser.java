package zotov_sd.automation_qa.test.redmine_ui_test.administration;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import zotov_sd.automation_qa.db.connection.PostgresConnection;
import zotov_sd.automation_qa.model.user.Email;
import zotov_sd.automation_qa.model.user.User;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CreateUser extends Admin {

    private User newUser;

    @BeforeMethod
    public void prepareFixtures() {
        createAdmin();
        openBrowser();
        newUser = new User() {{
            setId(1);
        }};
        newUser.setEmails(Collections.singletonList(new Email(newUser)));
    }

    @Test
    public void createUserTest() {
        loginAdmin();
        assertHomepage();

        headerPage.administration.click();
        assertAdministration();
        administrationPage.users.click();
        userTablePage.newUser.click();

        createUser();

        Integer id = readUserIdDB(newUser.getLogin());
        User copyUserDB = new User().read(id);
        Assert.assertEquals(newUser.getLogin(), copyUserDB.getLogin());
        Assert.assertEquals(newUser.getLastName(), copyUserDB.getLastName());
        Assert.assertEquals(newUser.getFirstName(), copyUserDB.getFirstName());
        Assert.assertEquals(newUser.getEmails().get(0).getAddress(), copyUserDB.getEmails().get(0).getAddress());
    }

    private void createUser() {
        createUserPage.userLogin.sendKeys(newUser.getLogin());
        createUserPage.userLastName.sendKeys(newUser.getLastName());
        createUserPage.userFirstName.sendKeys(newUser.getFirstName());
        createUserPage.userMail.sendKeys(newUser.getEmails().get(0).getAddress());
        createUserPage.userGeneratePassword.click();
        createUserPage.createUser.click();
        Assert.assertEquals(createUserPage.userCrated.getText(), "Пользователь " + newUser.getLogin() + " создан.");
    }

    private Integer readUserIdDB(String login) {
        try {
            String query = "SELECT id\n" +
                    "FROM public.users\n" +
                    "WHERE login=?;\n";
            List<Map<String, Object>> result = PostgresConnection.INSTANCE.executeQuery(query, login);
            User user = from(result.get(0));
            return user.getId();
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalStateException("Пользователь " + login + " в базе данных не найден.");
        }
    }

    private User from(Map<String, Object> data) {
        return (User) new User().setId((Integer) data.get("id"));
    }
}
