package zotov_sd.automation_qa.cucumber.validators;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class UserDtoParametersValidator {

    public static void validateUserDtoParameters(Set<String> keys) {
        List<String> allowedKeys = Arrays.asList("Статус", "Email", "Пароль");

        boolean allKeysAreValid = allowedKeys.containsAll(keys);
        if (!allKeysAreValid) {
            throw new IllegalArgumentException("Среди переданных параметров пользователя есть недопустимые параметры");
        }
    }
}
