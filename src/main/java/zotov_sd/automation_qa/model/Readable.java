package zotov_sd.automation_qa.model;

public interface Readable<T extends Entity> {

    T read(Integer id);

}
