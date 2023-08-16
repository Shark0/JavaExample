package com.shark.example.shoalter.menu.pojo;

import lombok.Data;

@Data
public class SysRevampRoleFuncDo {
    private Integer id;
    private Integer roleId;
    private Integer funcId;
    private Boolean active;
}