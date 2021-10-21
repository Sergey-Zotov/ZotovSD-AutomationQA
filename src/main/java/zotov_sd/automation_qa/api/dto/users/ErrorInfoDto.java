package zotov_sd.automation_qa.api.dto.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@AllArgsConstructor
public class ErrorInfoDto {

    List<String> errors;
}
