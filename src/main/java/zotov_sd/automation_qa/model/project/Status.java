package zotov_sd.automation_qa.model.project;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Status {
    OPEN(1),
    ARCHIVED(9),
    CLOSED(5);

    public final Integer status;
}
