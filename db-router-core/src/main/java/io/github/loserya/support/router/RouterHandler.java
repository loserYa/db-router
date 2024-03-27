package io.github.loserya.support.router;


import io.github.loserya.entity.Router;
import org.apache.ibatis.mapping.BoundSql;

/**
 * 自定义路由处理器
 *
 * @author loser
 */
public interface RouterHandler<T> {

    /**
     * 策略类型
     *
     * @return 返回策略
     */
    String strategy();

    /**
     * 自定义路由规则
     *
     * @param dbCount    数据库索引
     * @param tableCount 表索引
     * @param params     自定参数
     * @return 路由参数
     */
    Router doRouter(BoundSql boundSql, String key, int dbCount, int tableCount, String params) throws Exception;

    /**
     * 获取分片值
     */
    T buildShardValue(BoundSql boundSql, String key) throws Exception;

    /**
     * 是否是基础类型对象
     */
    default boolean isPrimitiveObj(Object obj) {
        return obj.getClass().isPrimitive() || obj instanceof String ||
                obj instanceof Integer || obj instanceof Long ||
                obj instanceof Double || obj instanceof Float ||
                obj instanceof Boolean || obj instanceof Byte ||
                obj instanceof Short || obj instanceof Character;
    }

}
