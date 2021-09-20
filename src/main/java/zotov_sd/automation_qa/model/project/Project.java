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
    private String description = "ZSD description";  // описание
    private String homepage = "ZSD.com";  //стартовая страница
    private Boolean isPublic = false;  //общедоступный
    private Integer parentId;  // id родительского проекта
    private String identifier; // уникальный индификатор
    private Status status = Status.OPEN;
    private Integer lft; // значение по умолчанию
    private Integer rgt; // значение по умолчанию
    private Boolean inheritMembers = false; // наследовать участников
    private Integer defaultVersionId = null;  // версия проекта, если есть
    private Integer defaultAssignedToId = null; // id участника на которого назначена задача, если есть.

    @Override
    public Project create() {
        // TODO: Реализовать с помощью SQL-Запроса
        throw new UnsupportedOperationException();
    }
}
