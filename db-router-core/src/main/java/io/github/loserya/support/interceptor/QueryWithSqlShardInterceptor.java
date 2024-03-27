package io.github.loserya.support.interceptor;

import io.github.loserya.support.DBContextHolder;
import io.github.loserya.support.SqlHandler;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

/**
 * 分片拦截器
 * 分库：切换数据源
 * 分表：将逻辑表替换为物理表
 *
 * @author loser
 */
@Intercepts(@Signature(
        type = Executor.class,
        method = "query",
        args = {
                MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}))
public class QueryWithSqlShardInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        BoundSql boundSql = mappedStatement.getBoundSql(invocation.getArgs()[1]);
        SqlHandler.Build.INSTANCE.getSqlHandler().setThreadLocalInfo(boundSql);
        try {
            return invocation.proceed();
        } finally {
            DBContextHolder.clearDBKey();
        }

    }

}