package com.javaorigin.test.apk;

/**
 * Created by Noor Zia on 5/26/2015.
 */
public class Anonymous {
    int id;
    String name;
    String number;

    public Anonymous(){

        name="Unknown";
    }
    public Anonymous(String no){
        name="Unknown";
        number = no;
    }
    public Anonymous(String no, int id){
        name="Unknown";
        number=no;
        this.id=id;
    }



    public int getID(){
        return this.id;
    }

    // setting id


    // getting name
    public String getName(){

        return this.name;

    }

    // setting name


    // getting phone number
    public String getPhoneNumber(){
        return this.number;
    }

    // setting phone number

}

