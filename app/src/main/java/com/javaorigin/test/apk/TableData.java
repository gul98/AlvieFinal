package com.javaorigin.test.apk;

/**
 * Created by Noor Zia on 5/26/2015.
 */
public class TableData {
    int id;
    String name;
    String number;

    public TableData(){}
    public TableData(String no){number = no;}
    public TableData(String n, String no){
        name = n;
        number = no;
    }
    public TableData(int i, String n, String no){
        id = i;
        name = n;
        number = no;
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

