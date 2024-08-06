package com.shark.example.file.xml.pojo;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;


@Data
public class ImpactReport {

	@JacksonXmlProperty(localName = "Name")
	private String name;
	@JacksonXmlProperty(localName = "Id")
	private String id;
	@JacksonXmlProperty(localName = "Description")
	private String description;
	@JacksonXmlProperty(localName = "ApiAccessible")
	private String apiAccessible;
	@JacksonXmlProperty(localName = "ApiRunUri")
	private String apiRunUri;
	@JacksonXmlProperty(localName = "DeferredApiRunUri")
	private String deferredApiRunUri;
	@JacksonXmlProperty(localName = "RunUri")
	private String runUri;
	@JacksonXmlProperty(localName = "MetaDataUri")
	private String metaDataUri;
}
