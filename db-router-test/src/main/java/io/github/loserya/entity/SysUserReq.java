package io.github.loserya.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysUserReq implements Serializable {

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

