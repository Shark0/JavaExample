package com.shark.example.mms;

import com.google.gson.Gson;
import com.shark.example.mms.pojo.FuncDto;
import com.shark.example.mms.pojo.RoleDto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CreateRoleSqlExample {


    public static void main(String[] argv) {
        Set<String> filterRoleSet = generateFilterRoleSet();
        Map<String, RoleDto> roleCodeRoleMap = generateRoleCodeRoleMap(filterRoleSet);
//        Map<String, FuncDto> funcIdFuncMap = generateIdFuncMap();
//        Map<String, FuncDto> revampFuncIdFuncMap = generateIdRevampFuncMap();
        Map<String, Set<String>> roleIdRevampFuncIdSetMap = generateRoleIdRevampFuncIdSetMap();
        String copySourceRoleCode = "RML";
        List<String> copyRoleCodeList = List.of("FINANCE", "FINBUSER", "OPERATION TEAM", "RMO");
        String sql = generateRevampRoleFuncSql(copySourceRoleCode, copyRoleCodeList,
                roleCodeRoleMap, roleIdRevampFuncIdSetMap);
        System.out.println(sql);
    }

    private static Set<String> generateFilterRoleSet() {
        Set<String> set = new HashSet<>();
        set.add("RML");
        set.add("FINANCE");
        set.add("FINBUSER");
        set.add("OPERATION TEAM");
        set.add("RMO");
        return set;
    }


    private static Map<String, RoleDto> generateRoleCodeRoleMap(Set<String> filterRoleSet) {
        Map<String, RoleDto> map = new HashMap<>();
        try(
                FileReader fileReader = new FileReader("mmsFile/menu/role.txt");
                BufferedReader bufferedReader = new BufferedReader(fileReader)
        ) {
            String line = bufferedReader.readLine();
            int index = 0;
            while (line != null) {
                if(index > 0) {
                    String[] array = line.split("\t", -1);
                    String roleCode = array[1];
                    if(filterRoleSet.contains(roleCode)) {
                        RoleDto roleDto = new RoleDto();
                        roleDto.setId(array[0]);
                        roleDto.setCode(roleCode);
                        roleDto.setName(array[2]);
                        map.put(roleCode, roleDto);
                    }
                }
                line = bufferedReader.readLine();
                index = index + 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    private static Map<String, FuncDto> generateIdFuncMap() {
        Map<String, FuncDto> map = new HashMap<>();
        try(
                FileReader fileReader = new FileReader("mmsFile/menu/func.txt");
                BufferedReader bufferedReader = new BufferedReader(fileReader)
        ) {
            String line = bufferedReader.readLine();
            int index = 0;
            while (line != null) {
                if(index > 0) {
                    String[] array = line.split("\t", -1);
                    String funcId = array[0];
                    FuncDto funcDto = new FuncDto();
                    funcDto.setId(funcId);
                    funcDto.setCode(array[1]);
                    funcDto.setName(array[7]);
                    map.put(funcId, funcDto);
                }
                line = bufferedReader.readLine();
                index = index + 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    private static Map<String, FuncDto> generateIdRevampFuncMap() {
        Map<String, FuncDto> map = new HashMap<>();

        try(
                FileReader fileReader = new FileReader("mmsFile/menu/revamp_func.txt");
                BufferedReader bufferedReader = new BufferedReader(fileReader)
        ) {
            String line = bufferedReader.readLine();
            int index = 0;
            while (line != null) {
                if(index > 0) {
                    String[] array = line.split("\t", -1);
                    System.out.println(index);
                    String funcId = array[0];
                    System.out.println(new Gson().toJson(array));
                    FuncDto funcDto = new FuncDto();
                    funcDto.setId(funcId);
                    funcDto.setCode(array[3]);
                    funcDto.setName(array[5]);
                    map.put(funcId, funcDto);
                }
                line = bufferedReader.readLine();
                index = index + 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    private static Map<String, Set<String>> generateRoleIdRevampFuncIdSetMap() {
        Map<String, Set<String>> map = new HashMap<>();
        try(
                FileReader fileReader = new FileReader("mmsFile/menu/revamp_role_func.txt");
                BufferedReader bufferedReader = new BufferedReader(fileReader)
        ) {
            String line = bufferedReader.readLine();
            int index = 0;
            while (line != null) {
                if(index > 0) {
                    String[] array = line.split("\t", -1);
                    String roleId = array[1];
                    String funcId = array[2];
                    Set<String> funcSet = map.computeIfAbsent(roleId, k -> new HashSet<>());
                    funcSet.add(funcId);
                }
                line = bufferedReader.readLine();
                index = index + 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    private static String generateRevampRoleFuncSql(String copySourceRoleCode, List<String> copyTargetRoleCodeList, Map<String, RoleDto> roleCodeRoleMap, Map<String, Set<String>> roleIdRevampFuncIdSetMap) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("use mms;").append("\n");
        String copySourceRoleId = roleCodeRoleMap.get(copySourceRoleCode).getId();
        Set<String> copySourceRevampFuncIdSet = roleIdRevampFuncIdSetMap.get(copySourceRoleId);
        for(String targetRoleCode: copyTargetRoleCodeList) {
            stringBuilder.append("#").append(targetRoleCode).append("\n");
            String targetRoleId = roleCodeRoleMap.get(targetRoleCode).getId();
//            System.out.println("targetRoleCode: " + targetRoleCode + ", targetRoleId: " + targetRoleId);
            Set<String> targetRoleIdRevampFuncIdSet = roleIdRevampFuncIdSetMap.get(targetRoleId);
            for (String sourceFuncId: copySourceRevampFuncIdSet) {
                if(targetRoleIdRevampFuncIdSet == null || !targetRoleIdRevampFuncIdSet.contains(sourceFuncId)) {
                    stringBuilder.append("INSERT INTO SYS_REVAMP_ROLE_FUNC (ROLE_ID, FUNC_ID, ACTIVE, CREATED_DATE, CREATED_BY, LAST_UPDATED_DATE, LAST_UPDATED_BY) VALUES (")
                            .append(targetRoleId).append(", ").append(sourceFuncId)
                            .append(", 1, now(), 'SYSTEM', now(), 'SYSTEM');").append("\n");
                }
            }
        }


        return stringBuilder.toString();
    }
}
