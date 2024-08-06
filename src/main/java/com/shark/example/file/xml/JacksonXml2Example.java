package com.shark.example.file.xml;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.Gson;
import com.shark.example.file.txt.ReadTxtFileExample;
import com.shark.example.file.xml.pojo.ImpactRadiusResponse;

public class JacksonXml2Example {
    public static void main(String[] args) {
        String filePath = "file/xml_example2.xml";
        ReadTxtFileExample readTxtFileExample = new ReadTxtFileExample();
        String xml = readTxtFileExample.readFile(filePath);
        XmlMapper xmlMapper = new XmlMapper();
        try {
            ImpactRadiusResponse impactRadiusResponse = xmlMapper.readValue(xml, ImpactRadiusResponse.class);
            Gson gson = new Gson();
            System.out.println(gson.toJson(impactRadiusResponse));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


    }
}
