package zotov_sd.automation_qa.api.rest_assured;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import zotov_sd.automation_qa.api.client.RestApiClient;
import zotov_sd.automation_qa.api.client.RestMethod;
import zotov_sd.automation_qa.api.client.RestRequest;
import zotov_sd.automation_qa.api.client.RestResponse;
import zotov_sd.automation_qa.model.user.Token;
import zotov_sd.automation_qa.model.user.User;

import static io.restassured.RestAssured.given;
import static zotov_sd.automation_qa.property.Property.getStringProperty;

public class RestAssuredClient implements RestApiClient {

    protected RequestSpecification specification;

    public RestAssuredClient() {
        this.specification = given()
                .baseUri(getStringProperty("url"))
                .contentType(ContentType.JSON);
    }

    public RestAssuredClient(User user) {
        this();
        String token = user.getTokens().stream()
                .filter(tkn -> tkn.getAction() == Token.TokenType.API)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("У пользователя нет API-токена"))
                .getValue();
        specification.header("X-Redmine-API-Key", token);
    }

    @Override
    @Step("Выполнение API-запроса")
    public RestResponse execute(RestRequest request) {
        RequestSpecification spec = given(specification)
                .queryParams(request.getQueryParameters())
                .headers(request.getHeaders())
                .filter(new AllureRestAssured());
        if (request.getBody() != null) {
            spec.body(request.getBody());
        }
        spec.log().all();

        Response response = spec.request(
                toRestAssuredMethod(request.getMethod()),
                request.getUri()
        );

        response.then().log().all();

        return new RestAssuredResponse(response);
    }

    private Method toRestAssuredMethod(RestMethod method) {
        return Method.valueOf(method.name());
    }
}
