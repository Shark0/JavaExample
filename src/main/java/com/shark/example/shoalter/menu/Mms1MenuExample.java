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

public class Mms1MenuExample {

    private static final String PROFILE = "prod";

    public static void main(String[] argv) {
        List<SysRoleDo> roleList = generateRoleList();
        List<SysFuncDo> funcDoList = generateFuncList();
        List<SysRoleFuncDo> roleFuncDoList = generateRoleFuncList();

        Map<Integer, SysFuncDo> funcIdFuncDoMap = new HashMap<>();
        for(SysFuncDo sysFuncDo: funcDoList) {
            funcIdFuncDoMap.put(sysFuncDo.getId(), sysFuncDo);
        }

        Map<Integer, Set<Integer>> roleIdFuncIdSetMap = new HashMap<>();
        for(SysRoleFuncDo sysRoleFuncDo: roleFuncDoList) {
            Integer roleId = sysRoleFuncDo.getRoleId();
            Set<Integer> funcCodeSet = roleIdFuncIdSetMap.computeIfAbsent(roleId, k -> new HashSet<>());
            if("Y".equalsIgnoreCase(sysRoleFuncDo.getActive()) &&
                    "Y".equalsIgnoreCase(sysRoleFuncDo.getCanView())) {
                SysFuncDo sysFuncDo = funcIdFuncDoMap.get(sysRoleFuncDo.getFuncId());
                if(sysFuncDo != null) {
                    funcCodeSet.add(sysFuncDo.getId());
                }
            }
        }

        Map<Integer, SysRoleDo> roleIdRoleDoMap = new HashMap<>();
        Map<Integer, List<SysFuncDo>> roleIdFuncListMap = new HashMap<>();
        for(SysRoleDo sysRoleDo: roleList) {
            Integer roleId = sysRoleDo.getId();
            Set<Integer> funcIdSet = roleIdFuncIdSetMap.get(roleId);
            System.out.println("roleId: " + roleId + ", funcIdSet: " + new Gson().toJson(funcIdSet));
            if(funcIdSet != null && funcIdSet.size() > 0) {
                List<SysFuncDo> rolesFuncDoList = new ArrayList<>();
                for(Integer funcId: funcIdSet) {
                    SysFuncDo sysFuncDo = funcIdFuncDoMap.get(funcId);
                    rolesFuncDoList.add(sysFuncDo);

                }
                roleIdFuncListMap.put(roleId, rolesFuncDoList);
            }
            roleIdRoleDoMap.put(roleId, sysRoleDo);
        }

//        List<RoleMenuDto> roleMenuDtoList = new ArrayList<>();
//        for(Integer roleId: roleIdFuncListMap.keySet()) {
//            RoleMenuDto roleMenuDto = new RoleMenuDto();
//            SysRoleDo sysRoleDo = roleIdRoleDoMap.get(roleId);
//            roleMenuDto.setRoleId(sysRoleDo.getId());
//            roleMenuDto.setRoleCode(sysRoleDo.getRoleCode());
//            roleMenuDto.setRoleName(sysRoleDo.getRoleName());
//
//            List<SysFuncDo> rolesFuncDoList = roleIdFuncListMap.get(roleId);
//            if(rolesFuncDoList != null && rolesFuncDoList.size() > 0) {
//                List<MenuDto> menuDtoList = generateMenuList(rolesFuncDoList);
//                roleMenuDto.setMenuList(menuDtoList);
//                roleMenuDtoList.add(roleMenuDto);
//            }
//
//        }

//        generateCsv(roleMenuDtoList);
    }


    private static List<MenuDto> generateMenuList(List<SysFuncDo> sysFuncDoList) {
        Map<Integer, List<MenuDto>> parentIdMenuListMap = new HashMap<>();
        for(SysFuncDo sysFuncDo: sysFuncDoList) {
            if("menu".equalsIgnoreCase(sysFuncDo.getType())) {
                int position = sysFuncDo.getLevel1Pos();
                parentIdMenuListMap.computeIfAbsent(position, k -> new ArrayList<>());
            }
        }
        for(SysFuncDo sysFuncDo: sysFuncDoList) {
            int parentId = 0;
            if("func".equalsIgnoreCase(sysFuncDo.getType())) {
                parentId = sysFuncDo.getLevel1Pos();
            }

            List<MenuDto> menuDtoList = parentIdMenuListMap.computeIfAbsent(parentId, k -> new ArrayList<>());
            MenuDto menuDto = new MenuDto();
            menuDto.setId(sysFuncDo.getId());
            menuDto.setName(sysFuncDo.getFuncNameEn());
            menuDto.setCode(sysFuncDo.getFuncCode());
            if("func".equalsIgnoreCase(sysFuncDo.getType())) {
                menuDto.setPosition(sysFuncDo.getLevel2Pos());
            } else {
                menuDto.setPosition(sysFuncDo.getLevel1Pos());
                int position = sysFuncDo.getLevel1Pos();
                List<MenuDto> childList = parentIdMenuListMap.get(position);
                menuDto.setMenuList(childList);
            }
            menuDtoList.add(menuDto);
        }

        Comparator<MenuDto> comparator = Comparator.comparingInt(MenuDto::getPosition);
        for(Integer parentId: parentIdMenuListMap.keySet()) {
            List<MenuDto> menuDtoList = parentIdMenuListMap.get(parentId);
            menuDtoList.sort(comparator);
        }

        return parentIdMenuListMap.get(0);
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


    private static void generateCsv(List<RoleMenuDto> roleMenuDtoList) {
        String filePath = "mmsFile/menu/" + PROFILE +  "/mms1_role_func.xlsx";


        try(Workbook workbook = new XSSFWorkbook();
                FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
            Sheet sheet = workbook.createSheet("role_func");
            String[] columns = {"ROLE", "FUNC"};
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
                //FIXME
//                String func = generateFunc(roleMenuDto.getMenuList());
//                row.createCell(1).setCellValue(func);
                rowIndex = rowIndex + 1;
            }
            workbook.write(fileOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

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
}
