package io.github.loserya.utils;


import io.github.loserya.entity.ShardingProperties;
import io.github.loserya.entity.TableInfo;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 配置转化
 *
 * @author loser
 */
public class ShadingConverter {

    public static ShardingProperties fromProperties(Properties properties) {

        ShardingProperties myObject = new ShardingProperties();
        Field[] fields = ShardingProperties.class.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            String fieldValue = properties.getProperty(fieldName);
            if (StrUtil.isEmpty(fieldValue) && field.getType() != List.class) {
                continue;
            }
            try {
                if (field.getType() == String.class) {
                    field.set(myObject, fieldValue);
                } else if (field.getType() == int.class) {
                    field.set(myObject, Integer.valueOf(fieldValue));
                } else if (field.getType() == List.class) {
                    List<TableInfo> items = getTableInfos(properties, fieldName);
                    field.set(myObject, items);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return myObject;

    }

    private static List<TableInfo> getTableInfos(Properties properties, String fieldName) throws IllegalAccessException {

        List<TableInfo> items = new ArrayList<>();
        int index = 0;
        while (true) {
            boolean isAllEmpty = true;
            TableInfo tableInfo = new TableInfo();
            Field[] declaredFields = TableInfo.class.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                String key = String.format("%s[%s].%s", fieldName, index, declaredField.getName());
                String value = properties.getProperty(key);
                if (StrUtil.isNotEmpty(value)) {
                    isAllEmpty = false;
                    declaredField.setAccessible(true);
                    if (declaredField.getType() == String.class) {
                        declaredField.set(tableInfo, value);
                    } else if (declaredField.getType() == int.class) {
                        declaredField.set(tableInfo, Integer.valueOf(value));
                    }
                }
            }
            index++;
            if (isAllEmpty) {
                break;
            }
            items.add(tableInfo);
        }
        return items;

    }

}
