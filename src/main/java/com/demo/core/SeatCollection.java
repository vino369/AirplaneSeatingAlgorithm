package com.demo.core;

import java.util.Iterator;
import java.util.List;

public class SeatCollection implements Iterable<List<String>> {

    List<String> columnList;

    SeatCollection(List<String> columnList) {
        this.columnList = columnList;
    }

    @Override
    public Iterator iterator() {
        return new SeatIterator();
    }


    private class SeatIterator implements Iterator{
        int currentPointer = 0;
        String position;

        @Override
        public boolean hasNext() {
            return currentPointer < columnList.size();
        }

        @Override
        public String[] next() {
            position = columnList.get(currentPointer);
            currentPointer++;
            String[] pos = position.split(",");
            return pos;
        }
    }
}

