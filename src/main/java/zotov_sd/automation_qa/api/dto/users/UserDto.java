package zotov_sd.automation_qa.api.dto.users;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

import static zotov_sd.automation_qa.utils.StringUtils.randomEmail;
import static zotov_sd.automation_qa.utils.StringUtils.randomEnglishString;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Integer id;

    private String login = "ZSD" + randomEnglishString(5);;

    @SerializedName("firstname")
    private String firstName = "ZSD" + randomEnglishString(5);

    @SerializedName("lastname")
    private String lastName = "ZSD" + randomEnglishString(10);

    private String mail = randomEmail();

    private String password = "1qaz@WSX" + randomEnglishString(5);

    private Boolean admin = false;

    @SerializedName("created_on")
    private LocalDateTime createdOn;

    @SerializedName("last_login_on")
    private LocalDateTime lastLoginOn;

    private Integer status;
}
