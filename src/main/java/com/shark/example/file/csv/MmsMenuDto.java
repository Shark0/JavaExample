package com.shark.example.file.csv;

import lombok.Data;

import java.util.List;

@Data
public class MmsMenuDto {
    private Integer id;
    private Integer parent;
    private Integer position;
    private String code;
    private String sla;
    private String funcNameEn;
    private String funcNameTw;
    private List<MmsMenuDto> menuList;
}
