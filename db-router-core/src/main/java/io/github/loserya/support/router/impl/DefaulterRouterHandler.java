package io.github.loserya.support.router.impl;

import io.github.loserya.entity.Const;
import io.github.loserya.entity.Router;
import io.github.loserya.support.router.RouterHandler;
import org.apache.ibatis.mapping.BoundSql;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * 默认路由规则 使用扰动函数
 *
 * @author loser
 */
public class DefaulterRouterHandler implements RouterHandler<Object> {

    @Override
    public String strategy() {
        return Const.HASH;
    }

    @Override
    public Router doRouter(BoundSql boundSql, String key, int dbCount, int tableCount, String params) throws Exception {
        Object value = buildShardValue(boundSql, key);
        int size = dbCount * tableCount;
        int idx = (size - 1) & (value.hashCode() ^ (value.hashCode() >>> 16));
        int dbIdx = idx / tableCount + 1;
        int tbIdx = idx - tableCount * (dbIdx - 1) + 1;
        return new Router(dbIdx, tbIdx);
    }

    @Override
    public Object buildShardValue(BoundSql boundSql, String key) throws Exception {

        Object value;
        Object parameterObject = boundSql.getParameterObject();
        if (parameterObject instanceof HashMap) {
            value = ((HashMap<?, ?>) parameterObject).get(key);
        } else if (isPrimitiveObj(parameterObject)) {
            value = parameterObject;
        } else {
            Field declaredField = parameterObject.getClass().getDeclaredField(key);
            declaredField.setAccessible(true);
            value = declaredField.get(parameterObject);
        }
        return value;

    }

}
