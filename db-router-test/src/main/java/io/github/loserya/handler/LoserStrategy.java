package io.github.loserya.handler;

import io.github.loserya.entity.Router;
import io.github.loserya.support.router.RouterHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.springframework.stereotype.Component;

@Component
public class LoserStrategy implements RouterHandler<Long> {
    @Override
    public String strategy() {
        return "loser";
    }

    @Override
    public Router doRouter(BoundSql boundSql, String key, int dbCount, int tableCount, String params) {
        return new Router(1, 1);
    }

    @Override
    public Long buildShardValue(BoundSql boundSql, String key) {
        return null;
    }
}
