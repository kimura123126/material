package com.example.dell1.materialtest;

/**
 * Created by DELL1 on 2019/1/31.
 */

public class Fruit {
    private String name;
    private int imageId;
    public Fruit(String name,int imageId){
        this.name=name;
        this.imageId=imageId;
    }

    public String getName() {
        return name;
    }

    public int getImageId(){
        return imageId;
    }
}
