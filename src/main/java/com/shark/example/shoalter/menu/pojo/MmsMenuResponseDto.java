package com.shark.example.shoalter.menu.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class MmsMenuResponseDto implements Serializable {
    private String status;
    private String message;

    private List<MmsMenuDto> data;
}
