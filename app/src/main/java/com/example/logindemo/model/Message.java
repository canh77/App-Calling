package com.example.logindemo.model;

public class Message {
    private String image;
    private  String title;

    public  Message(){

    }

    public  Message (String image,String title){
        this.image = image;
        this.title = title;
    }

    public String getImage(){
        return  image;
    }

    public void setImage(String image){
        this.image = image;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }
}
