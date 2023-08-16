package com.shark.example.shoalter.menu;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.shark.example.shoalter.menu.pojo.MmsMenuDto;
import com.shark.example.shoalter.menu.pojo.MmsMenuResponseDto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Mms2MenuJsonExample {

    private static final String PROFILE = "dev";

    public static void main(String[] argv) {
        List<String> funcCodeList = generateFuncCodeList();
        for(String funcCode: funcCodeList) {
            System.out.println(funcCode);
        }
    }

    private static List<String> generateFuncCodeList() {
        StringBuilder stringBuilder = new StringBuilder();
        try(FileReader fileReader = new FileReader("mmsFile/menu/" + PROFILE + "/revamp_menu.json");
            BufferedReader reader = new BufferedReader(fileReader)) {
            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String menuJson = stringBuilder.toString();
        Gson gson = new Gson();
        MmsMenuResponseDto mmsMenuResponseDto = gson.fromJson(menuJson, new TypeToken<MmsMenuResponseDto>(){}.getType());
        return generateFuncCodeList(mmsMenuResponseDto.getData());
    }

    private static List<String> generateFuncCodeList(List<MmsMenuDto> mmsMenuDtoList) {
        List<String> funcCodeList = new ArrayList<>();
        for(MmsMenuDto mmsMenuDto: mmsMenuDtoList) {
            if(mmsMenuDto.getCanView()) {
                String funcCode = mmsMenuDto.getFuncName();
                funcCodeList.add(funcCode);
                List<MmsMenuDto> childList = mmsMenuDto.getMenuList();
                if(childList != null && childList.size() > 0) {
                       funcCodeList.addAll(generateFuncCodeList(childList));
                }
            }
        }
        return funcCodeList;
    }
}
