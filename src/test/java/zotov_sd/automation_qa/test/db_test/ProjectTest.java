package zotov_sd.automation_qa.test.db_test;

import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;
import zotov_sd.automation_qa.model.project.Project;
import zotov_sd.automation_qa.model.project.StatusProject;

public class ProjectTest {



    @Test(description = "Создание проекта, и получение его из БД по id")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Зотов С.Д.")
    void projectRequestReadTest() {
        Project project = new Project().create();
        Project projectRead = new Project().read(project.getId());
    }

    @Test(description = "Создание, обновление и удаление проекта")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Зотов С.Д.")
    void projectRequestTest() {
        Project project = new Project();
        project.create();
        project.setName("test1");
        project.setDescription("test2");
        project.setStatus(StatusProject.CLOSED);
        project.update();
        project.delete();
    }
}
