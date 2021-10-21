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

import static zotov_sd.automation_qa.api.client.RestMethod.POST;
import static zotov_sd.automation_qa.api.rest_assured.GsonProvider.GSON;

public class CreateUserWithoutAdministratorRights {

    private RestApiClient apiClient;
    private RestRequest request;

    @BeforeMethod
    public void prepareFixtures() {
        User user = new User() {{
            setEmails(Arrays.asList(new Email(this), new Email(this)));
            setTokens(Arrays.asList(new Token(this)));
        }}.create();

        apiClient = new RestAssuredClient(user);
        String body = GSON.toJson(new UserInfoDto(new UserDto()));
        request = new RestAssuredRequest(POST, "/users.json", null, null, body);
    }

    @Test
    public void failCreateUserTest() {
        RestResponse response = apiClient.execute(request);
        Assert.assertEquals(response.getStatusCode(), 403);
    }
}
