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
import zotov_sd.automation_qa.api.dto.users.UserInfoDto;
import zotov_sd.automation_qa.api.rest_assured.RestAssuredClient;
import zotov_sd.automation_qa.api.rest_assured.RestAssuredRequest;
import zotov_sd.automation_qa.model.user.Email;
import zotov_sd.automation_qa.model.user.Token;
import zotov_sd.automation_qa.model.user.User;

import java.util.Collections;

import static zotov_sd.automation_qa.api.client.RestMethod.GET;

public class GetUserWithoutAdministratorRights {

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

    @Test(description = "Получение пользователя 1. Пользователем 1")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Зотов С.Д.")
    public void getUser1Test() {
        RestResponse response = response(user1.getId());
        AllureAssert.assertEquals(response.getStatusCode(), 200,
                "статус код 200");
        UserInfoDto userInfo = response.getPayload(UserInfoDto.class);
        AllureAssert.assertFalse(userInfo.getUser().getAdmin(),
                "Является ли пользователь 1 Адменистратором");
        AllureAssert.assertEquals(userInfo.getUser().getApiKey(), user1.getTokens().get(0).getValue(),
                "ключ API");
        AllureAssert.assertTrue(contains(response, "admin"),
                "В ответе присутствует поле \"admin\"");
        AllureAssert.assertTrue(contains(response, "api_key"),
                "В ответе присутствует поле \"api_key\"");
    }

    @Test(description = "Получение пользователя 2. Пользователем 1")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Зотов С.Д.")
    public void getUser2Test() {
        RestResponse response = response(user2.getId());
        AllureAssert.assertEquals(response.getStatusCode(), 200,
                "статус код 200");
        UserInfoDto userInfo = response.getPayload(UserInfoDto.class);
        AllureAssert.assertFalse(userInfo.getUser().getAdmin(),
                "Является ли пользователь 2 Адменистратором");
        AllureAssert.assertNull(userInfo.getUser().getApiKey(),
                "API Key");
        AllureAssert.assertFalse(contains(response, "admin"),
                "В ответе присутствует поле \"admin\"");
        AllureAssert.assertFalse(contains(response, "api_key"),
                "В ответе присутствует поле \"api_key\"");
    }

    @Step("Запрос GET пользавотелем 1")
    private RestResponse response(Integer userId) {
        apiClient = new RestAssuredClient(user1);
        request = new RestAssuredRequest(GET, "/users/" + userId + ".json", null, null, null);
        return apiClient.execute(request);
    }

    private Boolean contains(RestResponse response, String value) {
        String responsePayload = response.getPayload();
        return responsePayload.contains(value);
    }
}
