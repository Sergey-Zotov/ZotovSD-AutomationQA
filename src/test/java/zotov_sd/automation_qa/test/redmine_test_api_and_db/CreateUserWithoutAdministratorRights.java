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
import zotov_sd.automation_qa.api.dto.users.UserDto;
import zotov_sd.automation_qa.api.dto.users.UserInfoDto;
import zotov_sd.automation_qa.api.rest_assured.RestAssuredClient;
import zotov_sd.automation_qa.api.rest_assured.RestAssuredRequest;
import zotov_sd.automation_qa.model.user.Email;
import zotov_sd.automation_qa.model.user.Token;
import zotov_sd.automation_qa.model.user.User;

import java.util.Arrays;
import java.util.Collections;

import static zotov_sd.automation_qa.api.client.RestMethod.POST;
import static zotov_sd.automation_qa.api.rest_assured.GsonProvider.GSON;

public class CreateUserWithoutAdministratorRights {

    private RestApiClient apiClient;
    private RestRequest request;
    private User user;

    @BeforeMethod(description = "Создание пользователя без прав Администратора, с доступом к API и ключ API")
    public void prepareFixtures() {
        user = new User() {{
            setEmails(Arrays.asList(new Email(this), new Email(this)));
            setTokens(Collections.singletonList(new Token(this)));
        }}.create();
    }

    @Test(description = "Создание пользователя. Пользователь без прав администратора")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Зотов С.Д.")
    public void failCreateUserTest() {
        postRequest();
        RestResponse response = apiClient.execute(request);
        AllureAssert.assertEquals(response.getStatusCode(), 403,
                "статус код");
    }

    @Step("Запрос POST")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Зотов С.Д.")
    private void postRequest() {
        apiClient = new RestAssuredClient(user);
        String body = GSON.toJson(new UserInfoDto(new UserDto()));
        request = new RestAssuredRequest(POST, "/users.json", null, null, body);
    }
}
