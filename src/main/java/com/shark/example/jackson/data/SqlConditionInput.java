package com.shark.example.jackson.data;

import com.fasterxml.jackson.annotation.*;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class SqlConditionInput implements Serializable {

    @JsonProperty(value = "type")
    @SerializedName(value = "type")
    private SqlConditionType type;

    @JsonProperty(value = "subSqlCondition")
    @SerializedName(value = "subSqlCondition")
    private SqlConditionInput subSqlCondition;

    @JsonProperty(value = "columnName")
    @SerializedName(value = "columnName")
    private String columnName;

    @JsonProperty(value = "comparisonOperatorType")
    @SerializedName(value = "comparisonOperatorType")
    private ComparisonOperatorType comparisonOperatorType;

    @JsonProperty(value = "valueList")
    @SerializedName(value = "valueList")
    private List<String> valueList;

    @JsonProperty(value = "nextLogicOperator")
    @SerializedName(value = "nextLogicOperator")
    protected LogicOperatorType nextLogicOperator;

    @JsonProperty(value = "next")
    @SerializedName(value = "next")
    protected SqlConditionInput next;

    public void setNext(LogicOperatorType nextLogicOperator, SqlConditionInput nextCondition) {
        this.nextLogicOperator = nextLogicOperator;
        this.next = nextCondition;
    }

    public SqlConditionInput findLastCondition() {
        SqlConditionInput currentCondition = this;
        while (currentCondition.getNext() != null) {
            currentCondition = currentCondition.getNext();
        }
        return currentCondition;
    }

    public String toSql() {
        StringBuilder stringBuilder = new StringBuilder();
        switch (type) {
            case NORMAL:
                stringBuilder.append(appendComparison()).append(appendNext());
                break;
            case SUB:
                stringBuilder.append("(").append(subSqlCondition.toSql()).append(")").append(appendNext());
                break;
        }
        return stringBuilder.toString();
    }

    private String appendComparison() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(columnName);
        switch (comparisonOperatorType) {
            case EQUAL:
                stringBuilder.append(" = ").append(valueList.get(0));
                break;
            case NOT_EQUAL:
                stringBuilder.append(" != ").append(valueList.get(0));
                break;
            case GREATER_THAN:
                stringBuilder.append(" > ").append(valueList.get(0));
                break;
            case GREATER_THAN_OR_EQUAL_TO:
                stringBuilder.append(" >= ").append(valueList.get(0));
                break;
            case LESS_THAN:
                stringBuilder.append(" < ").append(valueList.get(0));
                break;
            case LESS_THAN_OR_EQUAL_TO:
                stringBuilder.append(" <= ").append(valueList.get(0));
                break;
            case IN:
                stringBuilder.append(" in ").append("(");
                int index = 0;
                for (String value : valueList) {
                    if (index != 0) {
                        stringBuilder.append(",");
                    }
                    stringBuilder.append(value);
                    index = index + 1;
                }
                stringBuilder.append(")");
                break;
            case NOT_IN:
                stringBuilder.append(" not in ").append("(");
                index = 0;
                for (String value : valueList) {
                    if (index != 0) {
                        stringBuilder.append(",");
                    }
                    stringBuilder.append(value);
                    index = index + 1;
                }
                stringBuilder.append(")");
                break;
            case BETWEEN:
                stringBuilder.append(" between ").append(valueList.get(0))
                        .append(" and ").append(valueList.get(1));
                break;
        }

        return stringBuilder.toString();
    }

    private String appendNext() {
        if (next == null) {
            return "";
        } else {
            return " " + nextLogicOperator.toString() + " " + next.toSql();
        }
    }

}

