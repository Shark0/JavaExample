package com.shark.example.shoalter.cashback.pojo;


import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;


@Data
public class CategoryDo {

    private Long id;

    private String platform;

    private Integer storeId;

    private String rebateSettingNameEn;

    private String rebateSettingNameCh;

    private String rebateSettingType;

    private String identify1;

    private String identify2;

    private String identify3;

    private String identify4;

    private String identify5;

    private String identify6;

    private String identify7;

    private String identify8;

    private String identify9;

    private String identify10;

    private BigDecimal rebateRate;

    private BigDecimal fixedAmount;

    private BigDecimal dealCommissionRate;

    private BigDecimal dealCommissionFixedAmount;

    private BigDecimal rebateCap;

    private Date rebateStartDate;

    private Date rebateEndDate;

    private String rebateSettingStatus;

    private Date createdTime;

    private Date lastUpdateTime;

    private String createdBy;

    private String lastUpdatedBy;
}
