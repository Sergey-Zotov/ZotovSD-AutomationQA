package zotov_sd.automation_qa.model;

import zotov_sd.automation_qa.model.user.Entity;

public interface Creatable<T extends Entity> {

    T create();

}
