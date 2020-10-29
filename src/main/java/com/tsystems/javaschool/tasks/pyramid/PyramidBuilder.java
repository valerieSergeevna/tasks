package com.tsystems.javaschool.tasks.pyramid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PyramidBuilder {

    /**
     * Builds a pyramid with sorted values (with minumum value at the top line and maximum at the bottom,
     * from left to right). All vacant positions in the array are zeros.
     *
     * @param inputNumbers to be used in the pyramid
     * @return 2d array with pyramid inside
     * @throws {@link CannotBuildPyramidException} if the pyramid cannot be build with given input
     */
    public int[][] buildPyramid(List<Integer> inputNumbers) {
        // TODO : Implement your solution here
        if (inputNumbers == null || inputNumbers.size() == 0){
            throw new CannotBuildPyramidException();
        }
        int rowsCount = 0;
        int iterator = 0;

        while (iterator < inputNumbers.size()) {
            rowsCount++;
            for (int i = 0; i < rowsCount; i++) {
                iterator++;
            }
        }
        if (rowsCount * (rowsCount + 1) / 2 != inputNumbers.size()) {
            throw new CannotBuildPyramidException();
        }
        for (Integer item : inputNumbers) {
            if (item == null) {
                throw new CannotBuildPyramidException();
            }
        }
        Collections.sort(inputNumbers);

        int[][] pyramid = new int[rowsCount][rowsCount * 2 - 1];
        iterator = 0;
        for (int i = 0; i <= rowsCount && iterator < inputNumbers.size(); i++) {
            int k = (rowsCount - i - 1);
            for (int j = 0; j <= i; j++) {
                pyramid[i][k] = inputNumbers.get(iterator++);
                k += 2;
            }
        }
        return pyramid;
    }
}



