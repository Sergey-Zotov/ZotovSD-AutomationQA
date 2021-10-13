package zotov_sd.automation_qa.test.redmine_test_api_and_db;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
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

import static zotov_sd.automation_qa.api.client.RestMethod.*;
import static zotov_sd.automation_qa.api.rest_assured.GsonProvider.GSON;

public class TestCase1 {

    private RestApiClient apiClient;
    private RestRequest request;
    private UserInfoDto dto;
    private UserInfoDto userResponse;
    private User userDB;

    @BeforeMethod
    public void prepareFixtures() {
        User Admin = new User() {{
            setIsAdmin(true);
            setEmails(Arrays.asList(new Email(this), new Email(this)));
            setTokens(Arrays.asList(new Token(this)));
        }}.create();

        apiClient = new RestAssuredClient(Admin);
    }

    @Test
    public void create() {

        dto = new UserInfoDto(new UserDto().setStatus(2));
        oneStep(oneStepResponse(dto));

        twoStep(twoStepResponse(dto));

        dto = new UserInfoDto(new UserDto().setMail("zotmail.ru").setPassword("1Qa@"));
        threeStep(threeStepResponse(dto));

        userResponse.getUser().setStatus(1).setCreatedOn(null);
        fourStep(fourStepResponse(userResponse));

        fiveStep(fiveStepResponse(userResponse));

        sixStepResponse(userResponse);

        sevenStepResponse(userResponse);
    }

    private void compare() {
        Assert.assertNotNull(userResponse.getUser().getStatus());
        Assert.assertEquals(userResponse.getUser().getStatus(), userDB.getStatus().statusCode);
        Assert.assertNotNull(userResponse.getUser().getLogin());
        Assert.assertEquals(userResponse.getUser().getLogin(), userDB.getLogin());
        Assert.assertNotNull(userResponse.getUser().getLastName());
        Assert.assertEquals(userResponse.getUser().getLastName(), userDB.getLastName());
        Assert.assertNotNull(userResponse.getUser().getFirstName());
        Assert.assertEquals(userResponse.getUser().getFirstName(), userDB.getFirstName());
        Assert.assertNotNull(userResponse.getUser().getAdmin());
        Assert.assertEquals(userResponse.getUser().getAdmin(), userDB.getIsAdmin());
        Assert.assertNotNull(userResponse.getUser().getMail());
        Assert.assertEquals(userResponse.getUser().getMail(), userDB.getEmails().get(0).getAddress());
        Assert.assertNotNull(userResponse.getUser().getApiKey());
        Assert.assertEquals(userResponse.getUser().getApiKey(), userDB.getTokens().get(0).getValue());
    }

    private RestResponse oneStepResponse(UserInfoDto dto) {
        String body = GSON.toJson(dto);
        request = new RestAssuredRequest(POST, "/users.json", null, null, body);
        return apiClient.execute(request);
    }

    private void oneStep(RestResponse response) {
        userResponse = response.getPayload(UserInfoDto.class);
        userDB = new User().read(userResponse.getUser().getId());
        Assert.assertEquals(userResponse.getUser().getStatus().intValue(), 2);
        Assert.assertEquals(response.getStatusCode(), 201);
        compare();
    }

    private RestResponse twoStepResponse(UserInfoDto dto) {
        String body = GSON.toJson(dto);
        request = new RestAssuredRequest(POST, "/users.json", null, null, body);
        return apiClient.execute(request);
    }

    private void twoStep(RestResponse response) {
        Assert.assertEquals(response.getStatusCode(), 422);
        Assert.assertEquals(response.getPayload(), "{\"errors\":[\"Email уже существует\",\"Пользователь уже существует\"]}");
    }

    private RestResponse threeStepResponse(UserInfoDto dto) {
        String body = GSON.toJson(dto);
        request = new RestAssuredRequest(POST, "/users.json", null, null, body);
        return apiClient.execute(request);
    }

    private void threeStep(RestResponse response) {
        Assert.assertEquals(response.getStatusCode(), 422);
        Assert.assertEquals(response.getPayload(), "{\"errors\":[\"Email имеет неверное значение\",\"Пароль недостаточной длины (не может быть меньше 8 символа)\"]}");
    }

    private RestResponse fourStepResponse(UserInfoDto dto) {
        String body = GSON.toJson(dto);
        request = new RestAssuredRequest(PUT, "/users/" + dto.getUser().getId() + ".json", null, null, body);
        return apiClient.execute(request);
    }

    private void fourStep(RestResponse response) {
        Assert.assertEquals(response.getStatusCode(), 204);
        Assert.assertNotNull(userResponse.getUser().getStatus());
        Assert.assertEquals(userResponse.getUser().getStatus().intValue(), 1);
        userDB = new User().read(userResponse.getUser().getId());
        Assert.assertEquals(userDB.getStatus().statusCode.intValue(), 1);
    }

    private RestResponse fiveStepResponse(UserInfoDto dto) {
        request = new RestAssuredRequest(GET, "/users/" + dto.getUser().getId() + ".json", null, null, null);
        return apiClient.execute(request);
    }

    private void fiveStep(RestResponse response) {
        userResponse = response.getPayload(UserInfoDto.class);
        userDB = new User().read(userResponse.getUser().getId());
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(userResponse.getUser().getStatus().intValue(), 1);
        compare();
    }

    private void sixStepResponse(UserInfoDto dto) {
        Integer id = dto.getUser().getId();
        request = new RestAssuredRequest(DELETE, "/users/" + dto.getUser().getId() + ".json", null, null, null);
        RestResponse response = apiClient.execute(request);
        Assert.assertEquals(response.getStatusCode(), 204);
        Assert.assertNull(userDB = new User().read(id));
    }

    private void sevenStepResponse(UserInfoDto dto) {
        request = new RestAssuredRequest(DELETE, "/users/" + dto.getUser().getId() + ".json", null, null, null);
        RestResponse response = apiClient.execute(request);
        Assert.assertEquals(response.getStatusCode(), 404);
    }
}
