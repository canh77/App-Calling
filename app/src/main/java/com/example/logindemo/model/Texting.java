package com.example.logindemo.model;

public class Texting {
    private  String text;

    public  Texting(){

    }

    public  Texting (String text){
        this.text = text;
    }

    public String getText(){
        return  text;
    }

    public void setText(String text){
        this.text = text;
    }
}
