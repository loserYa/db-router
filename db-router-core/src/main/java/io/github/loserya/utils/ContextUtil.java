package io.github.loserya.utils;

import io.github.loserya.entity.Const;
import io.github.loserya.support.router.RouterHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 容器上下文工具
 *
 * @author loser
 */
public class ContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    private static final Map<String, RouterHandler<?>> Maps = new ConcurrentHashMap<>();

    public static RouterHandler<?> getRouterHandler(String strategy) {
        RouterHandler<?> routerHandler = Maps.get(strategy);
        if (Objects.isNull(routerHandler)) {
            routerHandler = Maps.get(Const.HASH);
        }
        return routerHandler;
    }

    public static boolean exit(String strategy) {
        return Maps.containsKey(strategy);
    }

    public static void addRouterHandler(RouterHandler<?> routerHandler) {
        String strategy = routerHandler.strategy();
        if (exit(strategy)) {
            throw new RuntimeException("strategy " + strategy + " is exit");
        }
        Maps.put(strategy, routerHandler);
    }

    @Override
    public void setApplicationContext(ApplicationContext context) {
        applicationContext = context;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static <T> T getBean(Class<T> type) {
        return applicationContext.getBean(type);
    }

}
