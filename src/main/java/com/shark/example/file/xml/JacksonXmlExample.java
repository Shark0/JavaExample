package com.shark.example.file.xml;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.Gson;
import com.shark.example.file.xml.pojo.XmlInterestDo;
import com.shark.example.file.xml.pojo.XmlUserDo;

import java.util.ArrayList;
import java.util.List;

public class JacksonXmlExample {
    public static void main(String[] args) {
        JacksonXmlExample example = new JacksonXmlExample();
        List<XmlUserDo> xmlUserDoList = example.generateData();
        String xml = example.parserToXml(xmlUserDoList);
        System.out.println(xml);

        List<XmlUserDo> parserXmlUserDoList = example.parserToDo(xml);
        System.out.println(new Gson().toJson(parserXmlUserDoList));
    }

    private List<XmlUserDo> generateData() {
        XmlInterestDo xmlInterest1Do = new XmlInterestDo();
        xmlInterest1Do.setName("Watch Movie");
        XmlInterestDo xmlInterest2Do = new XmlInterestDo();
        xmlInterest2Do.setName("Play Basketball");
        List<XmlInterestDo> xmlInterestDoList = new ArrayList<>();
        xmlInterestDoList.add(xmlInterest1Do);
        xmlInterestDoList.add(xmlInterest2Do);
        XmlUserDo xmlUserDo = new XmlUserDo();
        xmlUserDo.setName("Shark");
        xmlUserDo.setInterestList(xmlInterestDoList);
        List<XmlUserDo> xmlUserDoList = new ArrayList<>();
        xmlUserDoList.add(xmlUserDo);
        return xmlUserDoList;
    }

    private String parserToXml(List<XmlUserDo> xmlUserDoList) {
        XmlMapper xmlMapper = new XmlMapper();
        String xml = null;
        try {
            xml = xmlMapper.writeValueAsString(xmlUserDoList);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return xml;
    }

    private List<XmlUserDo> parserToDo(String xml) {
        XmlMapper xmlMapper = new XmlMapper();
        List<XmlUserDo> xmlUserDoList = null;
        try {
            xmlUserDoList = xmlMapper.readValue(xml, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return xmlUserDoList;
    }
}
