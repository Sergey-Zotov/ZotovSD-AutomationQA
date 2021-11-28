package zotov_sd.automation_qa.model.project;

import lombok.AllArgsConstructor;

import java.util.stream.Stream;

@AllArgsConstructor
public enum StatusProject {
    OPEN(1, "Публичный"),
    ARCHIVED(9, "В архиве"),
    CLOSED(5, "Приватный");

    public final Integer status;
    private final String description;

    public static StatusProject fromInteger(Integer x) {
        for (StatusProject value : StatusProject.values()) {
            if (value.status.equals(x)) return value;
        }
        throw new IllegalStateException("Данный Status код проекта не зарегистрирован, требуется обновить список Status кодов.");
    }

    public static StatusProject of(String description) {
        return Stream.of(values())
                .filter(status -> status.description.equals(description))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Не найден объект Status с описанием " + description));
    }
}
