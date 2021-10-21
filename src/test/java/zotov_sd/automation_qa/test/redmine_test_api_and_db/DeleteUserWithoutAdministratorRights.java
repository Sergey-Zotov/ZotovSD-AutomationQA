package zotov_sd.automation_qa.test.redmine_test_api_and_db;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import zotov_sd.automation_qa.api.client.RestApiClient;
import zotov_sd.automation_qa.api.client.RestRequest;
import zotov_sd.automation_qa.api.client.RestResponse;
import zotov_sd.automation_qa.api.rest_assured.RestAssuredClient;
import zotov_sd.automation_qa.api.rest_assured.RestAssuredRequest;
import zotov_sd.automation_qa.model.user.Email;
import zotov_sd.automation_qa.model.user.Token;
import zotov_sd.automation_qa.model.user.User;

import java.util.Arrays;

import static zotov_sd.automation_qa.api.client.RestMethod.DELETE;

public class DeleteUserWithoutAdministratorRights {

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
    public void deleteUser2Test() {
        apiClient = new RestAssuredClient(user1);
        request = new RestAssuredRequest(DELETE, "/users/" + user2.getId() + ".json", null, null, null);
        RestResponse response = apiClient.execute(request);
        Assert.assertEquals(response.getStatusCode(), 403);
        User nullUser = new User().read(user2.getId());
        Assert.assertNotNull(nullUser);
    }

    @Test
    public void deleteUser1Test() {
        apiClient = new RestAssuredClient(user1);
        request = new RestAssuredRequest(DELETE, "/users/" + user1.getId() + ".json", null, null, null);
        RestResponse response = apiClient.execute(request);
        Assert.assertEquals(response.getStatusCode(), 403);
        Assert.assertNotNull(new User().read(user1.getId()));
    }
}
