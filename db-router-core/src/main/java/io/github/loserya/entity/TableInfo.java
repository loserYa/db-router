package io.github.loserya.entity;


/**
 * 需要进行分库分表的表
 *
 * @author loser
 */
public class TableInfo {

    /**
     * 表名称
     */
    private String table;

    /**
     * 分片键
     */
    private String key;

    /**
     * 分库数 优先级比总的配置高
     */
    private int dbCount = 0;

    /**
     * 分表数 优先级比总的配置高
     */
    private int tbCount = 0;

    /**
     * 分片策略
     */
    private String strategy;

    /**
     * 路由规则自定义参数
     */
    private String params;

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getDbCount() {
        return dbCount;
    }

    public void setDbCount(int dbCount) {
        this.dbCount = dbCount;
    }

    public int getTbCount() {
        return tbCount;
    }

    public void setTbCount(int tbCount) {
        this.tbCount = tbCount;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }
}
