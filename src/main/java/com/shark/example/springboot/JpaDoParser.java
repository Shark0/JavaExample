package com.shark.example.springboot;

import java.io.*;

public class JpaDoParser {
    public static void main(String[] argv) throws IOException {
        File sqlFile = new File("file/parser.sql");
        BufferedReader sqlBufferReader = new BufferedReader(new FileReader(sqlFile));
        StringBuilder doCodeStringBuilder = new StringBuilder();
        doCodeStringBuilder.append("import java.util.Date;").append("\n")
                .append("import java.math.BigDecimal;").append("\n")
                .append("import lombok.Data;").append("\n").append("import javax.persistence.*;").append("\n");
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
        doCodeStringBuilder.append("\t").append("@Column(name = \"").append(columnName).append("\")").append("\n")
                .append("\t").append("private ").append(dataType).append(" ").append(parameterName).append(";").append("\n");
    }

    private static void handleParameter(StringBuilder doCodeStringBuilder, String sqlLine) {
        String[] sqlContent = sqlLine.split("\\s+");
        String columnName = sqlContent[0];
        String parameterName = generateParameterName(columnName);
        String dataType = generateDataType(sqlContent[1]);
        doCodeStringBuilder.append("\t").append("@Column(name = \"").append(columnName.toUpperCase());
        if(sqlLine.contains("text")) {
            doCodeStringBuilder.append("\", columnDefinition=\"text");
        }
        doCodeStringBuilder.append("\")").append("\n")
                .append("\t").append("private ").append(dataType).append(" ").append(parameterName).append(";").append("\n");
    }

    private static String generateDataType(String subSql) {
        if(subSql.contains("bigint")) {
            return "Long";
        } else if(subSql.contains("int")) {
            return "Integer";
        } else if(subSql.contains("boolean")) {
            return "Boolean";
        } else if(subSql.contains("varchar")) {
            return "String";
        } else if(subSql.contains("text")) {
            return "String";
        } else if(subSql.contains("decimal")) {
            return "BigDecimal";
        } else if(subSql.contains("datetime")) {
            return "Date";
        } else if(subSql.contains("date")) {
            return "Date";
        }
        return "String";
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
