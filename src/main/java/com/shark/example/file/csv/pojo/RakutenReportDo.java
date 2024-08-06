package com.shark.example.file.csv.pojo;

import lombok.Data;


@Data
public class RakutenReportDo
{
	private String MID;
	private String advertiserName;
	private String memberId;
	private String orderId;
	private String transactionDate;
	private String transactionTime;
	private String sku;
	private String sales;
	private String items;
	private String cancelledItems;
	private String baselineCommission;
	private String adjustedCommission;
	private String transactionId;
	private String totalCommission;
	private String processDate;
	private String processTime;
	private String commissionStatus;
	private String productName;
	private String productCategory;
	private String networkId;
}
