package com.shark.example.jackson.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ImportanceComponentInput implements Serializable {
    @JsonProperty("importantColumnList")
    @SerializedName("importantColumnList")
    List<ImportanceColumnDio> importantColumnList;
}
