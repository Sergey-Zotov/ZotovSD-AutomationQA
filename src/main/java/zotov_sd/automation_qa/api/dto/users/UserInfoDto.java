package zotov_sd.automation_qa.api.dto.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
public class UserInfoDto {

    private UserDto user;

}