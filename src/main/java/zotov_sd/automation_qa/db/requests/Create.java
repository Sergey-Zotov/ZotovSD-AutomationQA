package zotov_sd.automation_qa.db.requests;

import zotov_sd.automation_qa.model.Entity;

public interface Create<T extends Entity> {

    void create(T entity);

}
