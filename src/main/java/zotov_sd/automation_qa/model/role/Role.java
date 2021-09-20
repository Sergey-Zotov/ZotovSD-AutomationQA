package zotov_sd.automation_qa.model.role;

import lombok.Getter;
import lombok.Setter;
import org.testng.annotations.Test;
import zotov_sd.automation_qa.model.Creatable;
import zotov_sd.automation_qa.model.user.Entity;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Role extends Entity implements Creatable<Role> {

    private String name;
    private Integer position = positionRoles.get(positionRoles.lastIndexOf(positionRole));       //индекс роли, т.е. как она будет отображаться
    private Boolean assignable = false;     //Задача может быть назначена этой роли
    private Integer builtin = 0;   //не в курсе если честно, попробуй 0 использовать везде
    private Permissions permissions = new Permissions();  // права доступа
    private IssueVisibility issueVisibility = IssueVisibility.ALL;   //Видимость задачи
    private UsersVisibility usersVisibility = UsersVisibility.ALL;   //Видимость пользователей
    private TimeEntriesVisibility timeEntriesVisibility = TimeEntriesVisibility.ALL;  //Видимость трудозатрат
    private Boolean allRoesManaged = false;   // Управление участниками
    private Settings settings = new Settings();   //адачи

    private static int positionRole = 0;
    private static List<Integer> positionRoles = new ArrayList<>();

    public Role() {
        positionRoles.add(positionRole++);
    }

    @Override
    public Role create() {
        // TODO: Реализовать с помощью SQL-Запроса
        throw new UnsupportedOperationException();
    }


    @Test
    void sg(){
        Role role = new Role();
        Role role1 = new Role();
        Role role2 = new Role();
    }
}