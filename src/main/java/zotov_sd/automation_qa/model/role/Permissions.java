package zotov_sd.automation_qa.model.role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zotov_sd.automation_qa.model.Creatable;
import zotov_sd.automation_qa.model.Entity;

@NoArgsConstructor
@Getter
@Setter
public class Permissions extends Entity implements Creatable<Permissions> {

    private Boolean addProject = false;
    private Boolean editProject = false;
    private Boolean closeProject = false;
    private Boolean selectProjectModules = false;
    private Boolean manageMembers = false;
    private Boolean manageVersions = false;
    private Boolean addSubProjects = false;
    private Boolean managePublicQueries = false;
    private Boolean saveQueries = false;
    private Boolean viewMessages = false;
    private Boolean addMessages = false;
    private Boolean editMessages = false;
    private Boolean editOwnMessages = false;
    private Boolean deleteMessages = false;
    private Boolean deleteOwnMessages = false;
    private Boolean manageBoards = false;
    private Boolean viewCalendar = false;
    private Boolean viewDocuments = false;
    private Boolean addDocuments = false;
    private Boolean editDocuments = false;
    private Boolean deleteDocuments = false;
    private Boolean viewFiles = false;
    private Boolean manageFiles = false;
    private Boolean viewGantt = false;
    private Boolean viewIssues = false;
    private Boolean addIssues = false;
    private Boolean editIssues = false;
    private Boolean editOwnIssues = false;
    private Boolean copyIssues = false;
    private Boolean manageIssueRelations = false;
    private Boolean manageSubtasks = false;
    private Boolean setIssuesPrivate = false;
    private Boolean setOwnIssuesPrivate = false;
    private Boolean addIssueNotes = false;
    private Boolean editIssueNotes = false;
    private Boolean editOwnIssueNotes = false;
    private Boolean viewPrivateNotes = false;
    private Boolean setNotesPrivate = false;
    private Boolean deleteIssues = false;
    private Boolean viewIssueWatchers = false;
    private Boolean addIssueWatchers = false;
    private Boolean deleteIssueWatchers = false;
    private Boolean importIssues = false;
    private Boolean manageCategories = false;
    private Boolean viewNews = false;
    private Boolean manageNews = false;
    private Boolean commentNews = false;
    private Boolean viewChangesets = false;
    private Boolean browseRepository = false;
    private Boolean commitAccess = false;
    private Boolean manageRelated_issues = false;
    private Boolean manageRepository = false;
    private Boolean viewTimeEntries = false;
    private Boolean logTime = false;
    private Boolean editTimeEntries = false;
    private Boolean editOwnTimeEntries = false;
    private Boolean manageProjectActivities = false;
    private Boolean logTimeForOtherUsers = false;
    private Boolean importTimeEntries = false;
    private Boolean viewWikiPages = false;
    private Boolean viewWikiEdits = false;
    private Boolean exportWikiPages = false;
    private Boolean editWikiPages = false;
    private Boolean renameWikiPages = false;
    private Boolean deleteWikiPages = false;
    private Boolean deleteWikiPagesAttachments = false;
    private Boolean protectWikiPages = false;
    private Boolean manageWiki = false;

    @Override
    public Permissions create() {
        // TODO: Реализовать с помощью SQL-Запроса
        throw new UnsupportedOperationException();
    }
}
