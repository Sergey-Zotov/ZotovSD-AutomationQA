package zotov_sd.automation_qa.clean;

import org.testng.annotations.Test;
import zotov_sd.automation_qa.db.connection.PostgresConnection;

import java.util.ArrayList;
import java.util.List;

public class CleanDB {

    @Test
    public void CleanDbTest() {
        List<String> cleanList = new ArrayList<>();
        cleanList.add("DELETE FROM members\n" +
                "WHERE id > 1;\n");
        cleanList.add("DELETE FROM email_addresses\n" +
                "WHERE user_id > 5;\n");
        cleanList.add("DELETE FROM tokens\n" +
                "WHERE user_id > 5;\n");
        cleanList.add("DELETE FROM users\n" +
                "WHERE id > 5;\n");
        cleanList.add("DELETE FROM roles\n" +
                "WHERE id > 3;\n");
        cleanList.add("DELETE FROM member_roles\n" +
                "WHERE id > 2;\n");
        cleanList.add("DELETE FROM projects\n" +
                "WHERE id > 1;\n");

        for (String query : cleanList) {
            PostgresConnection.INSTANCE.executeQuery(query);
        }
    }
}
