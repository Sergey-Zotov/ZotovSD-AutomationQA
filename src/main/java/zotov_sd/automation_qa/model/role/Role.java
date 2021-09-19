package zotov_sd.automation_qa.model.role;

import zotov_sd.automation_qa.model.user.Entity;

public class Role extends Entity {

    private String name;
    // private position;
    private Boolean assignable = false;     //Задача может быть назначена этой роли
    // private builtin;
    private Permissions permissions = new Permissions();  // права доступа
    private IssueVisibility issueVisibility = IssueVisibility.ALL;   //Видимость задачи
    private UsersVisibility usersVisibility = UsersVisibility.ALL;   //Видимость пользователей
    private TimeEntriesVisibility timeEntriesVisibility = TimeEntriesVisibility.ALL;  //Видимость трудозатрат
    private Boolean allRoesManaged = false;   // Управление участниками
    private Settings settings = new Settings();   //адачи

}
