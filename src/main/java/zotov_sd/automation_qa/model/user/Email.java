package zotov_sd.automation_qa.model.user;

import io.qameta.allure.Step;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import zotov_sd.automation_qa.db.requests.model_request.EmailRequests;
import zotov_sd.automation_qa.model.Creatable;
import zotov_sd.automation_qa.model.CreatableEntity;

import static zotov_sd.automation_qa.utils.StringUtils.randomEmail;

@Setter
@Getter
@Accessors(chain = true)
@NoArgsConstructor
public class Email extends CreatableEntity implements Creatable<Email> {

    private Integer userId;
    private String address = randomEmail();
    private Boolean isDefault = true;
    private Boolean notify = true;

    public Email(User user) {
        this.userId = user.getId();
        user.getEmails().add(this);
    }

    @Override
    @Step("Создана Email в БД")
    public Email create() {
        new EmailRequests().create(this);
        return this;
    }

}
