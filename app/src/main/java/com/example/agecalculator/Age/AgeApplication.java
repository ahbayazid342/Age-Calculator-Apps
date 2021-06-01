package com.example.agecalculator.Age;

import android.app.Application;

import java.util.ArrayList;

public class AgeApplication extends Application {
    public static ArrayList <AgeElement> ageElements;

    @Override
    public void onCreate() {
        super.onCreate();

        ageElements = new ArrayList<>();

        ageElements.add(new AgeElement(12, 5, 32, "hellpo", "2/2/2"));
        ageElements.add(new AgeElement(12, 5, 32, "hellpo", "2/2/2"));
        ageElements.add(new AgeElement(12, 5, 32, "hellpo", "2/2/2"));
    }
}
