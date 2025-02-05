package com.shark.example.algorithm.leetcode.page9;

import com.google.gson.Gson;

import java.util.*;

public class Program433 {

    public int minMutation(String startGene, String endGene, String[] bank) {
        //init
        int step = 0;
        Set<String> bankSet = generateBankSet(bank);
        Set<String> checkedSet = new HashSet<>();
        HashMap<String, Set<String>> geneBankMap = new HashMap<>();
        geneBankMap.put(startGene, bankSet);
        List<String> geneList = List.of(startGene);

        //start bfs
        while (!geneList.isEmpty()) {
            List<String> nextGeneList = new ArrayList<>();
            for (String gene : geneList) {
                char[] geneCharArray = gene.toCharArray();
                Set<String> theGeneBank = geneBankMap.get(gene);
                for (String bankGene : theGeneBank) {
                    int differentGeneIndex = -1;
                    char differentChar= ' ';
                    int differentCount = 0;
                    for (int i = 0; i < gene.length(); i++) {
                        char c1 = gene.charAt(i);
                        char c2 = bankGene.charAt(i);
                        if(c1 != c2) {
                            differentGeneIndex = i;
                            differentChar = c2;
                            differentCount ++;
                        }
                    }
                    if(differentCount != 1) {
                        continue;
                    }
                    char originalChar = geneCharArray[differentGeneIndex];
                    geneCharArray[differentGeneIndex] = differentChar;
                    String mutation = String.valueOf(geneCharArray);
                    geneCharArray[differentGeneIndex] = originalChar;
                    if(checkedSet.contains(mutation)) {
                        continue;
                    }
                    checkedSet.add(mutation);
                    if(mutation.equals(endGene)) {
                        return step + 1;
                    }
                    nextGeneList.add(mutation);
                    Set<String> mutationBankSet = new HashSet<>(theGeneBank);
                    mutationBankSet.remove(bankGene);
                    geneBankMap.put(mutation, mutationBankSet);
                }
            }

            step++;
            geneList = nextGeneList;
        }

        return -1;
    }

    private Set<String> generateBankSet(String[] bank) {
        Set<String> bankSet = new HashSet<>();
        Collections.addAll(bankSet, bank);
        return bankSet;
    }

    public static void main(String[] args) {
        String startGene = "AACCGGTT";
        String endGene = "AAACGGTA";
        String[] bank = {"AACCGATT", "AACCGATA", "AAACGATA", "AAACGGTA"};
        System.out.println("step: " + new Program433().minMutation(startGene, endGene, bank));
    }

}
