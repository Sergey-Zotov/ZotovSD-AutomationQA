package zotov_sd.automation_qa.model.role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zotov_sd.automation_qa.model.Creatable;
import zotov_sd.automation_qa.model.Entity;

import java.util.ArrayList;
import java.util.List;

import static zotov_sd.automation_qa.utils.StringUtils.randomEnglishString;

@NoArgsConstructor
@Getter
@Setter
public class Role extends Entity implements Creatable<Role> {

    private String name = "ZSD" + randomEnglishString(5);
    private Integer position = 1;
    private Boolean assignable = false;
    private Integer builtin = 0;
    private List<Permissions> permissions = new ArrayList<>();
    private IssueVisibility issueVisibility = IssueVisibility.ALL;
    private UsersVisibility usersVisibility = UsersVisibility.ALL;
    private TimeEntriesVisibility timeEntriesVisibility = TimeEntriesVisibility.ALL;
    private Boolean allRolesManaged = false;
    private Settings settings = new Settings();

    @Override
    public Role create() {
        // TODO: Реализовать с помощью SQL-Запроса
        throw new UnsupportedOperationException();
    }
}
