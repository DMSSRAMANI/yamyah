package com.example.shopping.model;

public class Users {
    private String name, phone,password;

    public Users(){

    }
    public Users(String name,String phone,String password){
        this.name= name;
        this.phone= phone;
        this.password= password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setPhone(String phone){
        this.phone =phone;
    }
    public String setPhone(){
        return phone;
    }
    public void setPassword(String password){
        this.phone =password;
    }
    public String setPassword(){
        return password;
    }

    public void getPhone() {this.phone =phone;
    }
    public void getPassword() {this.phone =password;
    }
}
