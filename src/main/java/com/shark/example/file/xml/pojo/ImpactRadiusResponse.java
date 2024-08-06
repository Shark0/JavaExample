package com.shark.example.file.xml.pojo;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import java.util.List;


@Data
public class ImpactRadiusResponse {
	@JacksonXmlElementWrapper(localName = "Reports")
	@JacksonXmlProperty(localName = "Report")
	private List<ImpactReport> reports;
}
