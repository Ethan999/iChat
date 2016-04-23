package com.example.song.ichat;

/**
 * Created by Ethan on 2016/4/23.
 */
public class Result
{
    private int code;
    private String text;

    public Result()
    {
    }

    public Result(int resultCode, String msg)
    {
        this.code = resultCode;
        this.text = msg;
    }

    public Result(int resultCode)
    {
        this.code = resultCode;
    }

    public int getCode()
    {
        return this.code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public String getText()
    {
        return this.text;
    }

    public void setText(String text)
    {
        this.text = text;
    }
}