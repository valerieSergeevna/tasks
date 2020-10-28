package com.tsystems.javaschool.tasks.subsequence;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Subsequence {

    /**
     * Checks if it is possible to get a sequence which is equal to the first
     * one by removing some elements from the second one.
     *
     * @param x first sequence
     * @param y second sequence
     * @return <code>true</code> if possible, otherwise <code>false</code>
     */
    @SuppressWarnings("rawtypes")
    public boolean find(List x, List y) {
        // TODO: Implement the logic here
        if (x == null || y == null) {
            throw new IllegalArgumentException();
        }
        if (x.size() > y.size()) {
            return false;
        }
        Iterator<Object> iterator = y.iterator();
        boolean check = false;
        List compareList = new ArrayList(x);
        while (iterator.hasNext()) {
            Object item = iterator.next();
            Iterator<Object> iterator2 = compareList.iterator();
            while (iterator2.hasNext()) {
                Object elem = iterator2.next();
                    if (elem.equals(item)) {
                        check = true;
                        iterator2.remove();
                        break;
                    }
            }
            if (!check) {
                iterator.remove();
            }
            check = false;
        }
        return x.equals(y);
    }
}
