package org.sen.common.exception;

public class SenBootException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SenBootException(String message){
        super(message);
    }

    public SenBootException(Throwable cause)
    {
        super(cause);
    }

    public SenBootException(String message,Throwable cause)
    {
        super(message,cause);
    }
}
