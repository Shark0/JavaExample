package com.shark.example.shoalter.menu.pojo;

import lombok.Data;

import java.util.List;

@Data
public class MenuDto {
    private Integer id;
    private String name;
    private String code;
    private Integer position;
    private List<MenuDto> menuList;
}
