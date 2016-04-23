package com.example.song.ichat;

/**
 * Created by Ethan on 2016/4/23.
 */
public class CommonException extends RuntimeException
{
    public CommonException()
    {
    }

    public CommonException(String detailMessage, Throwable throwable)
    {
        super(detailMessage, throwable);
    }

    public CommonException(String detailMessage)
    {
        super(detailMessage);
    }

    public CommonException(Throwable throwable)
    {
        super(throwable);
    }
}