package zotov_sd.automation_qa.test.api_test;

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
import zotov_sd.automation_qa.model.user.Token;
import zotov_sd.automation_qa.model.user.User;

import java.util.Collections;

import static zotov_sd.automation_qa.api.client.RestMethod.POST;
import static zotov_sd.automation_qa.api.rest_assured.GsonProvider.GSON;


public class CreateUserByNotAdminUserTest {
    private RestApiClient apiClient;
    private RestRequest request;

    @BeforeMethod
    public void prepareFixtures() {
        User user = new User() {{
            setTokens(Collections.singletonList(new Token(this)));
        }}.create();

        apiClient = new RestAssuredClient(user);

        UserInfoDto dto = new UserInfoDto(
                new UserDto()
                        .setLogin("jplang116")
                        .setLastName("Jean-Philippe116")
                        .setFirstName("Lang116")
                        .setMail("jp_lang116@yahoo.fr")
                        .setPassword("secret116")
        );
        String body = GSON.toJson(dto);

        request = new RestAssuredRequest(POST, "/users.json", null, null, body);
    }


    @Test
    public void createUserByNotAdminUserTest() {

        RestResponse response = apiClient.execute(request);

        Assert.assertEquals(response.getStatusCode(), 403);

    }
}
