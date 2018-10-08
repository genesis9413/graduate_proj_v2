package com.example.pc.android_project;

/**
 * Created by soyeon on 2018-10-03.
 */

public class Drama_item {
    int image;
    String title;

    Drama_item(int image, String title){
        this.image=image;
        this.title=title;
    }

    int getImage(){
        return this.image;
    }
    String getTitle(){
        return this.title;
    }
}