package io.github.loserya.service;

import io.github.loserya.entity.LoserTable;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
public class Handler {

    @Resource
    private LoserService loserService;

    public void test() {
        testDel();
    }

    //---------------01 测试不分库不分表---------------------------------------------------------------------------------------------------------------------------------
    // select * from ape.sys_user where id = ?
    public void testMasterDataSource() {
        LoserTable sysUser = loserService.queryUserById(1L);
        System.out.println("sysUser = " + sysUser);
    }

    //---------------02 测试分库分表---------------------------------------------------------------------------------------------------------------------------------
    // select * from loser_01.sys_loser_01 where id = ?
    // select * from loser_01.sys_loser_02 where id = ?
    // select * from loser_02.sys_loser_01 where id = ?
    // select * from loser_01.sys_loser_02 where id = ?
    public void testDel() {

        for (long i = 1; i <= 100; i++) {
            try {
                int count = loserService.deleteById(i);
                System.out.println("del = " + count);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void testSave() {

        for (long i = 1; i <= 100; i++) {
            try {
                LoserTable table = new LoserTable();
                table.setId(i);
                table.setName("loser" + i);
                table.setAge(1);
                table.setCreateBy("sys");
                table.setCreateTime(new Date());
                table.setUpdateBy("sys");
                table.setUpdateTime(new Date());
                table.setDeleteFlag(0);
                table.setVersion(0);
                loserService.insert(table);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void testGet() {

        for (long i = 1; i <= 100; i++) {
            try {
                long l = System.currentTimeMillis();
                LoserTable sysUser = loserService.queryById(0, i);
                System.out.println("l = " + (System.currentTimeMillis() - l));
                System.out.println("sysUser = " + sysUser);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    //---------------03 测试分库不分表---------------------------------------------------------------------------------------------------------------------------------
// select * from loser_01.sys_lusir where id = ?
// select * from loser_02.sys_lusir where id = ?
    public void testDel01() {

        for (long i = 1; i <= 100; i++) {
            try {
                int count = loserService.deleteById01(i);
                System.out.println("del = " + count);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void testSave01() {

        for (long i = 1; i <= 100; i++) {
            try {
                LoserTable table = new LoserTable();
                table.setId(i);
                table.setName("loser" + i);
                table.setAge(1);
                table.setCreateBy("sys");
                table.setCreateTime(new Date());
                table.setUpdateBy("sys");
                table.setUpdateTime(new Date());
                table.setDeleteFlag(0);
                table.setVersion(0);
                loserService.insert01(table);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void testGet01() {

        for (long i = 1; i <= 100; i++) {
            try {
                long l = System.currentTimeMillis();
                LoserTable sysUser = loserService.queryById01(0, i);
                System.out.println("l = " + (System.currentTimeMillis() - l));
                System.out.println("sysUser = " + sysUser);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

//---------------04 测试不分库分表---------------------------------------------------------------------------------------------------------------------------------
// select * from ape.sys_alan_01 where id = ?
// select * from ape.sys_alan_02 where id = ?

    public void testDel02() {

        for (long i = 1; i <= 100; i++) {
            try {
                int count = loserService.deleteById02(i);
                System.out.println("del = " + count);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void testSave02() {

        for (long i = 1; i <= 100; i++) {
            try {
                LoserTable table = new LoserTable();
                table.setId(i);
                table.setName("loser" + i);
                table.setAge(1);
                table.setCreateBy("sys");
                table.setCreateTime(new Date());
                table.setUpdateBy("sys");
                table.setUpdateTime(new Date());
                table.setDeleteFlag(0);
                table.setVersion(0);
                loserService.insert02(table);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void testGet02() {

        for (long i = 1; i <= 100; i++) {
            try {
                long l = System.currentTimeMillis();
                LoserTable sysUser = loserService.queryById02(0, i);
                System.out.println("l = " + (System.currentTimeMillis() - l));
                System.out.println("sysUser = " + sysUser);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


}
