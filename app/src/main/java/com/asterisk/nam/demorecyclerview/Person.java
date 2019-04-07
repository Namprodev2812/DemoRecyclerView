package com.asterisk.nam.demorecyclerview;

public class Person {
    private String mName;
    private int mAge;
    private String mPhone;
    private int mImage;

    public String getmName() {
        return mName;
    }

    public int getAge() {
        return mAge;
    }

    public String getPhone() {
        return mPhone;
    }

    public int getmImage() {
        return mImage;
    }

    public Person(String name ,int age, String phone, int image){
        mName = name;
        mAge = age;
        mPhone = phone;
        mImage = image;
    }
}
