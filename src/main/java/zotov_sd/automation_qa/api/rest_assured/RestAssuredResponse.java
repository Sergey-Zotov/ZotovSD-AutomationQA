package zotov_sd.automation_qa.api.rest_assured;

import io.restassured.http.Header;
import io.restassured.response.Response;
import lombok.Getter;
import zotov_sd.automation_qa.api.client.RestResponse;

import java.util.Map;
import java.util.stream.Collectors;

import static zotov_sd.automation_qa.api.rest_assured.GsonProvider.GSON;

@Getter
public class RestAssuredResponse implements RestResponse {

    private int statusCode;
    private Map<String, String> headers;
    private String payload;

    public RestAssuredResponse(Response response) {
        this.statusCode = response.getStatusCode();
        this.headers = response.getHeaders().asList().stream()
                .collect(Collectors.toMap(Header::getName, Header::getValue));
        this.payload = response.getBody().asString();
    }

    @Override
    public <T> T getPayload(Class<T> clazz) {
        return GSON.fromJson(payload, clazz);
    }
}
