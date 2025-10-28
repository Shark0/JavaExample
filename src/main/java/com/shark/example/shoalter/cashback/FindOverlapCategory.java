package com.shark.example.shoalter.cashback;

import com.google.gson.Gson;
import com.opencsv.CSVReader;
import com.shark.example.shoalter.cashback.pojo.CategoryDo;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.*;

public class FindOverlapCategory {

    public void start() {
        String fileName = "D:/Shark/JavaExample/file/shoalter/category.csv";
        List<CategoryDo> categoryDoList = mapExcelToCategoryList(fileName);
        List<Set<Long>> overlapCategorySetList = findOverlapCategorySetList(categoryDoList);
        System.out.println("overlap category set: " + new Gson().toJson(overlapCategorySetList));

    }

    private List<CategoryDo> mapExcelToCategoryList(String fileName) {
        List<CategoryDo> categoryDoList = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try (CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(fileName)))) {
            // Skip header
            reader.readNext();
            String[] values;
            while ((values = reader.readNext()) != null) {
                CategoryDo categoryDo = new CategoryDo();
                categoryDo.setId(getLong(values, 0));
                categoryDo.setPlatform(getString(values, 1));
                categoryDo.setStoreId(getInteger(values, 2));
                categoryDo.setRebateSettingNameEn(getString(values, 3));
                categoryDo.setRebateSettingNameCh(getString(values, 4));
                categoryDo.setRebateSettingType(getString(values, 5));
                categoryDo.setIdentify1(getString(values, 6));
                categoryDo.setIdentify2(getString(values, 7));
                categoryDo.setIdentify3(getString(values, 8));
                categoryDo.setIdentify4(getString(values, 9));
                categoryDo.setIdentify5(getString(values, 10));
                categoryDo.setIdentify6(getString(values, 11));
                categoryDo.setIdentify7(getString(values, 12));
                categoryDo.setIdentify8(getString(values, 13));
                categoryDo.setIdentify9(getString(values, 14));
                categoryDo.setIdentify10(getString(values, 15));
                categoryDo.setRebateRate(getBigDecimal(values, 16));
                categoryDo.setFixedAmount(getBigDecimal(values, 17));
                categoryDo.setDealCommissionRate(getBigDecimal(values, 18));
                categoryDo.setDealCommissionFixedAmount(getBigDecimal(values, 19));
                categoryDo.setRebateStartDate(getDate(values, 20, formatter));
                categoryDo.setRebateEndDate(getDate(values, 21, formatter));
                categoryDo.setRebateSettingStatus(getString(values, 22));
                categoryDoList.add(categoryDo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoryDoList;
    }

    private String getString(String[] values, int index) {
        if (values.length > index && values[index] != null && !values[index].isEmpty()) {
            return values[index];
        }
        return "";
    }

    private Integer getInteger(String[] values, int index) {
        String value = getString(values, index);
        if (!value.isEmpty()) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    private Long getLong(String[] values, int index) {
        String value = getString(values, index);
        if (!value.isEmpty()) {
            try {
                return Long.parseLong(value);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    private BigDecimal getBigDecimal(String[] values, int index) {
        String value = getString(values, index);
        if (!value.isEmpty()) {
            try {
                return new BigDecimal(value);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    private Date getDate(String[] values, int index, SimpleDateFormat formatter) {
        String value = getString(values, index);
        if (!value.isEmpty()) {
            try {
                return formatter.parse(value);
            } catch (DateTimeParseException e) {
                return null;
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    private List<Set<Long>> findOverlapCategorySetList(List<CategoryDo> categoryDoList) {

        Map<String, List<CategoryDo>> categoryIdentifyMap = new HashMap<>();

        for (CategoryDo categoryDo : categoryDoList) {
            if (!"ACTIVE".equalsIgnoreCase(categoryDo.getRebateSettingStatus())) {
                continue;
            }
            String key = String.join("_",
                    String.valueOf(categoryDo.getPlatform()),
                    String.valueOf(categoryDo.getStoreId()),
                    String.valueOf(categoryDo.getRebateSettingType()),
                    String.valueOf(categoryDo.getIdentify1()),
                    String.valueOf(categoryDo.getIdentify2()),
                    String.valueOf(categoryDo.getIdentify3()),
                    String.valueOf(categoryDo.getIdentify4()),
                    String.valueOf(categoryDo.getIdentify5()),
                    String.valueOf(categoryDo.getIdentify6()),
                    String.valueOf(categoryDo.getIdentify7()),
                    String.valueOf(categoryDo.getIdentify8()),
                    String.valueOf(categoryDo.getIdentify9()),
                    String.valueOf(categoryDo.getIdentify10()));

            List<CategoryDo> categoryIdentifyGroup = categoryIdentifyMap.getOrDefault(key, new ArrayList<>());
            categoryIdentifyGroup.add(categoryDo);
            categoryIdentifyMap.put(key, categoryIdentifyGroup);
        }

        List<Set<Long>> overlapCategorySetList = new ArrayList<>();
        for (String key : categoryIdentifyMap.keySet()) {
            List<CategoryDo> sameIdentifyCategoryList = categoryIdentifyMap.get(key);
            Set<Long> overlapCategorySet = generateOverlapCategory(sameIdentifyCategoryList);
            if (!overlapCategorySet.isEmpty()) {
                overlapCategorySetList.add(overlapCategorySet);
            }
        }
        return overlapCategorySetList;
    }

    private Set<Long> generateOverlapCategory(List<CategoryDo> categoryDos) {
        Set<Long> overlapSet = new HashSet<>();
        if (categoryDos.size() < 2) {
            return overlapSet;
        }

        categoryDos.sort(Comparator.comparing(CategoryDo::getRebateStartDate, Comparator.nullsFirst(Comparator.naturalOrder())));


        for (int i = 0; i < categoryDos.size(); i++) {
            for (int j = i + 1; j < categoryDos.size(); j++) {
                CategoryDo cat1 = categoryDos.get(i);
                CategoryDo cat2 = categoryDos.get(j);

                Date start1 = cat1.getRebateStartDate();
                Date end1 = cat1.getRebateEndDate();
                Date start2 = cat2.getRebateStartDate();
                Date end2 = cat2.getRebateEndDate();

                if (start1 == null || start2 == null) {
                    continue;
                }

                boolean noOverlap = (end1 != null && !end1.after(start2)) || (end2 != null && !end2.after(start1));

                if (!noOverlap) {
                    overlapSet.add(cat1.getId());
                    overlapSet.add(cat2.getId());
                }
            }
        }
        return overlapSet;
    }


    public static void main(String[] args) {
        FindOverlapCategory findOverlapCategory = new FindOverlapCategory();
        findOverlapCategory.start();
    }
}
