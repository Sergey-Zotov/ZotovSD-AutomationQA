package zotov_sd.automation_qa.utils;

import org.testng.Assert;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.time.format.DateTimeFormatter.ofPattern;

public class CompareUtils {

    private static final Comparator<String> DATE_DESC_COMPARATOR = (s1, s2) -> {
        LocalDateTime d1 = LocalDateTime.parse(s1, ofPattern("dd.MM.yyyy HH:mm"));
        LocalDateTime d2 = LocalDateTime.parse(s2, ofPattern("dd.MM.yyyy HH:mm"));
        return d2.compareTo(d1);
    };

    private static final Comparator<String> DATE_ASC_COMPARATOR = DATE_DESC_COMPARATOR.reversed();

    private static final Comparator<String> USER_DESC_COMPARATOR = String::compareToIgnoreCase;

    private static final Comparator<String> USER_ASC_COMPARATOR =USER_DESC_COMPARATOR.reversed();

    public static void assertListSortedByDateDesc(List<String> dates) {
        List<String> datesCopy = new ArrayList<>(dates);
        datesCopy.sort(DATE_DESC_COMPARATOR);
        Assert.assertEquals(dates, datesCopy);
    }

    public static void assertListSortedByDateAsc(List<String> dates) {
        List<String> datesCopy = new ArrayList<>(dates);
        datesCopy.sort(DATE_ASC_COMPARATOR);
        Assert.assertEquals(dates, datesCopy);
    }

    public static void assertListSortedByUserDesc(List<String> users) {
        List<String> usersCopy = new ArrayList<>(users);
        usersCopy.sort(USER_DESC_COMPARATOR);
        Assert.assertEquals(users, usersCopy);
    }

    public static void assertListSortedByUserAsc(List<String> users) {
        List<String> usersCopy = new ArrayList<>(users);
        usersCopy.sort(USER_ASC_COMPARATOR);
        Assert.assertEquals(users, usersCopy);
    }
}
