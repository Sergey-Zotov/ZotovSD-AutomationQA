package zotov_sd.automation_qa.db.requests.model_request;

import zotov_sd.automation_qa.db.connection.PostgresConnection;
import zotov_sd.automation_qa.model.role.Role;
import zotov_sd.automation_qa.model.user.User;

public class MemberRoleRequest {

    static public void create(Role role, User user) {
        String query = "INSERT INTO public.member_roles\n" +
                "(id, member_id, role_id, inherited_from)\n" +
                "VALUES(Default, ?, ?, ?);";
        PostgresConnection.INSTANCE.executeQuery(
                query,
                user.getMembersId(),
                role.getId(),
                null
        );
    }

    static public void delete(Integer id) {
        String query = "DELETE FROM public.member_roles\n" +
                "WHERE id=?;";
        PostgresConnection.INSTANCE.executeQuery(query, id);
    }
}
