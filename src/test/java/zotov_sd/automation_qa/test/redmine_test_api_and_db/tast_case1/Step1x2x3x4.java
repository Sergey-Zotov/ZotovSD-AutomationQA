package zotov_sd.automation_qa.test.redmine_test_api_and_db.tast_case1;

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

import static zotov_sd.automation_qa.api.client.RestMethod.POST;
import static zotov_sd.automation_qa.api.rest_assured.GsonProvider.GSON;

public class Step1x2x3x4 {

    private RestApiClient apiClient;
    private RestRequest request;
    private UserInfoDto dto;

    @BeforeMethod
    public void prepareFixtures() {
        User Admin = new User() {{
            setIsAdmin(true);
            setEmails(Arrays.asList(new Email(this), new Email(this)));
            setTokens(Arrays.asList(new Token(this)));
        }}.create();

        apiClient = new RestAssuredClient(Admin);

        dto = new UserInfoDto(new UserDto().setStatus(2));
        String body = GSON.toJson(dto);

        request = new RestAssuredRequest(POST, "/users.json", null, null, body);
    }

    @Test
    public void create() {
        RestResponse response = apiClient.execute(request);
        Assert.assertEquals(response.getStatusCode(), 201);

        UserInfoDto userInfoDto = response.getPayload(UserInfoDto.class);
        User user = new User().read(userInfoDto.getUser().getId());

        Assert.assertEquals(userInfoDto.getUser().getStatus(), user.getStatus().statusCode);
        Assert.assertEquals(userInfoDto.getUser().getStatus().intValue(), 2);
        Assert.assertEquals(userInfoDto.getUser().getLogin(), user.getLogin());
        Assert.assertEquals(userInfoDto.getUser().getLastName(), user.getLastName());
        Assert.assertEquals(userInfoDto.getUser().getFirstName(), user.getFirstName());

        RestResponse responseRepeat = apiClient.execute(request);

        Assert.assertEquals(responseRepeat.getStatusCode(), 422);
        Assert.assertEquals(responseRepeat.getPayload(), "{\"errors\":[\"Email уже существует\",\"Пользователь уже существует\"]}");

        dto = new UserInfoDto(new UserDto().setMail("zotmail.ru").setPassword("1Qa@"));
        String body = GSON.toJson(dto);
        request = new RestAssuredRequest(POST, "/users.json", null, null, body);
        RestResponse responseRepeat2 = apiClient.execute(request);

        Assert.assertEquals(responseRepeat2.getStatusCode(), 422);
        Assert.assertEquals(responseRepeat2.getPayload(), "{\"errors\":[\"Email имеет неверное значение\",\"Пароль недостаточной длины (не может быть меньше 8 символа)\"]}");

        user.delete();
    }
}
