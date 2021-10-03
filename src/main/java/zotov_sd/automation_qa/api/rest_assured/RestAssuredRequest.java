package zotov_sd.automation_qa.api.rest_assured;

import lombok.Getter;
import lombok.NonNull;
import zotov_sd.automation_qa.api.client.RestMethod;
import zotov_sd.automation_qa.api.client.RestRequest;

import java.util.HashMap;
import java.util.Map;

@Getter
public class RestAssuredRequest implements RestRequest {

    @NonNull
    private RestMethod method;
    @NonNull
    private String uri;

    private Map<String, String> headers = new HashMap<>();
    private Map<String, String> queryParameters = new HashMap<>();
    private String body;

    public RestAssuredRequest(@NonNull RestMethod method, @NonNull String uri, Map<String, String> headers, Map<String, String> queryParameters, String body) {
        this.method = method;
        this.uri = uri;
        if (headers != null) {
            this.headers = headers;
        }
        if (queryParameters != null) {
            this.queryParameters = queryParameters;
        }
        this.body = body;
    }

}
