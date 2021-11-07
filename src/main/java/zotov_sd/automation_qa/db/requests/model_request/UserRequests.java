package zotov_sd.automation_qa.db.requests.model_request;

import zotov_sd.automation_qa.db.connection.PostgresConnection;
import zotov_sd.automation_qa.db.requests.BaseRequests;
import zotov_sd.automation_qa.db.requests.Create;
import zotov_sd.automation_qa.db.requests.Delete;
import zotov_sd.automation_qa.db.requests.Update;
import zotov_sd.automation_qa.model.user.Status;
import zotov_sd.automation_qa.model.user.User;

import java.util.List;
import java.util.Map;

public class UserRequests extends BaseRequests implements Create<User>, Update<User>, Delete {

    @Override
    public void create(User user) {
        String query = "INSERT INTO public.users\n" +
                "(id, login, hashed_password, firstname, lastname, " +
                "\"admin\", status, last_login_on, \"language\", auth_source_id, " +
                "created_on, updated_on, \"type\", identity_url, mail_notification, " +
                "salt, must_change_passwd, passwd_changed_on)\n" +
                "VALUES(DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id;\n";
        Integer userId = (Integer) PostgresConnection.INSTANCE.executeQuery(
                query,
                user.getLogin(),
                user.getHashedPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getIsAdmin(),
                user.getStatus().statusCode,
                user.getLastLoginOn(),
                user.getLanguage().languageCode,
                user.getAuthSourceId(),
                user.getCreatedOn(),
                user.getUpdatedOn(),
                user.getType(),
                user.getIdentityUrl(),
                user.getMailNotification().name().toLowerCase(),
                user.getSalt(),
                user.getMustChangePassword(),
                user.getPasswordChangedOn()
        ).get(0).get("id");
        user.setId(userId);
    }

    @Override
    public void delete(Integer id) {
        String query = "DELETE FROM public.users WHERE id = ?";
        PostgresConnection.INSTANCE.executeQuery(query, id);
    }

    @Override
    public void update(Integer id, User user) {
        String query = "UPDATE public.users\n" +
                "SET login=?, hashed_password=?, firstname=?, lastname=?, \"admin\"=?, " +
                "status=?, last_login_on=?, \"language\"=?, auth_source_id=?, created_on=?, " +
                "updated_on=?, \"type\"=?, identity_url=?, mail_notification=?, salt=?, " +
                "must_change_passwd=?, passwd_changed_on=? \n" +
                "WHERE id=?;\n";
        PostgresConnection.INSTANCE.executeQuery(
                query,
                user.getLogin(),
                user.getHashedPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getIsAdmin(),
                user.getStatus().statusCode,
                user.getLastLoginOn(),
                user.getLanguage().languageCode,
                user.getAuthSourceId(),
                user.getCreatedOn(),
                user.getUpdatedOn(),
                user.getType(),
                user.getIdentityUrl(),
                user.getMailNotification().name().toLowerCase(),
                user.getSalt(),
                user.getMustChangePassword(),
                user.getPasswordChangedOn(),
                id
        );
    }

    public User read(Integer id) {
        try {
            String query = "SELECT id, login, firstname, lastname, status, created_on\n" +
                    "FROM public.users\n" +
                    "WHERE id=?;\n";
            List<Map<String, Object>> result = PostgresConnection.INSTANCE.executeQuery(query, id);
            User user = from(result.get(0));
            new EmailRequests().readAll(user);
            new TokenRequests().readAll(user);
            return user;
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    private User from(Map<String, Object> data) {
        return (User) new User()
                .setLogin((String) data.get("login"))
                .setFirstName((String) data.get("firstname"))
                .setLastName((String) data.get("lastname"))
                .setStatus(Status.fromInt((Integer) data.get("status")))
                .setCreatedOn(toLocalDate(data.get("created_on")))
                .setId((Integer) data.get("id"));
    }

    public Integer read(String login) {
        try {
            String query = "SELECT id, login, firstname, lastname, status, created_on\n" +
                    "FROM public.users\n" +
                    "WHERE login=?;\n";
            List<Map<String, Object>> result = PostgresConnection.INSTANCE.executeQuery(query, login);
            User user = from(result.get(0));
            return user.getId();
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalStateException("Пользователь " + login + " в базе данных не найден.");
        }
    }
}
