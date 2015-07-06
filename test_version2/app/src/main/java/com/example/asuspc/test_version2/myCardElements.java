package com.example.asuspc.test_version2;

/**
 * Created by ASUS PC on 06-Jul-15.
 */
public class myCardElements {
    public String uid;
    public String price1;
    public String price2;
    public String price3;
    public myCardElements()
    {

    }
    public myCardElements(String id,String pric1,String pric2,String pric3)
    {
        uid=id;
        price1=pric1;
        price2=pric2;
        price3=pric3;
    }
    String getuid()
    {
        return uid;
    }

}
