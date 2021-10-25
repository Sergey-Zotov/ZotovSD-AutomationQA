package zotov_sd.automation_qa.model.project;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import zotov_sd.automation_qa.db.requests.model_request.MemberRequest;
import zotov_sd.automation_qa.db.requests.model_request.MemberRoleRequest;
import zotov_sd.automation_qa.db.requests.model_request.ProjectRequests;
import zotov_sd.automation_qa.model.Readable;
import zotov_sd.automation_qa.model.*;
import zotov_sd.automation_qa.model.role.Role;
import zotov_sd.automation_qa.model.user.User;

import java.util.List;

import static zotov_sd.automation_qa.utils.StringUtils.randomEnglishString;

@NoArgsConstructor
@Setter
@Getter
@Accessors(chain = true)
public class Project extends CreatableEntity implements Creatable<Project>, Deleteable<Project>, Readable<Project>, Updateable<Project> {

    private String name = "ZSD" + randomEnglishString(15);
    private String description = "ZSD" + randomEnglishString(15);
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
        new ProjectRequests().create(this);
        return this;
    }

    @Override
    public Project delete() {
        new ProjectRequests().delete(this.id);
        return this;
    }

    @Override
    public Project update() {
        new ProjectRequests().update(this.id, this);
        return this;
    }

    @Override
    public Project read(Integer id) {
        return new ProjectRequests().read(id);
    }

    public void addUserWithRoles(User user, List<Role> roles) {
        MemberRequest.create(this, user);
        roles.forEach(role -> MemberRoleRequest.create(role, user));
    }
}
