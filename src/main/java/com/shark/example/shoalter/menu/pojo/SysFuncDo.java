package com.shark.example.shoalter.menu.pojo;

import lombok.Data;

@Data
public class SysFuncDo {
    private Integer id;
    private String funcCode;
    private String type;
    private Integer level1Pos;
    private Integer level2Pos;
    private String funcNameEn;
    private String funcNameZhCn;
    private String funcNameZhTw;
}