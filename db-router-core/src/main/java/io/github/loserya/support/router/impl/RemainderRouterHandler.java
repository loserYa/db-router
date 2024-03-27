package io.github.loserya.support.router.impl;


import io.github.loserya.entity.Const;
import io.github.loserya.entity.Router;
import io.github.loserya.support.router.RouterHandler;
import org.apache.ibatis.mapping.BoundSql;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * 取余数路由
 *
 * @author loser
 */
public class RemainderRouterHandler implements RouterHandler<Integer> {

    @Override
    public String strategy() {
        return Const.REMAINDER;
    }

    @Override
    public Router doRouter(BoundSql boundSql, String key, int dbCount, int tableCount, String params) throws Exception {
        int res = buildShardValue(boundSql, key) + 1;
        int dbIdx = res % dbCount + 1;
        int tbIdx = res % tableCount + 1;
        return new Router(dbIdx, tbIdx);
    }

    @Override
    public Integer buildShardValue(BoundSql boundSql, String key) throws Exception {
        String value;
        Object parameterObject = boundSql.getParameterObject();
        if (parameterObject instanceof HashMap) {
            value = ((HashMap<?, ?>) parameterObject).get(key).toString();
        } else if (isPrimitiveObj(parameterObject)) {
            value = parameterObject.toString();
        } else {
            Field declaredField = parameterObject.getClass().getDeclaredField(key);
            declaredField.setAccessible(true);
            value = declaredField.get(parameterObject).toString();
        }
        return Integer.parseInt(value);
    }

}
