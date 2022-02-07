package com.demo.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SeatArrangement {

    public static List<int[]> inputs = new ArrayList();
    static Map<Integer, List<String>> windowSeatMap = new HashMap<>();
    static Map<Integer, List<String>> centerSeatMap = new HashMap<>();
    static Map<Integer, List<String>> aisleSeatMap = new HashMap<>();
    static int count = 1;
    static int passengers = 0;
    static String matrix[][] = {};


    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.print("Enter the no of rows ");
        int inputRows = scan.nextInt();
        int inputArray[][] = new int[inputRows][2];

        for (int i = 0; i < inputRows; i++) {
            for (int j = 0; j < 2; j++) {
                inputArray[i][j] = scan.nextInt();
            }
        }

        System.out.print("Enter the no of passengers ");
        passengers = scan.nextInt();

        int colCount = 0;
        int rowCount = inputRows;

        for (int i = 0; i < inputRows; i++) {
            colCount += inputArray[i][0];
            inputs.add(inputArray[i]);
        }

        matrix = new String[rowCount][colCount];
        int offset = 0;
        for (int i = 0; i < inputs.size(); i++) {
            int[] value = inputs.get(i);
            offset += populate(offset, value[0], value[1], (i == inputs.size() - 1));
        }

        SeatType type = SeatType.values()[0];
        switch (type.ordinal()) {
            case 0:
                printSeats(aisleSeatMap);
            case 1:
                printSeats(windowSeatMap);
            case 2:
                printSeats(centerSeatMap);
        }

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                if (matrix[i][j] == null)
                    matrix[i][j] = getWhiteSpace(1);
                if (matrix[i][j].length() == 1)
                    System.out.print(getWhiteSpace(2) + matrix[i][j]);
                else
                    System.out.print(getWhiteSpace(1) + matrix[i][j]);
            }
            System.out.println();
        }
    }

    private static String getWhiteSpace(int size) {
        StringBuilder builder = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            builder.append(' ');
        }
        return builder.toString();
    }

    public static List<String> getNewList() {
        return new ArrayList<>();
    }

    public static int populate(int offset, int col, int row, boolean isLast) {
        int j = offset;
        for (; j < offset + col; j++) {

            for (int i = 0; i < row; i++) {
                if (j == 0 || j == offset) {
                    if (aisleSeatMap.size() == 0) {
                        if (windowSeatMap.get(i) == null) {
                            windowSeatMap.put(i, getNewList());
                        }
                        windowSeatMap.get(i).add(i + "," + j);
                    } else {
                        if (aisleSeatMap.get(i) == null) {
                            aisleSeatMap.put(i, getNewList());
                        }
                        aisleSeatMap.get(i).add(i + "," + j);
                    }
                    continue;
                } else if (j == offset + col - 1) {
                    if (isLast) {
                        if (windowSeatMap.get(i) == null) {
                            windowSeatMap.put(i, getNewList());
                        }
                        windowSeatMap.get(i).add(i + "," + j);
                    } else {
                        if (aisleSeatMap.get(i) == null) {
                            aisleSeatMap.put(i, getNewList());
                        }
                        aisleSeatMap.get(i).add(i + "," + j);
                    }
                    continue;
                } else {
                    if (centerSeatMap.get(i) == null) {
                        centerSeatMap.put(i, getNewList());
                    }
                    centerSeatMap.get(i).add(i + "," + j);
                    continue;
                }
            }

        }
        return col;
    }

    public static void printSeats(Map<Integer, List<String>> seats) {
        for (Integer row : seats.keySet()) {
            SeatCollection seatCollection = new SeatCollection(seats.get(row));
            Iterator<String[]> iterator = seatCollection.iterator();
            while (iterator.hasNext()) {
                String[] position = iterator.next();
                if (count <= passengers) {
                    matrix[Integer.parseInt(position[0])][Integer.parseInt(position[1])] = String.valueOf(count);
                    count++;
                }

            }

        }

    }
}


