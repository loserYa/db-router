package io.github.loserya.entity;

import io.github.loserya.utils.StrUtil;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 路由组件配置类
 *
 * @author loser
 */
public class ShardingProperties {

    /**
     * 总的路由策略
     */
    private String strategy = "hash";

    /**
     * 总的分库数
     */
    private int dbCount;

    /**
     * 总的分表数
     */
    private int tbCount;

    /**
     * 总的自定义路由配置参数
     */
    private String params;

    /**
     * 配置需要被分表的 逻辑表名和分片键 个性化配置
     */
    private List<TableInfo> tables;

    private Map<String, TableInfo> map;


    public Map<String, TableInfo> getTableMap() {
        if (Objects.nonNull(map)) {
            return map;
        }
        synchronized (ShardingProperties.class) {
            if (Objects.nonNull(map)) {
                return map;
            }
            map = getTables().stream().peek(item -> {
                if (StrUtil.isEmpty(item.getStrategy())) {
                    item.setParams(getStrategy());
                }
                if (item.getDbCount() == 0) {
                    item.setDbCount(getDbCount());
                }
                if (item.getTbCount() == 0) {
                    item.setTbCount(getTbCount());
                }
            }).collect(Collectors.toMap(TableInfo::getTable, Function.identity()));
            if (CollectionUtils.isEmpty(map)) {
                map = new HashMap<>();
            }
        }
        return map;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
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

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public List<TableInfo> getTables() {
        return tables;
    }

    public void setTables(List<TableInfo> tables) {
        this.tables = tables;
    }
}

