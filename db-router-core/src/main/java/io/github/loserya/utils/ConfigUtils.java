package io.github.loserya.utils;

import io.github.loserya.entity.ShardingProperties;

/**
 * 配置工具
 *
 * @author loser
 */
public class ConfigUtils {

    private static ShardingProperties shardingProperties;

    private static String dbFormat;

    private static String tbFormat;

    private ConfigUtils() {
    }

    public static ShardingProperties getShardingProperties() {
        return shardingProperties;
    }

    public static void setShardingProperties(ShardingProperties shardingProperties) {
        ConfigUtils.shardingProperties = shardingProperties;
    }

    public static String getDbFormat() {
        return dbFormat;
    }

    public static void setDbFormat(String dbFormat) {
        ConfigUtils.dbFormat = dbFormat;
    }

    public static String getTbFormat() {
        return tbFormat;
    }

    public static void setTbFormat(String tbFormat) {
        ConfigUtils.tbFormat = tbFormat;
    }
}
