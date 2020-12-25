package com.example.appdocbao;

public class User {
    private String email;
    private String password;
    private String hoTen;
    private String soDienThoai;

    public User(String email, String password, String hoTen, String soDienThoai) {
        this.email = email;
        this.password = password;
        this.hoTen = hoTen;
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }
}
