package com.shark.example.file.xml.pojo;

import lombok.Data;

import java.util.List;

@Data
public class XmlUserDo {
    String name;
    List<XmlInterestDo> interestList;
}
