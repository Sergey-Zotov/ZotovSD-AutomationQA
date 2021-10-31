package zotov_sd.automation_qa.utils;

import io.qameta.allure.Step;
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

    private static final Comparator<String> USER_ASC_COMPARATOR = USER_DESC_COMPARATOR.reversed();

    @Step("Проверка сортировки списка дат по убыванию")
    public static void assertListSortedByDateDesc(List<String> dates) {
        List<String> datesCopy = new ArrayList<>(dates);
        datesCopy.sort(DATE_DESC_COMPARATOR);
        Assert.assertEquals(dates, datesCopy);
    }

    @Step("Проверка сортировки списка дат по возрастанию")
    public static void assertListSortedByDateAsc(List<String> dates) {
        List<String> datesCopy = new ArrayList<>(dates);
        datesCopy.sort(DATE_ASC_COMPARATOR);
        Assert.assertEquals(dates, datesCopy);
    }

    @Step("Проверка сортировки списка элементов по убыванию")
    public static void assertListSortedByElementsDesc(List<String> elements) {
        List<String> elementsCopy = new ArrayList<>(elements);
        elementsCopy.sort(USER_DESC_COMPARATOR);
        Assert.assertEquals(elements, elementsCopy);
    }

    @Step("Проверка сортировки списка элементо по возрастанию")
    public static void assertListSortedByElementAsc(List<String> elements) {
        List<String> elementsCopy = new ArrayList<>(elements);
        elementsCopy.sort(USER_ASC_COMPARATOR);
        Assert.assertEquals(elements, elementsCopy);
    }

    @Step("Проверка на неотсортированность списка элементов")
    public static void assertListNoSortedByElement(List<String> elements) {
        List<String> elementCopy = new ArrayList<>(elements);
        elementCopy.sort(USER_DESC_COMPARATOR);
        Assert.assertNotEquals(elements, elementCopy);
        elementCopy.sort(USER_ASC_COMPARATOR);
        Assert.assertNotEquals(elements, elementCopy);
    }
}
