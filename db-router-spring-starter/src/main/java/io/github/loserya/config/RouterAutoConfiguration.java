package io.github.loserya.config;

import io.github.loserya.entity.Const;
import io.github.loserya.entity.DbInfo;
import io.github.loserya.entity.ShardingProperties;
import io.github.loserya.support.DynamicDataSource;
import io.github.loserya.support.interceptor.QueryShardInterceptor;
import io.github.loserya.support.interceptor.QueryWithSqlShardInterceptor;
import io.github.loserya.support.interceptor.ShardInterceptor;
import io.github.loserya.support.interceptor.UpdateShardInterceptor;
import io.github.loserya.support.router.RouterHandler;
import io.github.loserya.support.router.impl.DefaulterRouterHandler;
import io.github.loserya.support.router.impl.RemainderRouterHandler;
import io.github.loserya.utils.ConfigUtils;
import io.github.loserya.utils.ContextUtil;
import io.github.loserya.utils.ShadingConverter;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.util.CollectionUtils;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 路由组建自动配置类
 *
 * @author loser
 */
@Configuration
@EnableConfigurationProperties(RouterProperties.class)
public class RouterAutoConfiguration {

    private final RouterProperties properties;

    public RouterAutoConfiguration(RouterProperties properties, ResourceLoader resourceLoader) {

        String config = properties.getConfig();
        Resource resource = resourceLoader.getResource(config);
        YamlPropertiesFactoryBean yamlFactory = new YamlPropertiesFactoryBean();
        yamlFactory.setResources(resource);
        Properties prop = yamlFactory.getObject();
        ShardingProperties shardingProperties = ShadingConverter.fromProperties(prop);
        properties.setShadingProperties(shardingProperties);
        ConfigUtils.setShardingProperties(shardingProperties);
        ConfigUtils.setTbFormat(properties.getTbFormat());
        ConfigUtils.setDbFormat(properties.getDbFormat());
        this.properties = properties;

        ContextUtil.addRouterHandler(new DefaulterRouterHandler());
        ContextUtil.addRouterHandler(new RemainderRouterHandler());

    }

    @Bean
    public DataSource dataSource() {

        Map<Object, Object> targetDataSources = new HashMap<>();
        int index = 1;
        for (DbInfo db : properties.getDbs()) {
            String dbName = String.format(properties.getDbFormat(), index);
            targetDataSources.put(dbName, new DriverManagerDataSource(db.getUrl(), db.getUserName(), db.getPassword()));
            index++;
        }
        DbInfo db = properties.getMaster();
        targetDataSources.put(null, new DriverManagerDataSource(db.getUrl(), db.getUserName(), db.getPassword()));
        // 设置数据源
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setTargetDataSources(targetDataSources);
        return dynamicDataSource;

    }

    @Bean
    public ContextUtil contextUtil(@Autowired(required = false) List<RouterHandler<?>> handlers) {
        if (!CollectionUtils.isEmpty(handlers)) {
            for (RouterHandler<?> handler : handlers) {
                ContextUtil.addRouterHandler(handler);
            }
        }
        return new ContextUtil();
    }

    @Bean
    public org.apache.ibatis.session.Configuration configuration() {

        // 配置分库分表拦截器
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setUseColumnLabel(true);
        configuration.addInterceptor(new UpdateShardInterceptor());
        configuration.addInterceptor(new QueryShardInterceptor());
        configuration.addInterceptor(new QueryWithSqlShardInterceptor());
        configuration.addInterceptor(new ShardInterceptor());
        return configuration;

    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {

        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource());
        bean.setConfiguration(configuration());
        return bean.getObject();

    }


}
