package com.shark.example.shoalter.menu.pojo;

import lombok.Data;

@Data
public class SysRoleFuncDo {
    private Integer id;
    private Integer roleId;
    private Integer funcId;
    private String canView;
    private String active;
}