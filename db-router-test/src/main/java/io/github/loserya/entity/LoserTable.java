package io.github.loserya.entity;

import lombok.Data;

import java.util.Date;

@Data
public class LoserTable {

    private Long id;

    private String name;

    private Integer age;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private Integer deleteFlag;

    private Integer version;

}
