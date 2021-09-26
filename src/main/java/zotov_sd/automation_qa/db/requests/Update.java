package zotov_sd.automation_qa.db.requests;


import zotov_sd.automation_qa.model.Entity;

public interface Update<T extends Entity> {

    void update(Integer id, T entity);

}
