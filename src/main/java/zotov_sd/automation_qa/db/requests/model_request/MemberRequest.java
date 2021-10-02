package zotov_sd.automation_qa.db.requests.model_request;

import zotov_sd.automation_qa.db.connection.PostgresConnection;
import zotov_sd.automation_qa.model.project.Project;
import zotov_sd.automation_qa.model.user.User;

import java.time.LocalDateTime;

public class MemberRequest {

    public static void create(Project project, User user) {
        String query = "INSERT INTO public.members\n" +
                "(id, user_id, project_id, created_on, mail_notification)\n" +
                "VALUES(DEFAULT, ?, ?, ?, ?) RETURNING id;\n";
        Integer memberId = (Integer) PostgresConnection.INSTANCE.executeQuery(
                query,
                user.getId(),
                project.getId(),
                LocalDateTime.now(),
                !user.getEmails().isEmpty()
        ).get(0).get("id");
        user.setMembersId(memberId);
    }

    public static void delete(Integer id) {
        String query = "DELETE FROM public.members\n" +
                "WHERE id=?;\n";
        PostgresConnection.INSTANCE.executeQuery(query, id);
    }
}
