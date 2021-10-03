package zotov_sd.automation_qa.api.client;

import java.util.Map;

public interface RestResponse {

    int getStatusCode();

    Map<String, String> getHeaders();

    String getPayload();

    <T> T getPayload(Class<T> clazz);

}
