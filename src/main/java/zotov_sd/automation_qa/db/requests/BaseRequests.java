package zotov_sd.automation_qa.db.requests;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public abstract class BaseRequests {

    protected LocalDateTime toLocalDate(Object timestamp) {
        Timestamp ts = (Timestamp) timestamp;
        return ts.toLocalDateTime();
    }

}
