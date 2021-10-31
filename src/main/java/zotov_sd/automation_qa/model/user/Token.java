package zotov_sd.automation_qa.model.user;

import io.qameta.allure.Step;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import zotov_sd.automation_qa.db.requests.model_request.TokenRequests;
import zotov_sd.automation_qa.model.Creatable;
import zotov_sd.automation_qa.model.CreatableEntity;

import static zotov_sd.automation_qa.utils.StringUtils.randomHexString;

@Setter
@Getter
@Accessors(chain = true)
public class Token extends CreatableEntity implements Creatable<Token> {

    private Integer userId;
    private TokenType action = TokenType.API;
    private String value = randomHexString(40);

    public Token(User user) {
        this.userId = user.getId();
        user.getTokens().add(this);
    }

    public enum TokenType {
        SESSION,
        API,
        FEEDS
    }

    @Override
    @Step("Создан Token")
    public Token create() {
        new TokenRequests().create(this);
        return this;
    }
}
