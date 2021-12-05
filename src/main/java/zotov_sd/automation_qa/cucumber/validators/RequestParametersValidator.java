package zotov_sd.automation_qa.cucumber.validators;

import java.util.Arrays;
import java.util.List;

public class RequestParametersValidator {

    public static void validateRequestParameters(String keys) {
        List<String> allowedKeys = Arrays.asList("POST", "GET", "DELETE", "PUT");

        boolean allKeysAreValid = allowedKeys.contains(keys);
        if (!allKeysAreValid) {
            throw new IllegalArgumentException("Среди переданных параметров запроса есть недопустимые параметры");
        }
    }
}
