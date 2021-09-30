package zotov_sd.automation_qa.model.role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import zotov_sd.automation_qa.db.requests.model_request.RoleRequest;
import zotov_sd.automation_qa.model.Creatable;
import zotov_sd.automation_qa.model.Deleteable;
import zotov_sd.automation_qa.model.Entity;
import zotov_sd.automation_qa.model.Updateable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static zotov_sd.automation_qa.utils.StringUtils.randomEnglishString;

@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class Role extends Entity implements Creatable<Role>, Deleteable<Role>, Updateable<Role> {

    private String name = "ZSD" + randomEnglishString(5);
    private Integer position = new Random().nextInt(1000);
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
        new RoleRequest().create(this);
        return this;
    }

    @Override
    public Role delete() {
        new RoleRequest().delete(this.id);
        return this;
    }

    @Override
    public Role update() {
        new RoleRequest().update(this.id, this);
        return this;
    }
}
