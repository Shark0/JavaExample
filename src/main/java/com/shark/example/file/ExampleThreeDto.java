package com.shark.example.file;

import lombok.Data;

import java.io.Serializable;

@Data
public class ExampleThreeDto implements Serializable {
    private String uuid;
    private String result;
}
