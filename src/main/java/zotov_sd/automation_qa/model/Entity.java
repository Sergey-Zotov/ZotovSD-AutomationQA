package zotov_sd.automation_qa.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain = true)
public abstract class Entity {

    protected Integer id;

}
