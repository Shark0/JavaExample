package com.shark.example.shoalter.menu.pojo;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class RoleMenuDto {
    private int roleId;
    private String roleCode;
    private String roleName;

    private Map<String, List<MenuDto>> buCodeMenuListHashMap;
}
