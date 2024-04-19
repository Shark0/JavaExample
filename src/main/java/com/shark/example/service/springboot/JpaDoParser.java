package com.shark.example.service.springboot;

import java.io.*;

public class JpaDoParser {
    public static void main(String[] argv) throws IOException {
        File sqlFile = new File("mmsFile/parser.sql");
        BufferedReader sqlBufferReader = new BufferedReader(new FileReader(sqlFile));
        StringBuilder doCodeStringBuilder = new StringBuilder();
        doCodeStringBuilder.append("import java.time.Instant;").append("\n")
                .append("import java.math.BigDecimal;").append("\n")
                .append("import lombok.Data;").append("\n")
                .append("import javax.persistence.*;").append("\n");
        String sqlLine;
        while ((sqlLine = sqlBufferReader.readLine()) != null) {
            sqlLine = sqlLine.trim().toLowerCase();
            if(sqlLine.startsWith("create table")) {
                handleCreateTable(doCodeStringBuilder, sqlLine);
            } else if(sqlLine.contains("auto_increment")) {
                handleId(doCodeStringBuilder, sqlLine);
            } else if(sqlLine.contains("int") ||
                    sqlLine.contains("boolean") ||
                    sqlLine.contains("varchar") ||
                    sqlLine.contains("text") ||
                    sqlLine.contains("decimal") ||
                    sqlLine.contains("datetime") ||
                    sqlLine.contains("date") ) {
                handleParameter(doCodeStringBuilder, sqlLine);
            }
        }
        doCodeStringBuilder.append("}");
        System.out.println(doCodeStringBuilder);
    }

    private static void handleCreateTable(StringBuilder doCodeStringBuilder, String sqlLine) {
        String[] sqlContent = sqlLine.split(" ");
        String tableName = sqlContent[2];
        String[] tableNameArray = tableName.split("_");
        StringBuilder classNameStringBuilder = new StringBuilder();
        for(String subName: tableNameArray) {
            classNameStringBuilder.append(subName.substring(0, 1).toUpperCase()).append(subName.substring(1));
        }
        classNameStringBuilder.append("Do");
        doCodeStringBuilder.append("@Entity").append("\n")
                .append("@Table(name = \"").append(tableName.toUpperCase()).append("\")").append("\n")
                .append("@Data").append("\n")
                .append("public class ").append(classNameStringBuilder).append(" {").append("\n");
    }

    private static void handleId(StringBuilder doCodeStringBuilder, String sqlLine) {
        if(sqlLine.contains("auto_increment")) {
            doCodeStringBuilder.append("\t").append("@GeneratedValue(strategy = GenerationType.IDENTITY)").append("\n");
        }
        doCodeStringBuilder.append("\t").append("@Id").append("\n");
        String[] sqlContent = sqlLine.split(" ");
        String columnName = sqlContent[0];
        String parameterName = generateParameterName(columnName);
        String dataType = generateDataType(sqlLine);
        doCodeStringBuilder.append("\t").append("@Column(name = \"").append(columnName.toUpperCase()).append("\")").append("\n")
                .append("\t").append("private ").append(dataType).append(" ").append(parameterName).append(";").append("\n");
    }

    private static void handleParameter(StringBuilder doCodeStringBuilder, String sqlLine) {
        String[] sqlContentArray = sqlLine.split("\\s+");
        String columnType = sqlContentArray[1];
        String dataType = generateDataType(columnType);
        if(dataType == null) {
            return;
        }
        String columnName = sqlContentArray[0];
        String parameterName = generateParameterName(columnName);
        String columnDefinition = generateColumnDefinition(columnType);

        doCodeStringBuilder.append("\t").append("@Column(name = \"").append(columnName.toUpperCase());
        if(columnDefinition != null) {
            doCodeStringBuilder.append("\", columnDefinition=\"").append(columnDefinition);
        }
        doCodeStringBuilder.append("\")").append("\n")
                .append("\t").append("private ").append(dataType).append(" ").append(parameterName).append(";").append("\n");
    }

    private static String generateColumnDefinition(String columnTypeSql) {
        if(columnTypeSql.contains("tinyint(1)")) {
            return "BIT";
        } else if(columnTypeSql.contains("tinyint")) {
            return "TINYINT";
        } else if (columnTypeSql.contains("longtext")) {
            return "LONGTEXT";
        } else if(columnTypeSql.contains("text")) {
            return "TEXT";
        }
        return null;
    }

    private static String generateDataType(String columnTypeSql) {
        if(columnTypeSql.contains("bigint")) {
            return "Long";
        } else if (columnTypeSql.contains("tinyint(1)") || columnTypeSql.contains("boolean")) {
            return "Boolean";
        } else if(columnTypeSql.contains("int") || columnTypeSql.contains("tinyint")) {
            return "Integer";
        } else if(columnTypeSql.contains("varchar") || columnTypeSql.contains("text")) {
            return "String";
        } else if(columnTypeSql.contains("decimal")) {
            return "BigDecimal";
        } else if(columnTypeSql.contains("date") || columnTypeSql.contains("datetime") || columnTypeSql.contains("timestamp")) {
            return "Instant";
        }
        return null;
    }

    private static String generateParameterName(String columnName) {
        String[] parameterNameArray = columnName.split("_");
        StringBuilder parameterNameStringBuilder = new StringBuilder();
        for(int i = 0; i < parameterNameArray.length; i ++) {
            String subParameterName = parameterNameArray[i];
            if(i != 0) {
                subParameterName = subParameterName.substring(0, 1).toUpperCase() + subParameterName.substring(1);
            }
            parameterNameStringBuilder.append(subParameterName);
        }
        return parameterNameStringBuilder.toString();
    }
}
