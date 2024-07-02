package com.shark.example.file.ftp;

import lombok.Data;

import java.util.List;

@Data
public class FtpPathDto {
    private String name;
    private List<FtpPathDto> childList;
}
