package zotov_sd.automation_qa.db.requests;

import zotov_sd.automation_qa.db.connection.PostgresConnection;
import zotov_sd.automation_qa.model.project.Project;
import zotov_sd.automation_qa.model.project.Status;

import java.util.List;
import java.util.Map;

public class ProjectRequests extends BaseRequests implements Create<Project>, Read<Project>, Update<Project>, Delete {
    @Override
    public void create(Project project) {
        String query = "INSERT INTO public.projects\n" +
                "(id, \"name\", description, homepage, is_public, parent_id," +
                " created_on, updated_on, identifier, status, lft, rgt," +
                " inherit_members, default_version_id, default_assigned_to_id)\n" +
                "VALUES(DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id;\n";
        Integer projectId = (Integer) PostgresConnection.INSTANCE.executeQuery(
                query,
                project.getName(),
                project.getDescription(),
                project.getHomepage(),
                project.getIsPublic(),
                project.getParentId(),
                project.getCreatedOn(),
                project.getUpdatedOn(),
                project.getIdentifier(),
                project.getStatus().status,
                project.getLft(),
                project.getRgt(),
                project.getInheritMembers(),
                project.getDefaultVersionId(),
                project.getDefaultAssignedToId()
        ).get(0).get("id");
        project.setId(projectId);
    }

    @Override
    public void delete(Integer id) {
        String query = "DELETE FROM public.projects\n" +
                "WHERE id = ?;\n";
        PostgresConnection.INSTANCE.executeQuery(query, id);
    }

    @Override
    public void update(Integer id, Project project) {
        String query = "UPDATE public.projects\n" +
                "SET \"name\"=?, description=?, homepage=?," +
                " is_public=?, parent_id=?, created_on=?," +
                " updated_on=?, identifier=?, status=?, lft=?, rgt=?," +
                " inherit_members=?, default_version_id=?, default_assigned_to_id=?\n" +
                "WHERE id=?;";
        PostgresConnection.INSTANCE.executeQuery(
                query,
                project.getName(),
                project.getDescription(),
                project.getHomepage(),
                project.getIsPublic(),
                project.getParentId(),
                project.getCreatedOn(),
                project.getUpdatedOn(),
                project.getIdentifier(),
                project.getStatus().status,
                project.getLft(),
                project.getRgt(),
                project.getInheritMembers(),
                project.getDefaultVersionId(),
                project.getDefaultAssignedToId(),
                id
        );
    }

    @Override
    public Project read(Integer id) {
        String query = "SELECT id, \"name\", description, homepage, is_public, parent_id, created_on, updated_on, identifier, status, lft, rgt, inherit_members, default_version_id, default_assigned_to_id\n" +
                "FROM public.projects\n" +
                "WHERE id=?";
        List<Map<String, Object>> result = PostgresConnection.INSTANCE.executeQuery(query, id);
        return from(result.get(0));
    }

    private Project from(Map<String, Object> data) {
        return (Project) new Project()
                .setName((String) data.get("name"))
                .setDescription((String) data.get("description"))
                .setHomepage((String) data.get("homepage"))
                .setIsPublic((Boolean) data.get("is_public"))
                .setParentId((Integer) data.get("parent_id"))
                .setIdentifier((String) data.get("identifier"))
                .setStatus(Status.fromInteger((Integer) data.get("status")))
                .setLft((Integer) data.get("lft"))
                .setRgt((Integer) data.get("rgt"))
                .setInheritMembers((Boolean) data.get("inherit_members"))
                .setDefaultVersionId((Integer) data.get("default_version_id"))
                .setDefaultAssignedToId((Integer) data.get("default_assigned_to_id"))
                .setCreatedOn(toLocalDate(data.get("created_on")))
                .setUpdatedOn(toLocalDate(data.get("updated_on")))
                .setId((Integer) data.get("id"));
    }
}
