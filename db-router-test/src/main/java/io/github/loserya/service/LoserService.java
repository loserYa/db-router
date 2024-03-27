package io.github.loserya.service;

import io.github.loserya.dao.LoserDao;
import io.github.loserya.entity.LoserTable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoserService {

    @Resource
    private LoserDao loserDao;

    /**
     * 01 测试不分库分表
     */
    public LoserTable queryUserById(long id) {
        return loserDao.queryUserById(0, id);
    }

    /**
     * 02 测试分库分表
     */
    public LoserTable queryById(long ignore, long id) {
        return loserDao.queryById(ignore, id);
    }

    /**
     * 02 测试分库分表
     */
    public int insert(LoserTable loserTable) {
        return loserDao.insert(loserTable);
    }

    /**
     * 02 测试分库分表
     */
    public int deleteById(long id) {
        return loserDao.deleteById(id);
    }


    /**
     * 03 测试分库不分表
     */
    public int deleteById01(long id) {
        return loserDao.deleteById01(id);
    }

    /**
     * 03 测试分库不分表
     */
    public int insert01(LoserTable loserTable) {
        return loserDao.insert01(loserTable);
    }

    /**
     * 03 测试分库不分表
     */
    public LoserTable queryById01(long ignore, long id) {
        return loserDao.queryById01(ignore, id);
    }

    /**
     * 04 测试不分库分表
     */
    public int deleteById02(long id) {
        return loserDao.deleteById02(id);
    }


    /**
     * 04 测试不分库分表
     */
    public int insert02(LoserTable loserTable) {
        return loserDao.insert02(loserTable);
    }

    /**
     * 04 测试不分库分表
     */
    public LoserTable queryById02(long ignore, long id) {
        return loserDao.queryById02(ignore, id);
    }


}
