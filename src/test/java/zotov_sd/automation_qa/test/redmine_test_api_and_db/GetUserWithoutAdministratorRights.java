package zotov_sd.automation_qa.test.redmine_test_api_and_db;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import zotov_sd.automation_qa.api.client.RestApiClient;
import zotov_sd.automation_qa.api.client.RestRequest;
import zotov_sd.automation_qa.api.client.RestResponse;
import zotov_sd.automation_qa.api.dto.users.UserInfoDto;
import zotov_sd.automation_qa.api.rest_assured.RestAssuredClient;
import zotov_sd.automation_qa.api.rest_assured.RestAssuredRequest;
import zotov_sd.automation_qa.model.user.Email;
import zotov_sd.automation_qa.model.user.Token;
import zotov_sd.automation_qa.model.user.User;

import java.util.Arrays;

import static zotov_sd.automation_qa.api.client.RestMethod.GET;

public class GetUserWithoutAdministratorRights {

    private RestApiClient apiClient;
    private RestRequest request;
    private User user1;
    private User user2;

    @BeforeMethod
    public void prepareFixtures() {
        user1 = new User() {{
            setEmails(Arrays.asList(new Email(this)));
            setTokens(Arrays.asList(new Token(this)));
        }}.create();
        user2 = new User().create();
    }

    @Test
    public void getUser1Test() {
        apiClient = new RestAssuredClient(user1);
        request = new RestAssuredRequest(GET, "/users/" + user1.getId() + ".json", null, null, null);
        RestResponse response = apiClient.execute(request);
        Assert.assertEquals(response.getStatusCode(), 200);
        UserInfoDto userInfo = response.getPayload(UserInfoDto.class);
        Assert.assertFalse(userInfo.getUser().getAdmin());
        Assert.assertEquals(userInfo.getUser().getApiKey(), user1.getTokens().get(0).getValue());
        Assert.assertTrue(contains(response, "admin"));
        Assert.assertTrue(contains(response, "api_key"));
    }

    @Test
    public void getUser2Test() {
        apiClient = new RestAssuredClient(user1);
        request = new RestAssuredRequest(GET, "/users/" + user2.getId() + ".json", null, null, null);
        RestResponse response = apiClient.execute(request);
        Assert.assertEquals(response.getStatusCode(), 200);
        UserInfoDto userInfo = response.getPayload(UserInfoDto.class);
        Assert.assertFalse(userInfo.getUser().getAdmin());
        Assert.assertNull(userInfo.getUser().getApiKey());
        Assert.assertFalse(contains(response, "admin"));
        Assert.assertFalse(contains(response, "api_key"));
    }

    private Boolean contains(RestResponse response, String value) {
        String responsePayload = response.getPayload();
        return responsePayload.contains(value);
    }
}
