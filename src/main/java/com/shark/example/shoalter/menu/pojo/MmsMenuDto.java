package com.shark.example.shoalter.menu.pojo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class MmsMenuDto implements Serializable {
  private String funcName;

  private Boolean canView;

  private Boolean sla;

  @SerializedName("listFuc")
  private List<MmsMenuDto> menuList;
}
