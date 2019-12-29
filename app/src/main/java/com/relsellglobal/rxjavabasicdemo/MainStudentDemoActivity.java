/*
 * Copyright (c) 2019. Relsell Global
 */


package com.relsellglobal.rxjavabasicdemo;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainStudentDemoActivity extends AppCompatActivity {

    Observable<Student> studentObservable;
    Observer<Student> studentObserver;
    Disposable disposable;
    String TAG = MainStudentDemoActivity.class.getSimpleName();
    ArrayList<Student> studentArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Student student = new Student("anil");
        studentArrayList.add(student);
        student = new Student("sunil");
        studentArrayList.add(student);
        student = new Student("kittu");
        studentArrayList.add(student);
        student = new Student("tillu");
        studentArrayList.add(student);
        student = new Student("anil2");
        studentArrayList.add(student);
        student = new Student("anil3");
        studentArrayList.add(student);



         studentObservable = Observable.fromIterable(studentArrayList);

        studentObserver = getStudentObserver();

        final Observer<Student> a = studentObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(student12 -> student12.name.startsWith("a"))
                .map(this::encryptFunc)
                .map(this::decryptFunc)
                .subscribeWith(studentObserver);


    }

    private Student decryptFunc(Student student1) {
        char[] arr = student1.name.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for(char c : arr){
            c -=1;
            stringBuilder.append(c);
        }
        student1.name = stringBuilder.toString().toUpperCase();

        return student1;
    }

    private Student encryptFunc(Student student1) {
        char[] arr = student1.name.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for(char c : arr){
            c +=1;
            stringBuilder.append(c);
        }
        student1.name = stringBuilder.toString().toUpperCase();
        return student1;
    }


    private Observer<Student> getStudentObserver() {
        return new Observer<Student>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
                disposable = d;
            }

            @Override
            public void onNext(Student s) {
                Log.d(TAG, "Name: " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "All items are emitted!");
            }
        };
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //don't send events once the activity is destroyed
        disposable.dispose();
    }
}
