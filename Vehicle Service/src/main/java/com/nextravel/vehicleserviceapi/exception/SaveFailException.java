package com.nextravel.vehicleserviceapi.exception;

public class SaveFailException extends Exception{
    public SaveFailException(String message, Throwable cause) {
    super(message + " : "+cause.getMessage(), cause);
    }
}
