package io.github.loserya.entity;

public class Router {

    /**
     * 数据库下标
     */
    private String dbIndex;

    /**
     * 表下标
     */
    private String tbIndex;

    public Router(String dbIndex, String tbIndex) {
        this.dbIndex = dbIndex;
        this.tbIndex = tbIndex;
    }

    public Router() {
    }

    public String getDbIndex() {
        return dbIndex;
    }

    public void setDbIndex(String dbIndex) {
        this.dbIndex = dbIndex;
    }

    public String getTbIndex() {
        return tbIndex;
    }

    public void setTbIndex(String tbIndex) {
        this.tbIndex = tbIndex;
    }
}
