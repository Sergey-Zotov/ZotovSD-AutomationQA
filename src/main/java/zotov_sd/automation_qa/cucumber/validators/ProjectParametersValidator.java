package zotov_sd.automation_qa.cucumber.validators;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class ProjectParametersValidator {

    public static void validateProjectParameters(Set<String> keys) {
        List<String> allowedKeys = Arrays.asList("Статус");

        boolean allKeysAreValid = allowedKeys.containsAll(keys);
        if (!allKeysAreValid) {
            throw new IllegalArgumentException("Среди переданных параметров проекта есть недопустимые параметры");
        }
    }
}
