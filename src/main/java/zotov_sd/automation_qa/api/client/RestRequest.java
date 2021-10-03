package zotov_sd.automation_qa.api.client;

import java.util.Map;

public interface RestRequest {

    RestMethod getMethod();

    String getUri();

    Map<String, String> getHeaders();

    Map<String, String> getQueryParameters();

    String getBody();

}
