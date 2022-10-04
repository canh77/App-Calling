package com.example.logindemo.model;

public class PhoneBook {
    private String id;
    private String name;
    private String phone;

    public PhoneBook() {
    }

    public PhoneBook(String id, String name,  String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


//    public String getStt(int statuss){
//        String stts = "";
//        if (statuss == 0){
//            stts = "Trần Vân";
//        }else if (statuss == 1){
//            stts ="Hồ An";
//        }else if (statuss == 2){
//            stts ="Vân Anh";
//        }
//        return  stts;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
