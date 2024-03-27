package io.github.loserya.entity;


public class Router {

    /**
     * 数据库下标
     */
    private int dbIndex;

    /**
     * 表下标
     */
    private int tbIndex;

    public Router(int dbIndex, int tbIndex) {
        this.dbIndex = dbIndex;
        this.tbIndex = tbIndex;
    }

    public Router() {
    }

    public int getDbIndex() {
        return dbIndex;
    }

    public void setDbIndex(int dbIndex) {
        this.dbIndex = dbIndex;
    }

    public int getTbIndex() {
        return tbIndex;
    }

    public void setTbIndex(int tbIndex) {
        this.tbIndex = tbIndex;
    }
}
