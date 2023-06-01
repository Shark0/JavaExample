package com.shark.example.csv;

import com.google.gson.Gson;
import com.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ParserCsvExample2 {

    public static void main(String[] argv) {
        Gson gson = new Gson();
        HashMap<Integer, MmsMenuDto> idMenuHashMap = new HashMap<>();
        try(
                FileReader fileReader = new FileReader("file/mms_SYS_REVAMP_FUNC.csv");
                CSVReader csvReader = new CSVReader(fileReader);
        ) {
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                MmsMenuDto mmsMenuDto = generateMmsMenu(nextRecord);
                idMenuHashMap.put(mmsMenuDto.getId(), mmsMenuDto);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<MmsMenuDto> menuList = generateMmsMenuList(idMenuHashMap);
        System.out.println(new Gson().toJson(menuList));
    }

    private static MmsMenuDto generateMmsMenu(String[] nextRecord) {
        MmsMenuDto mmsMenuDto = new MmsMenuDto();
        mmsMenuDto.setId(Integer.valueOf(nextRecord[0]));
        mmsMenuDto.setParent(Integer.valueOf(nextRecord[1]));
        mmsMenuDto.setPosition(Integer.valueOf(nextRecord[2]));
        mmsMenuDto.setCode(nextRecord[3]);
        mmsMenuDto.setSla(nextRecord[4]);
        mmsMenuDto.setFuncNameEn(nextRecord[5]);
        mmsMenuDto.setFuncNameTw(nextRecord[7]);
        return mmsMenuDto;
    }

    private static List<MmsMenuDto> generateMmsMenuList(HashMap<Integer, MmsMenuDto> idMenuHashMap) {
        List<MmsMenuDto> menuList = new ArrayList<>();
        for(Integer id: idMenuHashMap.keySet()) {
            MmsMenuDto mmsMenuDto = idMenuHashMap.get(id);
            Integer parentId = mmsMenuDto.getParent();
            if(parentId == 0) {
                menuList.add(mmsMenuDto);
            } else {
                MmsMenuDto parent = idMenuHashMap.get(parentId);
                if(parent != null) {
                    List<MmsMenuDto> subMenuList = parent.getMenuList();
                    if(subMenuList == null) {
                        subMenuList = new ArrayList<>();
                        parent.setMenuList(subMenuList);
                    }
                    subMenuList.add(mmsMenuDto);
                }
            }
        }
        sortMenu(menuList);
        return menuList;
    }

    private static void sortMenu(List<MmsMenuDto> menuList) {
        Collections.sort(menuList, (v1, v2) -> {
            if(v1.getPosition() > v2.getPosition()) {
                return 1;
            } else if(v1.getPosition() == v2.getPosition()) {
                return 0;
            } else {
                return -1;
            }
        });

        for(MmsMenuDto menuDto: menuList) {
            if(menuDto.getMenuList() != null) {
                sortMenu(menuDto.getMenuList());
            }
        }
    }

}
