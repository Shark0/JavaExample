package com.shark.example.shoalter.menu.pojo;

import lombok.Data;

@Data
public class SysRevampFuncDo {
    private Integer id;
    private Integer parentFuncId;
    private Integer position;
    private String funcCode;
    private String slaService;
    private String funcNameEn;
    private String funcNameCn;
    private String funcNameTw;
}
