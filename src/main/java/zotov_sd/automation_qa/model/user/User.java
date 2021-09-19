package zotov_sd.automation_qa.model.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zotov_sd.automation_qa.model.Creatable;
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
public class User extends CreatableEntity implements Creatable<User> {

    private String login = "AutoLogin" + randomEnglishString(10);
    private String password = "1qaz@WSX";
    private String salt = randomHexString(32);
    private String hashedPassword = hashPassword();
    private String firstName = "AutoF" + randomEnglishString(10);
    private String lastName = "AutoL" + randomEnglishString(10);
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

    public void setPassword(String password) {
        this.password = password;
        this.hashedPassword = hashPassword();
    }

    private String hashPassword() {
        return sha1Hex(salt + sha1Hex(password));
    }

    @Override
    public User create() {
        // TODO: Реализовать с помощью SQL-Запроса
        tokens.forEach(Token::create);
        emails.forEach(Email::create);
        throw new UnsupportedOperationException();
    }

    public void addProject(Project project, List<Role> roles) {
        // TODO: Реализовать с помощью SQL-запроса
    }

}
