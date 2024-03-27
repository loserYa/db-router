package io.github.loserya.config;

import io.github.loserya.entity.DbInfo;
import io.github.loserya.entity.ShardingProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * 路由组件配置类
 *
 * @author loser
 */
@ConfigurationProperties("db.router")
public class RouterProperties {

    private String dbFormat = "db%02d";

    private String tbFormat = "_%02d";

    /**
     * 主数据源
     */
    private DbInfo master;

    /**
     * 分库数据源
     */
    private List<DbInfo> dbs;

    /**
     * 分库分表策略配置文件路径
     */
    private String config;

    /**
     * 分库分表策略配置
     */
    private ShardingProperties shadingProperties;

    public DbInfo getMaster() {
        return master;
    }

    public void setMaster(DbInfo master) {
        this.master = master;
    }

    public List<DbInfo> getDbs() {
        return dbs;
    }

    public void setDbs(List<DbInfo> dbs) {
        this.dbs = dbs;
    }


    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public ShardingProperties getShadingProperties() {
        return shadingProperties;
    }

    public void setShadingProperties(ShardingProperties shadingProperties) {
        this.shadingProperties = shadingProperties;
    }

    public String getDbFormat() {
        return dbFormat;
    }

    public void setDbFormat(String dbFormat) {
        this.dbFormat = dbFormat;
    }

    public String getTbFormat() {
        return tbFormat;
    }

    public void setTbFormat(String tbFormat) {
        this.tbFormat = tbFormat;
    }
}
