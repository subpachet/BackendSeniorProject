package com.seniorproject.project.exception;

public class ProjectException extends RuntimeException{
    private final int code;

    public ProjectException(int code){
        this.code = code;
    }

    public ProjectException(String code){
        this.code = Integer.parseInt(code);
    }

    public int getCode() {
        return code;
    }
}
