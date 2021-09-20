package zotov_sd.automation_qa.utils;

import java.util.Random;

/**
 * Класс для работы со строками, их генерации.
 */
public class StringUtils {
    private static final String LATIN_PATTERN = "abcdefghijklmnopqrstuvwxyz";
    private static final String HEX_PATTERN = "0123456789abcdef";
    private static final Random RANDOM = new Random();

    /**
     * Генерирует случайный email.
     *
     * @return строка, содержащая сгенерированный email.
     */
    public static String randomEmail() {
        return randomEnglishString(6) + "@" + randomEnglishString(6) + "." + randomEnglishString(3);
    }

    /**
     * Генерирует 16ричную стороку.
     *
     * @param length длина сторки.
     * @return сгенерированная строка.
     */
    public static String randomHexString(int length) {
        return randomString(HEX_PATTERN, length);
    }

    /**
     * Генерирует латинскую строку определенной длины.
     *
     * @param length длина строки.
     * @return сгенерированную латинскую строку.
     */
    public static String randomEnglishString(int length) {
        return randomString(LATIN_PATTERN, length);
    }

    /**
     * Генерирует строку по передаваемому патерну определенной длины.
     *
     * @param pattern шаблон строки.
     * @param length  длина строки.
     * @return сгенерированная сторка.
     */
    public static String randomString(String pattern, int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int patternLength = pattern.length();
            int randomIndex = RANDOM.nextInt(patternLength);
            char c = pattern.charAt(randomIndex);
            sb.append(c);
        }
        return sb.toString();
    }

}
