package com.shark.example.algolia;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookDto {
    @JsonProperty("objectID")
    private String objectId;
    private String name;
    private String description;
    private BigDecimal price;
}
