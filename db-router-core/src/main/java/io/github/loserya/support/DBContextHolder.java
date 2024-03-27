package io.github.loserya.support;


public class DBContextHolder {

    private static final ThreadLocal<String> db = new ThreadLocal<>();
    private static final ThreadLocal<String> sql = new ThreadLocal<>();

    public static void setTbKey(String keyMap) {
        sql.set(keyMap);
    }

    public static String getTbKey() {
        return sql.get();
    }

    public static void setDBKey(String dbKeyIdx) {
        db.set(dbKeyIdx);
    }

    public static String getDBKey() {
        return db.get();
    }

    public static void clearDBKey() {
        db.remove();
    }

    public static void clearTBKey() {
        sql.remove();
    }

}
