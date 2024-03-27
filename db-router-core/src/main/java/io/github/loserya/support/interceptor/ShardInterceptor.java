package io.github.loserya.support.interceptor;

import io.github.loserya.support.DBContextHolder;
import io.github.loserya.entity.Const;
import io.github.loserya.utils.StrUtil;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Connection;

/**
 * 分片拦截器
 * 分库：切换数据源
 * 分表：将逻辑表替换为物理表
 *
 * @author loser
 */
@Intercepts(@Signature(
        type = StatementHandler.class,
        method = "prepare",
        args = {
                Connection.class, Integer.class}))
public class ShardInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        String tbKey = DBContextHolder.getTbKey();
        if (StrUtil.isEmpty(tbKey)) {
            return invocation.proceed();
        }
        try {
            StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
            MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY,
                    SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory());
            metaObject.setValue(Const.REAL_SQL, DBContextHolder.getTbKey());
            return invocation.proceed();
        } finally {
            DBContextHolder.clearTBKey();
        }

    }

}