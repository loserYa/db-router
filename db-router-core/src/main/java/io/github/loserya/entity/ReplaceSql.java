package io.github.loserya.entity;


/**
 * sql 是否被替换
 *
 * @author loser
 */
public class ReplaceSql {

    /**
     * 旧sql
     */
    private String oldSql;

    /**
     * 新sql
     */
    private String newSql;

    public ReplaceSql() {
    }

    public ReplaceSql(String oldSql, String newSql) {
        this.oldSql = oldSql;
        this.newSql = newSql;
    }

    public String getOldSql() {
        return oldSql;
    }

    public void setOldSql(String oldSql) {
        this.oldSql = oldSql;
    }

    public String getNewSql() {
        return newSql;
    }

    public void setNewSql(String newSql) {
        this.newSql = newSql;
    }
}
