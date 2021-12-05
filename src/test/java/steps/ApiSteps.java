package steps;

import cucumber.api.java.ru.Если;
import cucumber.api.java.ru.Пусть;
import cucumber.api.java.ru.То;
import org.testng.Assert;
import zotov_sd.automation_qa.allure.AllureAssert;
import zotov_sd.automation_qa.api.client.RestApiClient;
import zotov_sd.automation_qa.api.client.RestRequest;
import zotov_sd.automation_qa.api.client.RestResponse;
import zotov_sd.automation_qa.api.dto.users.ErrorInfoDto;
import zotov_sd.automation_qa.api.dto.users.UserDto;
import zotov_sd.automation_qa.api.dto.users.UserInfoDto;
import zotov_sd.automation_qa.api.rest_assured.RestAssuredClient;
import zotov_sd.automation_qa.api.rest_assured.RestAssuredRequest;
import zotov_sd.automation_qa.context.Context;
import zotov_sd.automation_qa.cucumber.validators.RequestParametersValidator;
import zotov_sd.automation_qa.cucumber.validators.UserDtoParametersValidator;
import zotov_sd.automation_qa.model.user.User;

import java.util.List;
import java.util.Map;

import static zotov_sd.automation_qa.api.client.RestMethod.*;
import static zotov_sd.automation_qa.api.rest_assured.GsonProvider.GSON;

public class ApiSteps {

    @Пусть("В системе есть пользователь для отправки запроса API \"(.+)\"")
    public void createUserInfoDTO(String userDTOStashId) {
        UserInfoDto dto = new UserInfoDto(new UserDto());
        Context.getStash().put(userDTOStashId, dto);
    }

    @Пусть("Выполнено подключение к \"(.+)\" пользователем \"(.+)\"")
    public void connectAPI(String apiStashId, String userStashId) {
        User user = Context.getStash().get(userStashId, User.class);
        RestApiClient apiClient = new RestAssuredClient(user);
        Context.getStash().put(apiStashId, apiClient);
    }

    @Если("Отправить запрос \"(.+)\" пользователя \"(.+)\" и сохранить ответ \"(.+)\" от \"(.+)\"")
    public void request(String request, String userDTOStashId, String restResponseStashId, String apiStashId) {
        RequestParametersValidator.validateRequestParameters(request);
        RestRequest req;
        RestResponse restResponse;
        RestApiClient apiClient;
        if (request.equals("POST")) {
            UserInfoDto dto = Context.getStash().get(userDTOStashId, UserInfoDto.class);
            String body = GSON.toJson(dto);
            req = new RestAssuredRequest(POST, "/users.json", null, null, body);
            apiClient = Context.getStash().get(apiStashId, RestApiClient.class);
            restResponse = apiClient.execute(req);
            Context.getStash().put(restResponseStashId, restResponse);
        }
        if (request.equals("PUT")) {
            UserInfoDto dto = Context.getStash().get(userDTOStashId, UserInfoDto.class);
            String body = GSON.toJson(dto);
            req = new RestAssuredRequest(PUT, "/users/" + dto.getUser().getId() + ".json", null, null, body);
            apiClient = Context.getStash().get(apiStashId, RestApiClient.class);
            restResponse = apiClient.execute(req);
            Context.getStash().put(restResponseStashId, restResponse);
        }
        if (request.equals("GET")) {
            UserInfoDto dto;
            User user;
            try {
                dto = Context.getStash().get(userDTOStashId, UserInfoDto.class);
                req = new RestAssuredRequest(GET, "/users/" + dto.getUser().getId() + ".json", null, null, null);
            } catch (ClassCastException e) {
                user = Context.getStash().get(userDTOStashId, User.class);
                req = new RestAssuredRequest(GET, "/users/" + user.getId() + ".json", null, null, null);
            }
            apiClient = Context.getStash().get(apiStashId, RestApiClient.class);
            restResponse = apiClient.execute(req);
            Context.getStash().put(restResponseStashId, restResponse);
        }
        if (request.equals("DELETE")) {
            UserInfoDto dto;
            User user;
            try {
                dto = Context.getStash().get(userDTOStashId, UserInfoDto.class);
                req = new RestAssuredRequest(DELETE, "/users/" + dto.getUser().getId() + ".json", null, null, null);
            } catch (ClassCastException e) {
                user = Context.getStash().get(userDTOStashId, User.class);
                req = new RestAssuredRequest(DELETE, "/users/" + user.getId() + ".json", null, null, null);
            }
            apiClient = Context.getStash().get(apiStashId, RestApiClient.class);
            restResponse = apiClient.execute(req);
            Context.getStash().put(restResponseStashId, restResponse);
        }
    }

    @То("Получен статус код ответа \"(.+)\" из ответа \"(.+)\"")
    public void assertStatusCode(String status, String restResponseStashId) {
        Integer statusCode = Integer.parseInt(status);
        RestResponse restResponse = Context.getStash().get(restResponseStashId, RestResponse.class);
        AllureAssert.assertEquals(restResponse.getStatusCode(), statusCode,
                "статус код " + statusCode);
    }

    @То("Тело ответа \"(.+)\" содержит данные о пользователе \"(.+)\" теже что и у созданного пользователя в БД")
    public void reconciliationOfResponseWithDataBase(String restResponseStashId, String userResponseStashId) {
        RestResponse restResponse = Context.getStash().get(restResponseStashId, RestResponse.class);
        UserInfoDto userResponse = restResponse.getPayload(UserInfoDto.class);

        if (userResponse != null) Context.getStash().put(userResponseStashId, userResponse);
        if (userResponse == null) userResponse = Context.getStash().get(userResponseStashId, UserInfoDto.class);

        User userDB = new User().read(userResponse.getUser().getId());

        AllureAssert.assertNotNull(userResponse.getUser().getStatus(), "Статус");
        AllureAssert.assertEquals(userResponse.getUser().getStatus(), userDB.getStatus().statusCode);
        AllureAssert.assertNotNull(userResponse.getUser().getLogin(), "Логин");
        AllureAssert.assertEquals(userResponse.getUser().getLogin(), userDB.getLogin());
        AllureAssert.assertNotNull(userResponse.getUser().getLastName(), "Имя");
        AllureAssert.assertEquals(userResponse.getUser().getLastName(), userDB.getLastName());
        AllureAssert.assertNotNull(userResponse.getUser().getFirstName(), "Фамилии");
        AllureAssert.assertEquals(userResponse.getUser().getFirstName(), userDB.getFirstName());
        AllureAssert.assertNotNull(userResponse.getUser().getAdmin(), "статус Админестратора");
        AllureAssert.assertEquals(userResponse.getUser().getAdmin(), userDB.getIsAdmin());
        AllureAssert.assertNotNull(userResponse.getUser().getMail(), "Mail");
        AllureAssert.assertEquals(userResponse.getUser().getMail(), userDB.getEmails().get(0).getAddress());
        AllureAssert.assertNotNull(userResponse.getUser().getApiKey(), "API ключ");
        AllureAssert.assertEquals(userResponse.getUser().getApiKey(), userDB.getTokens().get(0).getValue());
    }

    @То("Тело ответа \"(.+)\" содержит errors, содержащий строки:")
    public void responseBodyContainsFollowingLines(String restResponseStashId, List<String> parameters) {
        RestResponse restResponse = Context.getStash().get(restResponseStashId, RestResponse.class);
        ErrorInfoDto repeated = restResponse.getPayload(ErrorInfoDto.class);
        for (String parameter : parameters) {
            Assert.assertTrue(repeated.getErrors().contains(parameter));
        }
    }

    @Если("Изменить данные пользователя \"(.+)\", параметры:")
    public void changeUserDataParameters(String userDTOStashId, Map<String, String> parameters) {
        UserDtoParametersValidator.validateUserDtoParameters(parameters.keySet());
        UserInfoDto dto = Context.getStash().get(userDTOStashId, UserInfoDto.class);
        if (parameters.containsKey("Статус")) {
            String statusDescription = parameters.get("Статус");
            dto.getUser().setStatus(Integer.parseInt(statusDescription));
        }
        if (parameters.containsKey("Email")) {
            String email = parameters.get("Email");
            dto.getUser().setMail(email);
        }
        if (parameters.containsKey("Пароль")) {
            String password = parameters.get("Пароль");
            dto.getUser().setPassword(password);
        }
        Context.getStash().put(userDTOStashId, dto);
    }

    @То("Тело ответа \"(.+)\" содержит данные пользователя \"(.+)\", указанные при его создании")
    public void responseBodyContainsUserDataSpecifiedWhenItWasCreated(String restResponseStashId, String userDTOStashId) {
        UserInfoDto userDto = Context.getStash().get(userDTOStashId, UserInfoDto.class);
        RestResponse restResponse = Context.getStash().get(restResponseStashId, RestResponse.class);
        UserInfoDto responseUserDto = restResponse.getPayload(UserInfoDto.class);

        AllureAssert.assertEquals(responseUserDto.getUser().getStatus(), userDto.getUser().getStatus());
        AllureAssert.assertEquals(responseUserDto.getUser().getLogin(), userDto.getUser().getLogin());
        AllureAssert.assertEquals(responseUserDto.getUser().getLastName(), userDto.getUser().getLastName());
        AllureAssert.assertEquals(responseUserDto.getUser().getFirstName(), userDto.getUser().getFirstName());
        AllureAssert.assertEquals(responseUserDto.getUser().getAdmin(), userDto.getUser().getAdmin());
        AllureAssert.assertEquals(responseUserDto.getUser().getMail(), userDto.getUser().getMail());
        AllureAssert.assertEquals(responseUserDto.getUser().getApiKey(), userDto.getUser().getApiKey());
    }

    @То("В базе данных отсутствует пользователь \"(.+)\"")
    public void noUserInDatabase(String userDTOStashId) {
        try {
            UserInfoDto userDto = Context.getStash().get(userDTOStashId, UserInfoDto.class);
            AllureAssert.assertNull(new User().read(userDto.getUser().getId()),
                    "пользователь в БД");
        } catch (ClassCastException e) {
            User user = Context.getStash().get(userDTOStashId, User.class);
            AllureAssert.assertNull(new User().read(user.getId()),
                    "пользователь в БД");
        }
    }

    @Пусть("В системе есть пользователь \"(.+)\" без ключа API")
    public void thereIsUserInTheSystemWithoutAnAPIKey(String userStashId) {
        User user = new User();
        user.create();
        Context.getStash().put(userStashId, user);
    }

    @То("В теле ответа \"(.+)\" содержится информация о пользователе \"(.+)\"")
    public void responseBodyContainsInformationAboutUser(String restResponseStashId, String userStashId) {
        RestResponse restResponse = Context.getStash().get(restResponseStashId, RestResponse.class);
        UserInfoDto userResponse = restResponse.getPayload(UserInfoDto.class);
        User user = Context.getStash().get(userStashId, User.class);

        AllureAssert.assertEquals(userResponse.getUser().getLogin(), user.getLogin());
        AllureAssert.assertEquals(userResponse.getUser().getLastName(), user.getLastName());
        AllureAssert.assertEquals(userResponse.getUser().getFirstName(), user.getFirstName());
        AllureAssert.assertEquals(userResponse.getUser().getAdmin(), user.getIsAdmin());
        if (userResponse.getUser().getApiKey() != null)
            AllureAssert.assertEquals(userResponse.getUser().getApiKey(), user.getTokens().get(0).getValue());
    }

    @То("В базе данных присутствует пользователь \"(.+)\"")
    public void thereIsUserInTheDatabase(String userDTOStashId) {
        try {
            UserInfoDto userDto = Context.getStash().get(userDTOStashId, UserInfoDto.class);
            AllureAssert.assertNotNull(new User().read(userDto.getUser().getId()),
                    "пользователь в БД");
        } catch (ClassCastException e) {
            User user = Context.getStash().get(userDTOStashId, User.class);
            AllureAssert.assertNotNull(new User().read(user.getId()),
                    "пользователь в БД");
        }
    }
}