package com.shark.example.algorithm.leetcode.page1.program37;

import com.google.gson.Gson;

import java.util.*;

public class Program37 {

    public void solveSudoku(char[][] board) {
        helper(board, 0, 0);
    }

    public boolean helper(char[][] board, int row, int column) {
        System.out.println("helper row: " + row + ", column: " + column);
        printCharBoard(board);

        if (row == board.length) {
            return true;
        }
        int nextRow;
        int nextColumn;
        if (column != board.length - 1) {
            nextRow = row;
            nextColumn = column + 1;
        } else {
            nextRow = row + 1;
            nextColumn = 0;
        }
        if (board[row][column] != '.') {
            return helper(board, nextRow, nextColumn);
        } else {
            for (int i = 1; i <= 9; i++) {
                if (isSafe(board, row, column, i)) {
                    board[row][column] = (char) (i + '0');
                    if (helper(board, nextRow, nextColumn)) {
                        return true;
                    } else {
                        board[row][column] = '.';
                    }
                }
            }
        }
        return false;
    }

    public boolean isSafe(char[][] board, int row, int col, int number) {
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] == (char) (number + '0')) {
                return false;
            }
            if (board[row][i] == (char) (number + '0')) {
                return false;
            }
        }
        int sr = (row / 3) * 3;
        int sc = (col / 3) * 3;
        for (int i = sr; i < sr + 3; i++) {
            for (int j = sc; j < sc + 3; j++) {
                if (board[i][j] == (char) (number + '0')) {
                    return false;
                }
            }
        }
        return true;
    }

    public void solveSudoku1(char[][] board) {
        Map<Integer, Map<Integer, Set<Integer>>> unSolvedMap = new HashMap<>();
        //init
        Map<Integer, Set<Integer>> rowSetMap = new HashMap<>();
        Map<Integer, Set<Integer>> columnSetMap = new HashMap<>();
        Map<Integer, Set<Integer>> blockSetMap = new HashMap<>();

        for (int rowIndex = 0; rowIndex < 9; rowIndex++) {
            for (int columnIndex = 0; columnIndex < 9; columnIndex++) {
                char boardValue = board[rowIndex][columnIndex];
                if ('.' == boardValue) {
                    Set<Integer> set = new HashSet<>();
                    for (int i = 0; i < 9; i++) {
                        set.add(i + 1);
                    }
                    Map<Integer, Set<Integer>> columnMap = unSolvedMap.computeIfAbsent(rowIndex, k -> new HashMap<>());
                    columnMap.put(columnIndex, set);
                } else {
                    int intValue = Integer.parseInt(String.valueOf(boardValue));
                    rowSetMap.computeIfAbsent(rowIndex, k -> new HashSet<>()).add(intValue);
                    columnSetMap.computeIfAbsent(columnIndex, k -> new HashSet<>()).add(intValue);
                    int blockIndex = generateBlockIndex(rowIndex, columnIndex);
                    blockSetMap.computeIfAbsent(blockIndex, k -> new HashSet<>()).add(intValue);
                }
            }
        }
        for (Integer rowIndex : unSolvedMap.keySet()) {
            Map<Integer, Set<Integer>> columnMap = unSolvedMap.get(rowIndex);
            for (Integer columnIndex : columnMap.keySet()) {
                Set<Integer> unsolvedSet = columnMap.get(columnIndex);
                int blockIndex = generateBlockIndex(rowIndex, columnIndex);
                unsolvedSet.removeAll(rowSetMap.computeIfAbsent(rowIndex, k -> new HashSet<>()));
                unsolvedSet.removeAll(columnSetMap.computeIfAbsent(columnIndex, k -> new HashSet<>()));
                unsolvedSet.removeAll(blockSetMap.computeIfAbsent(blockIndex, k -> new HashSet<>()));
            }
        }

        Gson gson = new Gson();
        System.out.println("rowSetMap: " + gson.toJson(rowSetMap));
        System.out.println("columnSetMap: " + gson.toJson(columnSetMap));
        System.out.println("blockSetMap: " + gson.toJson(blockSetMap));
        System.out.println("unSolvedMap: " + gson.toJson(unSolvedMap));
        printCharBoard(board);

        while (!unSolvedMap.isEmpty()) {
            for (int rowIndex = 0; rowIndex < 9; rowIndex++) {
                Map<Integer, Set<Integer>> unsolvedColumnMap = unSolvedMap.get(rowIndex);
                if (unsolvedColumnMap == null) {
                    continue;
                }
                for (int columnIndex = 0; columnIndex < 9; columnIndex++) {
                    Set<Integer> unsolvedSet = unsolvedColumnMap.get(columnIndex);
                    if (unsolvedSet == null) {
                        continue;
                    }
                    System.out.println("before rowIndex: " + rowIndex + ", columnIndex: " + columnIndex + ", unsolvedSet: " + gson.toJson(unsolvedSet));
                    int blockIndex = generateBlockIndex(rowIndex, columnIndex);
                    Set<Integer> rowSet = rowSetMap.computeIfAbsent(rowIndex, k -> new HashSet<>());
                    System.out.println("rowSet: " + gson.toJson(rowSet));
                    unsolvedSet.removeAll(rowSet);
                    Set<Integer> columnSet = columnSetMap.computeIfAbsent(columnIndex, k -> new HashSet<>());
                    System.out.println("columnSet: " + gson.toJson(columnSet));
                    unsolvedSet.removeAll(columnSet);
                    Set<Integer> blockSet = blockSetMap.computeIfAbsent(blockIndex, k -> new HashSet<>());
                    System.out.println("blockSet: " + gson.toJson(blockSet));
                    unsolvedSet.removeAll(blockSet);
                    List<Set<Integer>> otherRowUnsolvedSetList = findOtherUnsolvedRowSetList(rowIndex, columnIndex, unSolvedMap);
                    System.out.println("otherRowUnsolvedSetList: " + gson.toJson(otherRowUnsolvedSetList));
                    List<Set<Integer>> repeatSetList = findRepeatSetList(otherRowUnsolvedSetList);
                    System.out.println("row repeatSetList: " + gson.toJson(repeatSetList));
                    for (Set<Integer> repeatSet : repeatSetList) {
                        unsolvedSet.removeAll(repeatSet);
                    }
                    List<Set<Integer>> otherColumnUnsolvedSetList = findOtherUnsolvedColumnSetList(rowIndex, columnIndex, unSolvedMap);
                    System.out.println("otherColumnUnsolvedSetList: " + gson.toJson(otherColumnUnsolvedSetList));
                    repeatSetList = findRepeatSetList(otherColumnUnsolvedSetList);
                    System.out.println("column repeatSetList: " + gson.toJson(repeatSetList));
                    for (Set<Integer> repeatSet : repeatSetList) {
                        unsolvedSet.removeAll(repeatSet);
                    }
                    List<Set<Integer>> otherBlockUnsolvedSetList = findOtherUnsolvedBlockSetList(rowIndex, columnIndex, unSolvedMap);
                    System.out.println("otherBlockUnsolvedSetList: " + gson.toJson(otherBlockUnsolvedSetList));
                    repeatSetList = findRepeatSetList(otherBlockUnsolvedSetList);
                    System.out.println("block repeatSetList: " + gson.toJson(repeatSetList));
                    for (Set<Integer> repeatSet : repeatSetList) {
                        unsolvedSet.removeAll(repeatSet);
                    }
                    if (unsolvedSet.size() == 1) {
                        int resultValue = -1;
                        for (int value : unsolvedSet) {
                            resultValue = value;
                        }
                        board[rowIndex][columnIndex] = (char) (resultValue + '0');
                        rowSetMap.computeIfAbsent(rowIndex, k -> new HashSet<>()).add(resultValue);
                        columnSetMap.computeIfAbsent(columnIndex, k -> new HashSet<>()).add(resultValue);
                        blockSetMap.computeIfAbsent(blockIndex, k -> new HashSet<>()).add(resultValue);
                        unsolvedColumnMap.remove(columnIndex);
                        if (unsolvedColumnMap.isEmpty()) {
                            unSolvedMap.remove(rowIndex);
                        }
                    }
                    System.out.println("after rowIndex: " + rowIndex + ", columnIndex: " + columnIndex + ", unsolvedSet: " + gson.toJson(unsolvedSet));
                    printCharBoard(board);
                    System.out.println();
                }
            }
        }
    }

    public int generateBlockIndex(int rowIndex, int columnIndex) {
        if (rowIndex >= 0 && rowIndex < 3) {
            if (columnIndex >= 0 && columnIndex < 3) {
                return 0;
            } else if (columnIndex >= 3 && columnIndex < 6) {
                return 1;
            } else {
                return 2;
            }
        } else if (rowIndex >= 3 && rowIndex < 6) {
            if (columnIndex >= 0 && columnIndex < 3) {
                return 3;
            } else if (columnIndex >= 3 && columnIndex < 6) {
                return 4;
            } else {
                return 5;
            }
        } else {
            if (columnIndex >= 0 && columnIndex < 3) {
                return 6;
            } else if (columnIndex >= 3 && columnIndex < 6) {
                return 7;
            } else {
                return 8;
            }
        }
    }


    public List<Set<Integer>> findOtherUnsolvedRowSetList(
            int rowIndex, int columnIndex,
            Map<Integer, Map<Integer, Set<Integer>>> unSolvedRowMap) {
        List<Set<Integer>> otherRowSetList = new ArrayList<>();
        Map<Integer, Set<Integer>> unsolvedColumnMap = unSolvedRowMap.get(rowIndex);
        for (Integer key : unsolvedColumnMap.keySet()) {
            if (key != columnIndex) {
                otherRowSetList.add(unsolvedColumnMap.get(key));
            }
        }
        return otherRowSetList;
    }

    public List<Set<Integer>> findOtherUnsolvedColumnSetList(
            int rowIndex, int columnIndex,
            Map<Integer, Map<Integer, Set<Integer>>> unSolvedRowMap) {
        List<Set<Integer>> otherRowSetList = new ArrayList<>();
        for (Integer unSolvedRowKey : unSolvedRowMap.keySet()) {
            if (unSolvedRowKey != rowIndex) {
                Set<Integer> unsolvedSet = unSolvedRowMap.get(unSolvedRowKey).get(columnIndex);
                if (unsolvedSet != null) {
                    otherRowSetList.add(unsolvedSet);
                }
            }
        }
        return otherRowSetList;
    }

    public List<Set<Integer>> findOtherUnsolvedBlockSetList(
            int rowIndex, int columnIndex,
            Map<Integer, Map<Integer, Set<Integer>>> unSolvedRowMap) {
        List<Set<Integer>> otherRowSetList = new ArrayList<>();
        int rowOffset = (rowIndex / 3) * 3;
        int columnOffset = (columnIndex / 3) * 3;
        for (int blockRowIndex = 0; blockRowIndex < 3; blockRowIndex++) {
            for (int blockColumnIndex = 0; blockColumnIndex < 3; blockColumnIndex++) {
                int unSolvedRowIndex = blockRowIndex + rowOffset;
                int unSolvedColumnIndex = blockColumnIndex + columnOffset;
                if (unSolvedRowIndex != rowIndex || unSolvedColumnIndex != columnIndex) {
                    Map<Integer, Set<Integer>> unSolveColumnMap = unSolvedRowMap.get(unSolvedRowIndex);
                    if (unSolveColumnMap != null) {
                        Set<Integer> unsolvedSet = unSolveColumnMap.get(unSolvedColumnIndex);
                        if (unsolvedSet != null) {
//                            System.out.println("unSolvedRowIndex: " + unSolvedRowIndex +
//                                    ", unSolvedColumnIndex: " + unSolvedColumnIndex + ", unsolvedSet: " + new Gson().toJson(unsolvedSet));
                            otherRowSetList.add(unsolvedSet);
                        }
                    }
                }
            }
        }
        return otherRowSetList;

    }

    public List<Set<Integer>> findRepeatSetList(List<Set<Integer>> list) {
        List<Set<Integer>> result = new ArrayList<>();
        Map<String, Set<Integer>> newSetMap = new HashMap<>();
        for (Set<Integer> set : list) {
            Map<String, Set<Integer>> tempNewSetMap = new HashMap<>();
            for (String key : newSetMap.keySet()) {
                Set<Integer> set2 = newSetMap.get(key);
                int containCount = 0;
                for (Integer value : set2) {
                    if (set.contains(value)) {
                        containCount++;
                    }
                }
                if (containCount > 0 && containCount != set.size()) {
                    Set<Integer> newSet = new HashSet<>(set);
                    newSet.addAll(set2);
                    String newSetKey = newSet.toString();
                    tempNewSetMap.put(newSetKey, newSet);
                }
            }
            newSetMap.putAll(tempNewSetMap);
            String key = set.toString();
            if (!newSetMap.containsKey(key)) {
                newSetMap.put(key, set);
            }
        }
        Map<String, Integer> keyCountMap = new HashMap<>();
        for (String key : newSetMap.keySet()) {
            Integer count = keyCountMap.computeIfAbsent(key, k -> 0);
            Set<Integer> newSet = newSetMap.get(key);
            for (Set<Integer> set : list) {
                if (newSet.containsAll(set)) {
                    count++;
                }
            }
            keyCountMap.put(key, count);
        }
        for (String key : newSetMap.keySet()) {
            Integer count = keyCountMap.get(key);
            Set<Integer> newSet = newSetMap.get(key);
            if (count == newSet.size()) {
                result.add(newSet);
            }
        }
        return result;
    }


    public void printCharBoard(char[][] board) {
        Gson gson = new Gson();
        for (char[] row : board) {
            System.out.println(gson.toJson(row));
        }
    }

    public static void main(String[] args) {
//        char[][] board = new char[][]{
//                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
//                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
//                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
//                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
//                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
//                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
//                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
//                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
//                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};

        char[][] board = new char[][]{
                {'.', '.', '9', '7', '4', '8', '.', '.', '.'},
                {'7', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '2', '.', '1', '.', '9', '.', '.', '.'},
                {'.', '.', '7', '.', '.', '.', '2', '4', '.'},
                {'.', '6', '4', '.', '1', '.', '5', '9', '.'},
                {'.', '9', '8', '.', '.', '.', '3', '.', '.'},
                {'.', '.', '.', '8', '.', '3', '.', '2', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '6'},
                {'.', '.', '.', '2', '7', '5', '9', '.', '.'}};

        Program37 program37 = new Program37();
        program37.printCharBoard(board);
        program37.solveSudoku(board);
        program37.printCharBoard(board);
    }
}
