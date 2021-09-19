package zotov_sd.automation_qa.model.role;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TimeEntriesVisibility {

    ALL("Все трудозатраты"),
    OWN("Только собственные трудозатраты");

    public final String value;
}
