package zotov_sd.automation_qa.model.role;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum IssueVisibility {

    ALL("Все задачи"),
    DEFAULT("Только общие задачи"),
    OWN("Задачи созданные или назначенные пользователю");

    public final String value;
}
