package com.example.logindemo.model;

public class Recently {
    private  String id;
    private  String name;
    private  String phones;
    private  int status;
    private  String time;

    public Recently() {
    }

    public Recently(String id, String name, String phones, int status,String time) {
        this.id = id;
        this.name = name;
        this.phones = phones;
        this.status = status;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhones() {
        return phones;
    }

    public void setPhone(String phones) {
        this.phones = phones;
    }

    public String getSTT(int status){
        String stt = "";
        if (status == 0) {
            stt = "Cuộc gọi đến";
        }else if (status == 1){
            stt = "Cuộc gọi đi";
        }else if (status == 2){
            stt = "Cuộc gọi bị nhỡ";
        }
        return stt;
    }
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
