package zotov_sd.automation_qa.test.redmine_test_api_and_db;

import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import zotov_sd.automation_qa.allure.AllureAssert;
import zotov_sd.automation_qa.api.client.RestApiClient;
import zotov_sd.automation_qa.api.client.RestRequest;
import zotov_sd.automation_qa.api.client.RestResponse;
import zotov_sd.automation_qa.api.rest_assured.RestAssuredClient;
import zotov_sd.automation_qa.api.rest_assured.RestAssuredRequest;
import zotov_sd.automation_qa.model.user.Email;
import zotov_sd.automation_qa.model.user.Token;
import zotov_sd.automation_qa.model.user.User;

import java.util.Collections;

import static zotov_sd.automation_qa.api.client.RestMethod.DELETE;

public class DeleteUserWithoutAdministratorRights {

    private RestApiClient apiClient;
    private RestRequest request;
    private User user1;
    private User user2;

    @BeforeMethod(description = "Создание в БД пользователя 1 с доступом к API и ключом API и пользователя 2 без доступа к API и ключа API")
    public void prepareFixtures() {
        user1 = new User() {{
            setEmails(Collections.singletonList(new Email(this)));
            setTokens(Collections.singletonList(new Token(this)));
        }}.create();
        user2 = new User().create();
    }

    @Test(description = "Удаление пользователя 2 пользователем 1 без прав администратора")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Зотов С.Д.")
    public void deleteUser2Test() {
        RestResponse response = response(user2.getId());
        AllureAssert.assertEquals(response.getStatusCode(), 403,
                "статус код 403");
        AllureAssert.assertNotNull(new User().read(user2.getId()),
                "пользоватоль 2 в БД");
    }

    @Test(description = "Удаление пользователея 1 пользователеи 1 без прав администратора")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Зотов С.Д.")
    public void deleteUser1Test() {
        RestResponse response = response(user1.getId());
        AllureAssert.assertEquals(response.getStatusCode(), 403,
                "статус код 403");
        AllureAssert.assertNotNull(new User().read(user1.getId()),
                "пользователь 1 в БД");
    }

    @Step("Запрос DELETE пользователем 1")
    private RestResponse response(Integer userId) {
        apiClient = new RestAssuredClient(user1);
        request = new RestAssuredRequest(DELETE, "/users/" + userId + ".json", null, null, null);
        return apiClient.execute(request);
    }
}
