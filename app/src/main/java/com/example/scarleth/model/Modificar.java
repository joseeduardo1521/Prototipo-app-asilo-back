package com.example.scarleth.model;

public class Modificar {
    private String uid;
    private  String name;
    private  String email;
    private String contraseña;
    private String age;
    private String sex;
    private String blood;
    private String ale;
    private String tratam;
    private String hour;
    private String status;
    private String room;

    public Modificar() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getAle() {
        return ale;
    }

    public void setAle(String ale) {
        this.ale = ale;
    }

    public String getTratam() {
        return tratam;
    }

    public void setTratam(String tratam) {
        this.tratam = tratam;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return name;
    }
}
