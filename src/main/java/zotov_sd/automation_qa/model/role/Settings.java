package zotov_sd.automation_qa.model.role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zotov_sd.automation_qa.model.Creatable;
import zotov_sd.automation_qa.model.Entity;

@NoArgsConstructor
@Getter
@Setter
public class Settings extends Entity implements Creatable {

    private Status viewIssues = Status.NO_ACTIVE;
    private Status addIssues = Status.ACTIVE;
    private Status editIssues = Status.NO_ACTIVE;
    private Status addIssueNotes = Status.ACTIVE;
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

    @Override
    public String toString() {
        return "--- !ruby/hash:ActiveSupport::HashWithIndifferentAccess\n" +
                "permissions_all_trackers: !ruby/hash:ActiveSupport::HashWithIndifferentAccess" +
                "  view_issues: '" + viewIssues.statusCode + "'\n" +
                "  add_issues: '" + addIssues.statusCode + "'\n" +
                "  edit_issues: '" + editIssues.statusCode + "'\n" +
                "  add_issue_notes: '" + addIssueNotes.statusCode + "'\n" +
                "  delete_issues: '" + deleteIssues.statusCode + "'\n" +
                "permissions_tracker_ids: !ruby/hash:ActiveSupport::HashWithIndifferentAccess\n" +
                "  view_issues: []\n" +
                "  add_issues: []\n" +
                "  edit_issues: []\n" +
                "  add_issue_notes: []\n" +
                "  delete_issues: []";
    }
}
