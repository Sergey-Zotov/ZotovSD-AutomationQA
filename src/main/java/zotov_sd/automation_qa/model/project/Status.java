package zotov_sd.automation_qa.model.project;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Status {
    OPEN(1),
    ARCHIVED(9),
    CLOSED(5);

    public final Integer status;

    public static Status fromInteger(int x) {
        switch (x) {
            case 1:
                return OPEN;
            case 5:
                return ARCHIVED;
            case 9:
                return CLOSED;
        }
        return null;
    }
}
