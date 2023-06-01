package com.shark.example.mms.pojo;

import lombok.Data;

@Data
public class RoleFuncDto {
    private String roleId;
    private String funcId;
    private String canSelect;
    private String canInsert;
    private String canUpdate;
    private String canDelete;
    private String cantAudit;
    private String cantView;
}
