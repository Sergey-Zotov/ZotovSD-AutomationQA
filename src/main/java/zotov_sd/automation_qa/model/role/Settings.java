package zotov_sd.automation_qa.model.role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zotov_sd.automation_qa.model.Creatable;
import zotov_sd.automation_qa.model.user.Entity;

@NoArgsConstructor
@Getter
@Setter
public class Settings extends Entity implements Creatable {

    private Status viewIssues = Status.NO_ACTIVE;
    private Status addIssues = Status.NO_ACTIVE;
    private Status editIssues = Status.NO_ACTIVE;
    private Status addIssueNotes = Status.NO_ACTIVE;
    private Status deleteIssues = Status.NO_ACTIVE;

    @AllArgsConstructor
    private enum Status {
        NO_ACTIVE(0),
        ACTIVE(1);

        public final int statusCode;
    }

    @Override
    public Entity create() {
        // TODO: Реализовать с помощью SQL-Запроса
        throw new UnsupportedOperationException();
    }
}
