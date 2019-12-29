/*
 * Copyright (c) 2019. Relsell Global
 */

package com.relsellglobal.rxjavabasicdemo;

public class Student {
    String name = "";

    public Student(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "\nStudent{" +
                "name='" + name + '\'' +
                '}';
    }
}
