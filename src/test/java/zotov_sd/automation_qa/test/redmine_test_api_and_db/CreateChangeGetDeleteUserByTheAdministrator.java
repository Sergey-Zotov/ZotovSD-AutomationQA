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
import zotov_sd.automation_qa.api.dto.users.ErrorInfoDto;
import zotov_sd.automation_qa.api.dto.users.UserDto;
import zotov_sd.automation_qa.api.dto.users.UserInfoDto;
import zotov_sd.automation_qa.api.rest_assured.RestAssuredClient;
import zotov_sd.automation_qa.api.rest_assured.RestAssuredRequest;
import zotov_sd.automation_qa.model.user.Email;
import zotov_sd.automation_qa.model.user.Token;
import zotov_sd.automation_qa.model.user.User;

import java.util.Arrays;
import java.util.Collections;

import static zotov_sd.automation_qa.api.client.RestMethod.*;
import static zotov_sd.automation_qa.api.rest_assured.GsonProvider.GSON;

public class CreateChangeGetDeleteUserByTheAdministrator {

    private RestApiClient apiClient;
    private RestRequest request;
    private UserInfoDto dto;
    private UserInfoDto userResponse;
    private User userDB;

    @BeforeMethod(description = "Создание пользователя с правами Администратора, с доступом к API и ключ API")
    public void prepareFixtures() {
        User Admin = new User() {{
            setIsAdmin(true);
            setEmails(Arrays.asList(new Email(this), new Email(this)));
            setTokens(Collections.singletonList(new Token(this)));
        }}.create();

        apiClient = new RestAssuredClient(Admin);

    }

    @Test(description = " Создание, изменение, получение, удаление пользователя. Администратор системы")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Зотов С.Д.")
    public void userTest() {

        dto = new UserInfoDto(new UserDto().setStatus(2));
        postUser(postResponse(dto));

        repeatedPostUser(postResponse(dto));

        dto = new UserInfoDto(new UserDto().setMail("zotmail.ru").setPassword("1Qa@"));
        repeatedPostUserNoValidEmailAndPassword(postResponse(dto));
        
        userResponse.getUser().setStatus(1);
        putUser(putResponse(userResponse));

        getUser(getResponse(userResponse));

        deleteUserResponse(userResponse);

        repeatedDeleteUserResponse(userResponse);
    }

    @Step("Сравнение пользователя из ответа API с темже пользователем из БД")
    private void compare() {
        AllureAssert.assertNotNull(userResponse.getUser().getStatus(), "Статус");
        AllureAssert.assertEquals(userResponse.getUser().getStatus(), userDB.getStatus().statusCode);
        AllureAssert.assertNotNull(userResponse.getUser().getLogin(), "Логин");
        AllureAssert.assertEquals(userResponse.getUser().getLogin(), userDB.getLogin());
        AllureAssert.assertNotNull(userResponse.getUser().getLastName(),"Имя");
        AllureAssert.assertEquals(userResponse.getUser().getLastName(), userDB.getLastName());
        AllureAssert.assertNotNull(userResponse.getUser().getFirstName(),"Фамилии");
        AllureAssert.assertEquals(userResponse.getUser().getFirstName(), userDB.getFirstName());
        AllureAssert.assertNotNull(userResponse.getUser().getAdmin(),"статус Админестратора");
        AllureAssert.assertEquals(userResponse.getUser().getAdmin(), userDB.getIsAdmin());
        AllureAssert.assertNotNull(userResponse.getUser().getMail(),"Mail");
        AllureAssert.assertEquals(userResponse.getUser().getMail(), userDB.getEmails().get(0).getAddress());
        AllureAssert.assertNotNull(userResponse.getUser().getApiKey(), "API ключ");
        AllureAssert.assertEquals(userResponse.getUser().getApiKey(), userDB.getTokens().get(0).getValue());
    }

    @Step("Запрос POST на создание пользователя")
    private RestResponse postResponse(UserInfoDto dto) {
        String body = GSON.toJson(dto);
        request = new RestAssuredRequest(POST, "/users.json", null, null, body);
        return apiClient.execute(request);
    }

    @Step("Проверка нового пользователя, со статусом = 2")
    private void postUser(RestResponse response) {
        userResponse = response.getPayload(UserInfoDto.class);
        userDB = new User().read(userResponse.getUser().getId());
        AllureAssert.assertEquals(userResponse.getUser().getStatus(), 2,
                "статус пользователя");
        AllureAssert.assertEquals(response.getStatusCode(), 201,
                "статус код 201");
        compare();
    }

    @Step("Проверка на создание пользователя повторно с тем же телом запроса")
    private void repeatedPostUser(RestResponse response) {
        ErrorInfoDto repeated = response.getPayload(ErrorInfoDto.class);
        AllureAssert.assertEquals(repeated.getErrors().get(0), "Email уже существует",
                "сообщения из ответа");
        AllureAssert.assertEquals(repeated.getErrors().get(1), "Пользователь уже существует",
                "сообщения из ответа");
        AllureAssert.assertEquals(response.getStatusCode(), 422, "статус код 422");
        AllureAssert.assertEquals(response.getPayload(), "{\"errors\":[\"Email уже существует\",\"Пользователь уже существует\"]}",
                "тело ответа");
    }

    @Step("Проверка на создание пользователя повторно с тем же телом запроса, с невалидным \"email\" и \"password\" ")
    private void repeatedPostUserNoValidEmailAndPassword(RestResponse response) {
        AllureAssert.assertEquals(response.getStatusCode(), 422,
                "статус код 422");
        AllureAssert.assertEquals(response.getPayload(), "{\"errors\":[\"Email имеет неверное значение\",\"Пароль недостаточной длины (не может быть меньше 8 символа)\"]}",
                "тело ответа");
    }

    @Step("Запрос PUT на изменение пользователя")
    private RestResponse putResponse(UserInfoDto dto) {
        String body = GSON.toJson(dto);
        request = new RestAssuredRequest(PUT, "/users/" + dto.getUser().getId() + ".json", null, null, body);
        return apiClient.execute(request);
    }

    @Step("Проверка измененного пользователя")
    private void putUser(RestResponse response) {
        AllureAssert.assertEquals(response.getStatusCode(), 204,
                "статус код");
        AllureAssert.assertNotNull(userResponse.getUser().getStatus(),
                "статус пользователя");
        AllureAssert.assertEquals(userResponse.getUser().getStatus(), 1,
                "статус пользователя из запроса");
        userDB = new User().read(userResponse.getUser().getId());
        AllureAssert.assertEquals(userDB.getStatus().statusCode, 1,
                "статус пользователя из БД");
    }

    @Step("Запрос GET на получение пользователя")
    private RestResponse getResponse(UserInfoDto dto) {
        request = new RestAssuredRequest(GET, "/users/" + dto.getUser().getId() + ".json", null, null, null);
        return apiClient.execute(request);
    }

    @Step("Проверка полученного пользователя")
    private void getUser(RestResponse response) {
        userResponse = response.getPayload(UserInfoDto.class);
        userDB = new User().read(userResponse.getUser().getId());
        AllureAssert.assertEquals(response.getStatusCode(), 200,
                "статус код");
        AllureAssert.assertEquals(userResponse.getUser().getStatus(), 1,
                "статус пользователя из запроса");
        compare();
    }

    @Step("Запрос DELETE на удаление пользователя, проверка его удаления")
    private void deleteUserResponse(UserInfoDto dto) {
        Integer id = dto.getUser().getId();
        request = new RestAssuredRequest(DELETE, "/users/" + dto.getUser().getId() + ".json", null, null, null);
        RestResponse response = apiClient.execute(request);
        AllureAssert.assertEquals(response.getStatusCode(), 204,
                "статус код");
        AllureAssert.assertNull(userDB = new User().read(id),
                "пользователь в БД");
    }

    @Step("Запрос DELETE на повторное удаление пользователя, проверка ответа")
    private void repeatedDeleteUserResponse(UserInfoDto dto) {
        request = new RestAssuredRequest(DELETE, "/users/" + dto.getUser().getId() + ".json", null, null, null);
        RestResponse response = apiClient.execute(request);
        AllureAssert.assertEquals(response.getStatusCode(), 404,
                "статус код");
    }
}
