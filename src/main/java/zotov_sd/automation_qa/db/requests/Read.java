package zotov_sd.automation_qa.db.requests;


import zotov_sd.automation_qa.model.Entity;

public interface Read<T extends Entity> {

    T read(Integer id);

}
