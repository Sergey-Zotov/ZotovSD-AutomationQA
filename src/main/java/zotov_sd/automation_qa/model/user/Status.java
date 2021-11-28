package zotov_sd.automation_qa.model.user;

import lombok.AllArgsConstructor;

import java.util.stream.Stream;

@AllArgsConstructor
public enum Status {
    UNREGISTERED(0, "Не зарегистрирован"),
    ACTIVE(1, "Активен"),
    UNACCEPTED(2, "Не подтвержден"),
    LOCKED(3, "Заблокирован");

    public final Integer statusCode;
    private final String description;

    public static Status fromInt(Integer x) {
        for (Status value : Status.values()) {
            if (value.statusCode.equals(x)) return value;
        }
        throw new IllegalStateException("Данный Status код проекта не зарегистрирован, требуется обновить список Status кодов.");
    }

    public static Status of(String description) {
        return Stream.of(values())
                .filter(status -> status.description.equals(description))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Не найден объект Status с описанием " + description));
    }

}
