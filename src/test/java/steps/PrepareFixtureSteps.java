package steps;

import cucumber.api.java.ru.Дано;
import cucumber.api.java.ru.И;
import io.cucumber.datatable.DataTable;
import zotov_sd.automation_qa.context.Context;
import zotov_sd.automation_qa.cucumber.validators.UserParametersValidator;
import zotov_sd.automation_qa.model.user.Email;
import zotov_sd.automation_qa.model.user.MailNotification;
import zotov_sd.automation_qa.model.user.Status;
import zotov_sd.automation_qa.model.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PrepareFixtureSteps {

    @И("Имеется список E-Mail адресов \"(.+)\":")
    public void createEmails(String emailsStashId, DataTable dataTable) {
        // TODO: EmailValidator

        List<Map<String, String>> maps = dataTable.asMaps();
        List<Email> emails = new ArrayList<>();

        maps.forEach(map -> {
            String address = map.get("Адрес");
            Boolean isDefault = Boolean.parseBoolean(map.get("По умолчанию"));
            Boolean notify = Boolean.parseBoolean(map.get("Уведомления"));
            Email email = new Email()
                    .setAddress(address)
                    .setIsDefault(isDefault)
                    .setNotify(notify);
            emails.add(email);
        });

        Context.getStash().put(emailsStashId, emails);
    }

    @Дано("В системе есть пользователь \"(.+)\" с параметрами:")
    public void createAdminUser(String userStashId, Map<String, String> parameters) {
        UserParametersValidator.validateUserParameters(parameters.keySet());
        User user = new User();
        if (parameters.containsKey("Администратор")) {
            Boolean isAdmin = Boolean.parseBoolean(parameters.get("Администратор"));
            user.setIsAdmin(isAdmin);
        }
        if (parameters.containsKey("Статус")) {
            String statusDescription = parameters.get("Статус");
            Status status = Status.of(statusDescription);
            user.setStatus(status);
        }
        if (parameters.containsKey("Уведомления о новых событиях")) {
            String mailNotificationDescription = parameters.get("Уведомления о новых событиях");
            MailNotification mn = MailNotification.of(mailNotificationDescription);
            user.setMailNotification(mn);
        }
        if (parameters.containsKey("E-Mail")) {
            String emailsStashId = parameters.get("E-Mail");
            List<Email> emails = Context.getStash().get(emailsStashId, List.class);
            user.setEmails(emails);
        }
        user.create();
        Context.getStash().put(userStashId, user);
    }

}
