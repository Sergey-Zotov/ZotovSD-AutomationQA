package zotov_sd.automation_qa.model.project;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zotov_sd.automation_qa.model.Creatable;
import zotov_sd.automation_qa.model.CreatableEntity;

import static zotov_sd.automation_qa.utils.StringUtils.randomEnglishString;

@NoArgsConstructor
@Setter
@Getter
public class Project extends CreatableEntity implements Creatable<Project> {

    private String name = "ZSD" + randomEnglishString(5);
    private String description = "ZSD" + randomEnglishString(5);
    private String homepage = "ZSD.com";
    private Boolean isPublic = false;
    private Integer parentId;
    private String identifier;
    private Status status = Status.OPEN;
    private Integer lft = 1;
    private Integer rgt = 2;
    private Boolean inheritMembers = false;
    private Integer defaultVersionId = null;
    private Integer defaultAssignedToId = null;

    @Override
    public Project create() {
        // TODO: Реализовать с помощью SQL-Запроса
        throw new UnsupportedOperationException();
    }
}
