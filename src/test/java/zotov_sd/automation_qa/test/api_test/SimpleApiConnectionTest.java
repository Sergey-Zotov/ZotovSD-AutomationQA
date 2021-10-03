package zotov_sd.automation_qa.test.api_test;

import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import zotov_sd.automation_qa.api.client.RestApiClient;
import zotov_sd.automation_qa.api.client.RestRequest;
import zotov_sd.automation_qa.api.client.RestResponse;
import zotov_sd.automation_qa.api.dto.users.UserDto;
import zotov_sd.automation_qa.api.dto.users.UserInfoDto;
import zotov_sd.automation_qa.api.dto.users.UsersListDto;
import zotov_sd.automation_qa.api.rest_assured.RestAssuredClient;
import zotov_sd.automation_qa.api.rest_assured.RestAssuredRequest;
import zotov_sd.automation_qa.model.user.Token;
import zotov_sd.automation_qa.model.user.User;

import java.util.Collections;

import static io.restassured.RestAssured.given;
import static zotov_sd.automation_qa.api.client.RestMethod.GET;
import static zotov_sd.automation_qa.api.rest_assured.GsonProvider.GSON;

public class SimpleApiConnectionTest {

    private final RequestSpecification NO_AUTH_SPECIFICATION = given().baseUri("http://edu-at.dfu.i-teco.ru");
    private final RequestSpecification ADMIN_AUTH_SPECIFICATION = given(NO_AUTH_SPECIFICATION)
            .header("X-Redmine-API-Key", "55dfd83d5c925f999826c683114e589a4dd9f7e6")
            .log().all();

    @Test
    public void testSimpleRequest() {
        given(ADMIN_AUTH_SPECIFICATION)
                .request(Method.GET, "/users.json")
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void testNoAuthGetUsers() {
        given(NO_AUTH_SPECIFICATION)
                .log().all()
                .request(Method.GET, "/users.json")
                .then()
                .log().all()
                .statusCode(401);
    }

    @Test
    public void testUserCreation() {
        UserInfoDto body = new UserInfoDto(
                new UserDto()
                        .setLogin("jplang116")
                        .setLastName("Jean-Philippe116")
                        .setFirstName("Lang116")
                        .setMail("jp_lang116@yahoo.fr")
                        .setPassword("secret116")
        );
        given(ADMIN_AUTH_SPECIFICATION).contentType(ContentType.JSON)
                .body(GSON.toJson(body))
                .request(Method.POST, "/users.json")
                .then()
                .log().all()
                .statusCode(422);
    }


    @Test
    public void testApiClient() {
        User user = new User() {{
            setIsAdmin(true);
            setTokens(Collections.singletonList(new Token(this)));
        }}.create();

        RestApiClient apiClient = new RestAssuredClient(user);
        RestRequest request = new RestAssuredRequest(GET, "/users.json", null, null, null);

        RestResponse response = apiClient.execute(request);

        Assert.assertEquals(response.getStatusCode(), 200);

        UsersListDto body = response.getPayload(UsersListDto.class);

        Assert.assertEquals(body.getLimit().intValue(), 25);

        Assert.assertEquals(body.getUsers().get(0).getLogin(), "admin");
        Assert.assertEquals(body.getUsers().get(0).getFirstName(), "Redmine");
        Assert.assertEquals(body.getUsers().get(0).getLastName(), "Admin");


    }

}
