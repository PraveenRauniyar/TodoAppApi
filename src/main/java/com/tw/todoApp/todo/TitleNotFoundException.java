package com.tw.todoApp.todo;

public class TitleNotFoundException extends Throwable{

    public  TitleNotFoundException (){
        super("Title not found in Todo List");
    }
}
