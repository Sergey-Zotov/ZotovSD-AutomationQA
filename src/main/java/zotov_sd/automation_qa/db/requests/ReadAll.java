package zotov_sd.automation_qa.db.requests;

import zotov_sd.automation_qa.model.Entity;

import java.util.List;

public interface ReadAll<T extends Entity> {

    List<T> readAll();

}
