package zotov_sd.automation_qa.db.requests.model_request;

import zotov_sd.automation_qa.db.connection.PostgresConnection;
import zotov_sd.automation_qa.db.requests.Create;
import zotov_sd.automation_qa.db.requests.Delete;
import zotov_sd.automation_qa.db.requests.Update;
import zotov_sd.automation_qa.model.role.Permissions;
import zotov_sd.automation_qa.model.role.Role;

import java.util.List;

public class RoleRequest implements Create<Role>, Delete, Update<Role> {
    @Override
    public void create(Role role) {
        String query = "INSERT INTO roles\n" +
                "(id, \"name\", \"position\", assignable, builtin," +
                " permissions, issues_visibility, users_visibility," +
                " time_entries_visibility, all_roles_managed, settings)\n" +
                "VALUES(DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id;\n";
        Integer roleId = (Integer) PostgresConnection.INSTANCE.executeQuery(
                query,
                role.getName(),
                role.getPosition(),
                role.getAssignable(),
                role.getBuiltin(),
                fromPermissions(role.getPermissions()),
                role.getIssueVisibility().value,
                role.getUsersVisibility().value,
                role.getTimeEntriesVisibility().value,
                role.getAllRolesManaged(),
                role.getSettings().toString()
        ).get(0).get("id");
        role.setId(roleId);
    }

    private String fromPermissions(List<Permissions> per) {
        StringBuilder sb = new StringBuilder();
        sb.append("---\n");
        for (Permissions permissions : per) {
            sb.append("- :").append(permissions.toString().toLowerCase()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public void delete(Integer id) {
        String query = "DELETE FROM roles WHERE id = ?";
        PostgresConnection.INSTANCE.executeQuery(query, id);
    }

    @Override
    public void update(Integer id, Role role) {
        String query = "UPDATE public.roles\n" +
                "SET \"name\"=?, \"position\"=?, assignable=?, builtin=?," +
                " permissions=?, issues_visibility=?, users_visibility=?," +
                " time_entries_visibility=?, all_roles_managed=?," +
                " settings=?\n" +
                "WHERE id=?;";
        PostgresConnection.INSTANCE.executeQuery(
                query,
                role.getName(),
                role.getPosition(),
                role.getAssignable(),
                role.getBuiltin(),
                fromPermissions(role.getPermissions()),
                role.getIssueVisibility().value,
                role.getUsersVisibility().value,
                role.getTimeEntriesVisibility().value,
                role.getAllRolesManaged(),
                role.getSettings().toString(),
                id
        );
    }
}
