package zotov_sd.automation_qa.model.user;

import io.qameta.allure.Step;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import zotov_sd.automation_qa.db.requests.model_request.MemberRequest;
import zotov_sd.automation_qa.db.requests.model_request.MemberRoleRequest;
import zotov_sd.automation_qa.db.requests.model_request.UserRequests;
import zotov_sd.automation_qa.model.Readable;
import zotov_sd.automation_qa.model.*;
import zotov_sd.automation_qa.model.project.Project;
import zotov_sd.automation_qa.model.role.Role;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.codec.digest.DigestUtils.sha1Hex;
import static zotov_sd.automation_qa.utils.StringUtils.randomEnglishString;
import static zotov_sd.automation_qa.utils.StringUtils.randomHexString;


@NoArgsConstructor
@Setter
@Getter
@Accessors(chain = true)
public class User extends CreatableEntity implements Creatable<User>, Readable<User>, Updateable<User>, Deleteable<User> {

    private String login = "ZSD" + randomEnglishString(10);
    private String password = "1qaz@WSX";
    private String salt = randomHexString(32);
    private String hashedPassword = hashPassword();
    private String firstName = "ZSD" + randomEnglishString(10);
    private String lastName = "ZSD" + randomEnglishString(10);
    private Boolean isAdmin = false;
    private Status status = Status.ACTIVE;
    private LocalDateTime lastLoginOn;
    private Language language = Language.RUSSIAN;
    private String authSourceId;
    private String type = "User";
    private String identityUrl;
    private MailNotification mailNotification = MailNotification.NONE;
    private Boolean mustChangePassword = false;
    private LocalDateTime passwordChangedOn;
    private List<Token> tokens = new ArrayList<>();
    private List<Email> emails = new ArrayList<>();
    private Integer membersId;

    /**
     * При изменении пароля будет автоматически пересоздаваться hashedPassword.
     */
    public User setPassword(String password) {
        this.password = password;
        this.hashedPassword = hashPassword();
        return this;
    }

    /**
     * Сщздает hashedPassword из salt и пароля.
     */
    private String hashPassword() {
        return sha1Hex(salt + sha1Hex(password));
    }

    @Override
    @Step("Создан пользователь в БД")
    public User create() {
        new UserRequests().create(this);
        tokens.forEach(t -> t.setUserId(id));
        tokens.forEach(Token::create);
        emails.forEach(e -> e.setUserId(id));
        emails.forEach(Email::create);
        return this;
    }

    @Step("Удален пользователь в БД")
    public User delete() {
        new UserRequests().delete(this.id);
        return this;
    }

    @Step("Обнавлен пользователь в БД")
    @Override
    public User update() {
        new UserRequests().update(this.id, this);
        return this;
    }

    @Step("Пользователю добавлен в проект с ролями")
    public void addProject(Project project, List<Role> roles) {
        MemberRequest.create(project, this);
        roles.forEach(role -> MemberRoleRequest.create(role, this));
    }

    @Override
    @Step("Получен пользователь из БД")
    public User read(Integer id) {
        return new UserRequests().read(id);
    }

    @Step("Получение созданого пользователя из БД по логину")
    public Integer readUserIdFromTheDatabaseByLogin() {
        return new UserRequests().read(this.getLogin());
    }
}
