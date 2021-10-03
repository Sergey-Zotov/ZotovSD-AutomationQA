package zotov_sd.automation_qa.test.db_test;

import org.testng.annotations.Test;
import zotov_sd.automation_qa.model.user.User;

public class UserTest {

    @Test
    public void userCreationTest() {
        User user = new User();

        User user2 = new User();
        user2.setPassword("qwriqwiqw");
        user2.setFirstName("Иван");
        user2.setLastName("Петров");
       // user2.setTokens(Collections.singletonList(new Token()));
       // user2.setEmails(Collections.singletonList(new Email()));

    }
}
