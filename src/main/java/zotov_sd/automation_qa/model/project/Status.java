package zotov_sd.automation_qa.model.project;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Status {
    OPEN(1),
    ARCHIVED(9),
    CLOSED(5);

    public final Integer status;

    public static Status fromInteger(Integer x) {
        for (Status value : Status.values()) {
            if (value.status.equals(x)) return value;
        }
        throw new IllegalStateException("Данный Status код проекта не зарегистрирован, требуется обновить список Status кодов.");
    }
}
