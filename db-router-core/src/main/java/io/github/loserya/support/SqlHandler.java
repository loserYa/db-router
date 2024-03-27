package io.github.loserya.support;

import io.github.loserya.entity.ReplaceSql;
import io.github.loserya.entity.Router;
import io.github.loserya.entity.ShardingProperties;
import io.github.loserya.entity.TableInfo;
import io.github.loserya.support.router.RouterHandler;
import io.github.loserya.utils.ConfigUtils;
import io.github.loserya.utils.ContextUtil;
import io.github.loserya.utils.SQLParser;
import io.github.loserya.utils.StrUtil;
import org.apache.ibatis.mapping.BoundSql;

import java.util.Objects;

public class SqlHandler {

    public void setThreadLocalInfo(BoundSql boundSql) throws Exception {

        String sql = boundSql.getSql();
        String table = SQLParser.getTableNameFromSQL(sql);
        if (Objects.isNull(table) || table.isEmpty()) {
            return;
        }
        // 01 从配置中获取需要被分库分表的表信息
        ShardingProperties shadingProperties = ConfigUtils.getShardingProperties();
        TableInfo tableInfo = shadingProperties.getTableMap().get(table);
        if (Objects.isNull(tableInfo)) {
            return;
        }
        // 02 获取参数的真实值 并路由到指定库 指定表
        String strategy = tableInfo.getStrategy();
        RouterHandler<?> routerHandler = ContextUtil.getRouterHandler(strategy);
        int dbCount = tableInfo.getDbCount() > 0 ? tableInfo.getDbCount() : shadingProperties.getDbCount();
        int tbCount = tableInfo.getTbCount() > 0 ? tableInfo.getTbCount() : shadingProperties.getDbCount();
        Router router = routerHandler.doRouter(boundSql, tableInfo.getKey(), dbCount, tbCount, shadingProperties.getParams());
        if (dbCount == 1) router.setDbIndex(0);
        if (tbCount == 1) router.setTbIndex(0);
        ReplaceSql replaceSql = replaceSql(sql, table, router.getTbIndex());
        if (StrUtil.isNotEmpty(replaceSql.getNewSql())) {
            DBContextHolder.setTbKey(replaceSql.getNewSql());
        }
        DBContextHolder.setDBKey(String.format(ConfigUtils.getDbFormat(), router.getDbIndex()));

    }

    /**
     * 替换表名称
     *
     * @param sql 携带逻辑表的sql
     * @return 替换成物理表的sql
     */
    public ReplaceSql replaceSql(String sql, String table, Integer tableIndex) {

        ReplaceSql replaceSql = new ReplaceSql(sql, "");
        if (StrUtil.isEmpty(table)) {
            return replaceSql;
        }
        if (Objects.isNull(tableIndex) || tableIndex <= 0) {
            return replaceSql;
        }
        String replace = sql.replace(table, table + String.format(ConfigUtils.getTbFormat(), tableIndex));
        return new ReplaceSql(sql, replace);

    }

    public enum Build {
        INSTANCE(new SqlHandler());

        public SqlHandler getSqlHandler() {
            return sqlHandler;
        }

        Build(SqlHandler sqlHandler) {
            this.sqlHandler = sqlHandler;
        }

        private final SqlHandler sqlHandler;
    }

}
