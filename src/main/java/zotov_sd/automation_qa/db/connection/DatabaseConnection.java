package zotov_sd.automation_qa.db.connection;

import java.util.List;
import java.util.Map;

public interface DatabaseConnection {

    List<Map<String, Object>> executeQuery(String query, Object... parameters);

}
