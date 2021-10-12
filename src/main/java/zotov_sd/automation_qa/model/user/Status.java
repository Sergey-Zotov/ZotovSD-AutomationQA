package zotov_sd.automation_qa.model.user;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Status {
    UNREGISTERED(0),
    ACTIVE(1),
    UNACCEPTED(2),
    LOCKED(3);

    public final Integer statusCode;

    public static Status fromInt(Integer x) {
        for (Status value : Status.values()) {
            if (value.statusCode.equals(x)) return value;
        }
        throw new IllegalStateException("Данный Status код проекта не зарегистрирован, требуется обновить список Status кодов.");
    }

}
