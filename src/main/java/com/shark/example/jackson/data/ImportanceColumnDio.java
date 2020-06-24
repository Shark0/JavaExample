package com.shark.example.jackson.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class ImportanceColumnDio implements Serializable {
    @SerializedName("columnId")
    @JsonProperty("columnId")
    private Long columnId;
    @SerializedName("weight")
    @JsonProperty("weight")
    private Double weight;
}
