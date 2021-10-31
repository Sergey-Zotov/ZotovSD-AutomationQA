package zotov_sd.automation_qa.test.db_test;

import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;
import zotov_sd.automation_qa.model.user.User;

public class UserTest {

    @Test(description = "Создание пользователя в БД")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Зотов С.Д.")
    public void userCreationTest() {
        User user = new User();
        user.setPassword("qwriqwiqw");
        user.setFirstName("Иван");
        user.setLastName("Петров");
        user.create();
    }

    @Test(description = "Получение пользователя из БД")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Зотов С.Д.")
    public void userReadTest() {
        User user = new User().read(1);
    }
}
