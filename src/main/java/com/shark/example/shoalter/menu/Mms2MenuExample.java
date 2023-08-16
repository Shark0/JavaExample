package com.shark.example.shoalter.menu;

import com.google.gson.Gson;
import com.shark.example.shoalter.menu.pojo.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Mms2MenuExample {

    private static final String PROFILE = "prod";

    public static void main(String[] argv) {
        List<BuDo> buDoList = generateBuList();
        List<FuncBuDo> funcBuDoList = generateFucBuList();

        List<SysRoleDo> roleDoList = generateRoleList();
        List<SysFuncDo> funcDoList = generateFuncList();
        List<SysRoleFuncDo> roleFuncDoList = generateRoleFuncList();
        List<SysRevampFuncDo> revampFuncDoList = generateRevampFuncList();
        List<SysRevampRoleFuncDo> revampRoleFuncDoList = generateRevampRoleFuncList();
        List<RoleMenuDto> roleMenuDtoList =
                generateRoleMenuDtoList(buDoList, funcBuDoList, roleDoList, funcDoList, roleFuncDoList,
                        revampFuncDoList, revampRoleFuncDoList);
        System.out.println(new Gson().toJson(roleMenuDtoList));
        generateCsv(roleMenuDtoList);
    }

    private static List<BuDo> generateBuList() {
        List<BuDo> list = new ArrayList<>();
        try(FileReader fileReader = new FileReader("mmsFile/menu/" + PROFILE + "/bu.txt");
            BufferedReader reader = new BufferedReader(fileReader)) {
            String line = reader.readLine();
            int index = 0;
            while (line != null) {
                if(index > 0) {
                    String[] values = line.split("\t");
                    BuDo buDo = new BuDo();
                    buDo.setId(Integer.valueOf(values[0]));
                    buDo.setBuCode(values[1]);
                    list.add(buDo);
                }
                index ++;
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static List<FuncBuDo> generateFucBuList() {
        List<FuncBuDo> list = new ArrayList<>();
        try(FileReader fileReader = new FileReader("mmsFile/menu/" + PROFILE + "/func_bu.txt");
            BufferedReader reader = new BufferedReader(fileReader)) {
            String line = reader.readLine();
            int index = 0;
            while (line != null) {
                if(index > 0) {
                    String[] values = line.split("\t");
                    FuncBuDo funcBuDo = new FuncBuDo();
                    funcBuDo.setFuncId(Integer.valueOf(values[1]));
                    funcBuDo.setBuId(Integer.valueOf(values[2]));
                    list.add(funcBuDo);
                }
                index ++;
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static List<SysRoleDo> generateRoleList() {
        List<SysRoleDo> list = new ArrayList<>();
        try(FileReader fileReader = new FileReader("mmsFile/menu/" + PROFILE + "/role.txt");
            BufferedReader reader = new BufferedReader(fileReader)) {
            String line = reader.readLine();
            int index = 0;
            while (line != null) {
                if(index > 0) {
                    String[] values = line.split("\t");
                    String active = values[5];
                    if("Y".equalsIgnoreCase(active)) {
                        SysRoleDo sysRoleDo = new SysRoleDo();
                        sysRoleDo.setId(Integer.valueOf(values[0]));
                        sysRoleDo.setRoleCode(values[1]);
                        sysRoleDo.setRoleName(values[2]);
                        sysRoleDo.setRoleType(values[7]);
                        list.add(sysRoleDo);
                    }
                }
                index ++;
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static List<SysFuncDo> generateFuncList() {
        List<SysFuncDo> list = new ArrayList<>();
        try(FileReader fileReader = new FileReader("mmsFile/menu/" + PROFILE + "/func.txt");
            BufferedReader reader = new BufferedReader(fileReader)) {
            String line = reader.readLine();
            int index = 0;
            while (line != null) {
                if(index > 0) {
                    String[] values = line.split("\t");
                    SysFuncDo sysFuncDo = new SysFuncDo();
                    sysFuncDo.setId(Integer.valueOf(values[0]));
                    sysFuncDo.setFuncCode(values[1]);
                    sysFuncDo.setType(values[3]);
                    sysFuncDo.setLevel1Pos(Integer.valueOf(values[4]));
                    sysFuncDo.setLevel2Pos(Integer.valueOf(values[5]));
                    sysFuncDo.setFuncNameEn(values[7]);
                    sysFuncDo.setFuncNameZhCn(values[8]);
                    sysFuncDo.setFuncNameZhTw(values[9]);
                    list.add(sysFuncDo);
                }
                index ++;
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    private static List<SysRoleFuncDo> generateRoleFuncList() {
        List<SysRoleFuncDo> list = new ArrayList<>();
        try(FileReader fileReader = new FileReader("mmsFile/menu/" + PROFILE + "/role_func.txt");
                BufferedReader reader = new BufferedReader(fileReader)) {
            String line = reader.readLine();
            int index = 0;
            while (line != null) {
                if(index > 0) {
                    String[] values = line.split("\t");
                    SysRoleFuncDo sysRoleFuncDo = new SysRoleFuncDo();
                    sysRoleFuncDo.setId(Integer.valueOf(values[0]));
                    sysRoleFuncDo.setRoleId(Integer.valueOf(values[1]));
                    sysRoleFuncDo.setFuncId(Integer.valueOf(values[2]));
                    sysRoleFuncDo.setCanView(values[8]);
                    sysRoleFuncDo.setActive(values[9]);
                    list.add(sysRoleFuncDo);
                }
                index ++;
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static List<SysRevampFuncDo> generateRevampFuncList() {
        List<SysRevampFuncDo> list = new ArrayList<>();
        try(FileReader fileReader = new FileReader("mmsFile/menu/" + PROFILE + "/revamp_func.txt");
            BufferedReader reader = new BufferedReader(fileReader)) {
            String line = reader.readLine();
            int index = 0;
            while (line != null) {
                if(index > 0) {
                    String[] values = line.split("\t");
                    SysRevampFuncDo sysRevampFuncDo = new SysRevampFuncDo();
                    sysRevampFuncDo.setId(Integer.valueOf(values[0]));
                    sysRevampFuncDo.setParentFuncId(Integer.valueOf(values[1]));
                    sysRevampFuncDo.setPosition(Integer.valueOf(values[2]));
                    sysRevampFuncDo.setFuncCode(values[3]);
                    sysRevampFuncDo.setSlaService(values[4]);
                    sysRevampFuncDo.setFuncNameEn(values[5]);
                    sysRevampFuncDo.setFuncNameCn(values[6]);
                    sysRevampFuncDo.setFuncNameTw(values[7]);
                    list.add(sysRevampFuncDo);
                }
                index ++;
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static List<SysRevampRoleFuncDo> generateRevampRoleFuncList() {
        List<SysRevampRoleFuncDo> list = new ArrayList<>();
        try(FileReader fileReader = new FileReader("mmsFile/menu/" + PROFILE + "/revamp_role_func.txt");
            BufferedReader reader = new BufferedReader(fileReader)) {
            String line = reader.readLine();
            int index = 0;
            while (line != null) {
                if(index > 0) {
                    String[] values = line.split("\t");
                    SysRevampRoleFuncDo sysRevampRoleFuncDo = new SysRevampRoleFuncDo();
                    sysRevampRoleFuncDo.setId(Integer.valueOf(values[0]));
                    sysRevampRoleFuncDo.setRoleId(Integer.valueOf(values[1]));
                    sysRevampRoleFuncDo.setFuncId(Integer.valueOf(values[2]));
                    sysRevampRoleFuncDo.setActive("1".equalsIgnoreCase(values[3]));
                    list.add(sysRevampRoleFuncDo);
                }
                index ++;
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static String generateFunc(List<MenuDto> menuList) {
        if(menuList != null) {
            StringBuilder stringBuilder = new StringBuilder();
            for (MenuDto menuDto: menuList) {
                String func = menuDto.getName() + " (" + menuDto.getCode() + ")";
                stringBuilder.append(func).append("\n");
                if(menuDto.getMenuList() != null && menuDto.getMenuList().size() > 0) {
                    stringBuilder.append(generateFunc(menuDto.getMenuList()));
                }
            }
            return stringBuilder.toString();
        }
        return "";
    }

    private static List<RoleMenuDto> generateRoleMenuDtoList(
            List<BuDo> buDoList, List<FuncBuDo> funcBuDoList, List<SysRoleDo> sysRoleDoList, List<SysFuncDo> sysFuncDoList, List<SysRoleFuncDo> sysRoleFuncDoList,
            List<SysRevampFuncDo> revampFuncDoList, List<SysRevampRoleFuncDo> revampRoleFuncDoList) {
        Map<Integer, BuDo> activeBuIdBuDoMap = new HashMap<>();
        for(BuDo buDo: buDoList) {
            String buCode = buDo.getBuCode();
            if("HKTV".equalsIgnoreCase(buCode) || "LITTLE_MALL".equalsIgnoreCase(buCode)) {
                activeBuIdBuDoMap.put(buDo.getId(), buDo);
            }
        }

        HashMap<String, Set<Integer>> buCodeFuncIdSetHashMap = new HashMap<>();
        for(FuncBuDo funcBuDo: funcBuDoList) {
            Integer buId = funcBuDo.getBuId();
            BuDo buDo = activeBuIdBuDoMap.get(buId);
            if(buDo == null) {
                continue;
            }
            String buCode = buDo.getBuCode();
            Set<Integer> funcIdSet = buCodeFuncIdSetHashMap.computeIfAbsent(buCode, k -> new HashSet<>());
            funcIdSet.add(funcBuDo.getFuncId());
        }

        List<RoleMenuDto> roleMenuDtoList = new ArrayList<>();
        for(SysRoleDo sysRoleDo: sysRoleDoList) {
            RoleMenuDto roleMenuDto = new RoleMenuDto();
            roleMenuDto.setRoleId(sysRoleDo.getId());
            roleMenuDto.setRoleCode(sysRoleDo.getRoleCode());
            roleMenuDto.setRoleName(sysRoleDo.getRoleName());

            Map<String, List<MenuDto>> buCodeMenuDtoListMap = new HashMap<>();

            for(String buCode: buCodeFuncIdSetHashMap.keySet()) {
                Set<Integer> buFuncIdSet = buCodeFuncIdSetHashMap.get(buCode);
                List<MenuDto> buMenuList = generateBuMenuDtoList(
                        sysRoleDo, buFuncIdSet, sysFuncDoList, sysRoleFuncDoList, revampFuncDoList, revampRoleFuncDoList);
                buCodeMenuDtoListMap.put(buCode, buMenuList);
            }
            roleMenuDto.setBuCodeMenuListHashMap(buCodeMenuDtoListMap);
            roleMenuDtoList.add(roleMenuDto);
        }
        return roleMenuDtoList;
    }

    private static List<MenuDto> generateBuMenuDtoList(
            SysRoleDo sysRoleDo, Set<Integer> buFuncIdSet, List<SysFuncDo> sysFuncDoList,
            List<SysRoleFuncDo> sysRoleFuncDoList, List<SysRevampFuncDo> revampFuncDoList,
            List<SysRevampRoleFuncDo> revampRoleFuncDoList) {

        Map<Integer, SysFuncDo> funcIdFuncDoMap = new HashMap<>();
        for(SysFuncDo sysFuncDo: sysFuncDoList) {
            if(buFuncIdSet.contains(sysFuncDo.getId())) {
                funcIdFuncDoMap.put(sysFuncDo.getId(), sysFuncDo);
            }
        }

        Map<Integer, SysRevampFuncDo> revampFuncIdFuncDoMap = new HashMap<>();
        for(SysRevampFuncDo sysRevampFuncDo: revampFuncDoList) {
            revampFuncIdFuncDoMap.put(sysRevampFuncDo.getId(), sysRevampFuncDo);
        }

        Map<String, SysRoleFuncDo> roleCodeFuncCodeRoleFuncDoMap = new HashMap<>();
        for(SysRoleFuncDo sysRoleFuncDo: sysRoleFuncDoList) {
            if(Objects.equals(sysRoleFuncDo.getRoleId(), sysRoleDo.getId())) {
                SysFuncDo sysFuncDo = funcIdFuncDoMap.get(sysRoleFuncDo.getFuncId());
                if(sysFuncDo == null) {
                    continue;
                }
                roleCodeFuncCodeRoleFuncDoMap.put(sysRoleDo.getRoleCode() + "_" + sysFuncDo.getFuncCode(), sysRoleFuncDo);
            }
        }

        Map<String, SysRevampRoleFuncDo> revampRoleCodeFuncCodeRoleFuncDoMap = new HashMap<>();
        for(SysRevampRoleFuncDo sysRevampRoleFuncDo: revampRoleFuncDoList) {
            if(Objects.equals(sysRevampRoleFuncDo.getRoleId(), sysRoleDo.getId())) {
                SysRevampFuncDo sysRevampFuncDo = revampFuncIdFuncDoMap.get(sysRevampRoleFuncDo.getFuncId());
                if(sysRevampFuncDo == null) {
                    continue;
                }
                revampRoleCodeFuncCodeRoleFuncDoMap.put(sysRoleDo.getRoleCode() + "_" + sysRevampFuncDo.getFuncCode(), sysRevampRoleFuncDo);
            }
        }

        List<SysRevampFuncDo> sysRevampFuncDoList = new ArrayList<>();
        for(String roleCodeFuncCode: revampRoleCodeFuncCodeRoleFuncDoMap.keySet()) {
            SysRoleFuncDo sysRoleFuncDo = roleCodeFuncCodeRoleFuncDoMap.get(roleCodeFuncCode);
            SysRevampRoleFuncDo sysRevampRoleFuncDo = revampRoleCodeFuncCodeRoleFuncDoMap.get(roleCodeFuncCode);
            SysRevampFuncDo sysRevampFuncDo = revampFuncIdFuncDoMap.get(sysRevampRoleFuncDo.getFuncId());
            if(sysRoleFuncDo == null) {
                if(sysRevampRoleFuncDo.getActive()) {
                    sysRevampFuncDoList.add(sysRevampFuncDo);
                }
            } else {
                if("Y".equalsIgnoreCase(sysRoleFuncDo.getActive()) &&
                        "Y".equalsIgnoreCase(sysRoleFuncDo.getCanView())) {
                    sysRevampFuncDoList.add(sysRevampFuncDo);
                }
            }
        }

        return generateMenuDtoList(sysRevampFuncDoList);
    }

    private static List<MenuDto> generateMenuDtoList(List<SysRevampFuncDo> sysRevampFuncDoList) {
        Map<Integer, List<MenuDto>> parentIdMenuListMap = new HashMap<>();
        for(SysRevampFuncDo sysRevampFuncDo: sysRevampFuncDoList) {
            Integer parentId = sysRevampFuncDo.getParentFuncId();
            Integer funcId = sysRevampFuncDo.getId();
            List<MenuDto> parentMenuDtoList = parentIdMenuListMap.computeIfAbsent(parentId, k -> new ArrayList<>());
            List<MenuDto> childMenuDtoList = parentIdMenuListMap.computeIfAbsent(funcId, k -> new ArrayList<>());

            MenuDto menuDto = new MenuDto();
            menuDto.setId(sysRevampFuncDo.getId());
            menuDto.setName(sysRevampFuncDo.getFuncNameEn());
            menuDto.setCode(sysRevampFuncDo.getFuncCode());
            menuDto.setPosition(sysRevampFuncDo.getPosition());
            menuDto.setMenuList(childMenuDtoList);
            parentMenuDtoList.add(menuDto);
        }
        Comparator<MenuDto> comparator = Comparator.comparingInt(MenuDto::getPosition);
        for(Integer parentId: parentIdMenuListMap.keySet()) {
            List<MenuDto> menuDtoList = parentIdMenuListMap.get(parentId);
            menuDtoList.sort(comparator);
        }
        return parentIdMenuListMap.get(0);
    }

    private static void generateCsv(List<RoleMenuDto> roleMenuDtoList) {
        String filePath = "mmsFile/menu/" + PROFILE +  "/mms2_role_func.xlsx";
        try(Workbook workbook = new XSSFWorkbook();
            FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
            Sheet sheet = workbook.createSheet("role_func");
            String[] columns = {"ROLE", "HKTV FUNC", "LITTLE MALL FUNC"};
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
            }

            int rowIndex = 1;
            for (RoleMenuDto roleMenuDto: roleMenuDtoList) {
                Row row = sheet.createRow(rowIndex);
                String role = roleMenuDto.getRoleName() + " (" + roleMenuDto.getRoleCode() + ")";
                row.createCell(0).setCellValue(role);
                String hktvFunc = generateFunc(roleMenuDto.getBuCodeMenuListHashMap().get("HKTV"));
                row.createCell(1).setCellValue(hktvFunc);
                String littleMallFunc = generateFunc(roleMenuDto.getBuCodeMenuListHashMap().get("LITTLE_MALL"));
                row.createCell(2).setCellValue(littleMallFunc);
                rowIndex = rowIndex + 1;
            }
            workbook.write(fileOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
