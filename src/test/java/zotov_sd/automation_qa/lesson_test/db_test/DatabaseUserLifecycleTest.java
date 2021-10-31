package zotov_sd.automation_qa.lesson_test.db_test;

import org.testng.annotations.Test;
import zotov_sd.automation_qa.model.user.Email;
import zotov_sd.automation_qa.model.user.Status;
import zotov_sd.automation_qa.model.user.Token;
import zotov_sd.automation_qa.model.user.User;

import java.util.Arrays;
import java.util.Random;

public class DatabaseUserLifecycleTest {

    @Test
    public void userLifecycleTest() {
        User user = new User()
                .setFirstName("НовоеИмя" + new Random().nextInt(100))
                .setLastName("НоваяФамилия" + new Random().nextInt(100))
                .setPassword("1qaz@WSX3edc");
        user.setTokens(
                Arrays.asList(
                        new Token(user),
                        new Token(user).setAction(Token.TokenType.SESSION),
                        new Token(user).setAction(Token.TokenType.SESSION)
                )
        ).setEmails(
                Arrays.asList(
                        new Email(user),
                        new Email(user).setIsDefault(false),
                        new Email(user).setIsDefault(false),
                        new Email(user).setIsDefault(false).setAddress("my_manual@mail.ru")
                )
        );
        user.create();
        user.setStatus(Status.UNACCEPTED);
        user.update();
        user.setStatus(Status.LOCKED);
        user.update();
        user.delete();
    }

}
