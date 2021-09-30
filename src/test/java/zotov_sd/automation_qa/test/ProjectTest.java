package zotov_sd.automation_qa.test;

import org.testng.annotations.Test;
import zotov_sd.automation_qa.db.requests.ProjectRequests;
import zotov_sd.automation_qa.model.project.Project;
import zotov_sd.automation_qa.model.project.Status;

public class ProjectTest {

    @Test
    void projectRequestReadTest() {

        Project project = new ProjectRequests().read(4447);
    }

    @Test
    void projectRequestTest() {
        Project project = new Project();
        project.create();
        project.setName("test1");
        project.setDescription("test2");
        project.setStatus(Status.CLOSED);
        project.update();
        project.delete();
    }
}
