package com.example.logindemo.learn;

import android.util.Log;

public class Animal {
    private String ten2, mausac, gioitinh;
    private boolean tiemphong;
    private int tuoi;
    public int coin;

    //"A","do","nam
    public Animal(String ten2, String mausac, String gioitinh, boolean tiemphong, int tuoi) {
        this.ten2 = ten2;
        this.tiemphong = tiemphong;
        this.tuoi = tuoi;
        this.mausac = mausac;
        this.gioitinh = gioitinh;
    }

    public boolean isTiemPhong() {
        return tiemphong;
    }

    public void setTiemPhong(boolean tiemphong) {
        this.tiemphong = tiemphong;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public String getMausac() {
        return mausac;
    }

    public void setMausac(String mausac) {
        this.mausac = mausac;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getTen2() {
        return ten2;
    }

    public void setTen2(String ten2) {
        this.ten2 = ten2;
    }

   public void show(){
       Log.d("///","show"+ten2 +" "+ gioitinh);

    }
}
