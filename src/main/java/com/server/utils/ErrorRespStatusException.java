package com.server.utils;

public class ErrorRespStatusException extends Exception{
    private static final long serialVersionUID = -4954101493084921990L;
    public ErrorRespStatusException(String msg) {
        super(msg);
    }
}
