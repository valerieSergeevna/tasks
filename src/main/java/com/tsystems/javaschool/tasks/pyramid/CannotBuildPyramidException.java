package com.tsystems.javaschool.tasks.pyramid;

public class CannotBuildPyramidException extends RuntimeException {
    public CannotBuildPyramidException() {
        this("The pyramid cannot be build with given input");
    }

    public CannotBuildPyramidException(String message) {
        super(message);
    }
}
