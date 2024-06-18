package com.shark.example.shoalter.jwt;

import lombok.Data;

@Data
public class UserDto {
    private Integer userId;
    private String ssoUserId;
    private String userCode;
    private String userName;
    private String email;
    private Integer merchantId;
    private String merchantName;
    private Integer roleId;
    private String roleCode;
    private String roleName;
    private String remark;
    private String roleType;
    private String buCodes;
    private String platformCodes;
    private String systemCode;
}
