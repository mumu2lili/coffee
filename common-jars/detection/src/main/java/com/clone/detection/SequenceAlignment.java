package com.clone.detection;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * Project: CloneDetection
 * File: com.clone.detection.SequenceAlignment.java
 * Description:
 * Time: 2018/10/31 9:35
 *
 * @author ljz
 */
public class SequenceAlignment {
    private static final int MATCH = 1;
    private static final int DISMATCH = -1;
    private static final int GAP = -1;
    private ArrayList<String> codeALine = new ArrayList<>();
    private ArrayList<String> codeBLine = new ArrayList<>();
    private ArrayList<String> codeARow = new ArrayList<>();
    private ArrayList<String> codeBRow = new ArrayList<>();
    private ArrayList<String> codeAResult = new ArrayList<>();
    private ArrayList<String> codeBResult = new ArrayList<>();

    public void setParameter(String codeALine, String codeBLine, String codeARow, String codeBRow) {
        String[] split;
        split = codeALine.split(",");
        this.codeALine.addAll(Arrays.asList(split));
        split = codeBLine.split(",");
        this.codeBLine.addAll(Arrays.asList(split));
        split = codeARow.split(",");
        this.codeARow.addAll(Arrays.asList(split));
        split = codeBRow.split(",");
        this.codeBRow.addAll(Arrays.asList(split));
        codeAResult.clear();
        codeBResult.clear();
    }

    public String getCodeAResult() {
        return createStringBuilder(codeAResult).toString();
    }

    public String getCodeBResult() {
        return createStringBuilder(codeBResult).toString();
    }

    private StringBuilder createStringBuilder(ArrayList<String> arrayList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < arrayList.size(); i++) {
            stringBuilder.append(arrayList.get(i));
            if (i < arrayList.size() - 1) {
                stringBuilder.append(',');
            }
        }
        return stringBuilder;
    }

    /**
     * 序列对齐
     */
    public void alignment() {
        int length1 = codeALine.size();
        int length2 = codeBLine.size();
        int[][] scoreMatrix = new int[length1 + 2][length2 + 2];
        int[][] preMatrix = new int[length1 + 1][length2 + 1];
        int maxScore = 0;
        int maxI = 0;
        int maxJ = 0;
        for (int i = 1; i < length1 + 1; i++) {
            for (int j = 1; j < length2 + 1; j++) {
                int fromTop, fromTopLeft, fromLeft;

                // 计算得分
                fromTop = scoreMatrix[i - 1][j] + GAP;
                if (codeALine.get(i - 1).equals(codeBLine.get(j - 1))) {
                    fromTopLeft = scoreMatrix[i - 1][j - 1] + MATCH;
                } else {
                    fromTopLeft = scoreMatrix[i - 1][j - 1] + DISMATCH;
                }
                fromLeft = scoreMatrix[i][j - 1] + GAP;
                int score = calculateScore(fromTop, fromTopLeft ,fromLeft);
                scoreMatrix[i][j] = score;

                // 确定最大得分的位置
                if (score >= maxScore) {
                    maxScore = score;
                    maxI = i;
                    maxJ = j;
                }

                // 记录得分来源
                if (score == fromTop) {
                    preMatrix[i][j] = 1;
                } else if (score == fromTopLeft) {
                    preMatrix[i][j] = 2;
                } else {
                    preMatrix[i][j] = 3;
                }
            }
        }
        
        printM(scoreMatrix,"scoreMatrix...");
        printM(preMatrix,"preMatrix...");
        backtracking(scoreMatrix, preMatrix, maxI, maxJ);
    }
    
    
    void printM(int[][] m, String msg) {
    	System.out.println(msg);
    	for(int i = 0; i < m.length; i++) {
    		for(int j = 0; j < m[i].length; j++) {
    			System.out.print("\t" + m[i][j]);
    		}
    		System.out.println();
    	}
    }
    

    /**
     * 回溯
     * @param scoreMatrix 得分矩阵
     * @param preMatrix 前置矩阵
     * @param x 回溯起点横坐标
     * @param y 回溯起点纵坐标
     */
    private void backtracking(int[][] scoreMatrix, int[][] preMatrix, int x, int y) {
        int score = scoreMatrix[x][y];
        int i = x, j = y;
        String codeAResultBegin = "";
        String codeAResultEnd = codeARow.get(i - 1);
        String codeBResultBegin = "";
        String codeBResultEnd = codeBRow.get(j - 1);
        int codeAResultLength = 0;
        int codeBResultLength = 0;
        while (score != 0) {
            switch (preMatrix[i][j]) {
                case 1:
                    codeAResultLength += 1;
                    codeAResultBegin = codeARow.get(i - 1);
                    i -= 1;
                    break;
                case 2:
                    if (codeALine.get(i - 1).equals(codeBLine.get(j - 1))) {
                        codeAResultLength += 1;
                        codeBResultLength += 1;
                        codeAResultBegin = codeARow.get(i - 1);
                        codeBResultBegin = codeBRow.get(j - 1);
                    }
                    i -= 1;
                    j -= 1;
                    break;
                case 3:
                    codeBResultLength += 1;
                    codeBResultBegin = codeBRow.get(j - 1);
                    j -= 1;
                    break;
                default:
            }
            score = scoreMatrix[i][j];
        }
        codeAResult = createCodeResult(
                codeAResultBegin, codeAResultEnd,
                String.valueOf((float) codeAResultLength / codeALine.size()));
        codeBResult = createCodeResult(
                codeBResultBegin, codeBResultEnd,
                String.valueOf((float) codeBResultLength / codeBLine.size()));
    }

    private ArrayList<String> createCodeResult(String begin, String end, String rate) {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(begin);
        arrayList.add(end);
        arrayList.add(rate);
        return arrayList;
    }

    /**
     * 计算得分
     * @param fromTop 从上方来的得分
     * @param fromTopLeft 从左上方来的得分
     * @param fromLeft 从左侧来的得分
     * @return 得分值
     */
    private int calculateScore(int fromTop, int fromTopLeft, int fromLeft) {
        int score;
        if (fromTop > fromTopLeft) {
            if (fromTop > fromLeft) {
                score = fromTop;
            } else {
                score = fromLeft;
            }
        } else {
            if (fromTopLeft > fromLeft) {
                score = fromTopLeft;
            } else {
                score = fromLeft;
            }
        }
        if (score < 0) {
            score = 0;
        }
        return score;
    }
}
