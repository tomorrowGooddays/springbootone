package com.example.springbootone.model;


public class Response<T> {
    private T Response;
    private String Message;
    private int ReturnCode;
    private int Count;

    public Object getResponse() {
        return Response;
    }

    public void setResponse(T response) {
        Response = response;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public int getReturnCode() {
        return ReturnCode;
    }

    public void setReturnCode(int returnCode) {
        ReturnCode = returnCode;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }

    @Override
    public String toString() {
        return "Response{" +
                "Response=" + Response +
                ", Message='" + Message + '\'' +
                ", ReturnCode=" + ReturnCode +
                ", Count=" + Count +
                '}';
    }
}
