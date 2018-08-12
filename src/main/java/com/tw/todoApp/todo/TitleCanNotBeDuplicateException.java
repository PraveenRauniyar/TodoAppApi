package com.tw.todoApp.todo;

public class TitleCanNotBeDuplicateException extends Throwable{
    public  TitleCanNotBeDuplicateException (){
        super("Title can not duplicate .This title already added");
    }
}
