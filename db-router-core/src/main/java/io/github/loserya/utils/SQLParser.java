package io.github.loserya.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SQLParser {

    // 匹配 SQL 语句中的表名
    private static final Pattern selectPattern = Pattern.compile("\\bFROM\\b\\s+(\\w+)", Pattern.CASE_INSENSITIVE);
    private static final Pattern updatePattern = Pattern.compile("\\bUPDATE\\b\\s+(\\w+)", Pattern.CASE_INSENSITIVE);
    private static final Pattern insertPattern = Pattern.compile("\\bINTO\\b\\s+(\\w+)", Pattern.CASE_INSENSITIVE);
    private static final Pattern deletePattern = Pattern.compile("\\bFROM\\b\\s+(\\w+)", Pattern.CASE_INSENSITIVE);

    public static String getTableNameFromSQL(String sql) {

        Matcher matcher;

        // 匹配 SELECT 语句
        matcher = selectPattern.matcher(sql);
        if (matcher.find()) {
            return matcher.group(1);
        }

        // 匹配 UPDATE 语句
        matcher = updatePattern.matcher(sql);
        if (matcher.find()) {
            return matcher.group(1);
        }

        // 匹配 INSERT 语句
        matcher = insertPattern.matcher(sql);
        if (matcher.find()) {
            return matcher.group(1);
        }

        // 匹配 DELETE 语句
        matcher = deletePattern.matcher(sql);
        if (matcher.find()) {
            return matcher.group(1);
        }

        return null;
    }

}
