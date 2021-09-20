package zotov_sd.automation_qa.model.role;

import lombok.Getter;
import lombok.Setter;
import zotov_sd.automation_qa.model.Creatable;
import zotov_sd.automation_qa.model.Entity;

import static zotov_sd.automation_qa.utils.StringUtils.randomEnglishString;

@Getter
@Setter
public class Role extends Entity implements Creatable<Role> {

    private String name = "ZSD" + randomEnglishString(5);
    private Integer position = positions;       //индекс роли, т.е. как она будет отображаться
    private Boolean assignable = false;     //Задача может быть назначена этой роли
    private Integer builtin = 0;   //не в курсе если честно, попробуй 0 использовать везде
    private Permissions permissions = new Permissions();  // права доступа
    private IssueVisibility issueVisibility = IssueVisibility.ALL;   //Видимость задачи
    private UsersVisibility usersVisibility = UsersVisibility.ALL;   //Видимость пользователей
    private TimeEntriesVisibility timeEntriesVisibility = TimeEntriesVisibility.ALL;  //Видимость трудозатрат
    private Boolean allRoesManaged = false;   // Управление участниками
    private Settings settings = new Settings();   //адачи

    private static int positions = 0;

    public Role() {

        positions++;
    }

    @Override
    public Role create() {
        // TODO: Реализовать с помощью SQL-Запроса
        throw new UnsupportedOperationException();
    }
}
