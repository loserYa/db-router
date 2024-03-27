package io.github.loserya.utils;

/**
 * 字符串工具
 *
 * @author loser
 */
public class StrUtil {

    private StrUtil() {
    }

    public static boolean isEmpty(String str) {
        return null == str || str.length() == 0;
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

}
