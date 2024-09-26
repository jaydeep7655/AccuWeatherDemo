package com.example.accuweatherdemo.presentation.weatherDetails;

class A {
    public void add() {
        System.out.println("Class A");
    }

}

interface D {
    default void add(){
        System.out.println("Class D");
    }
}


public class Test extends A implements D {

    @Override
    public void add() {
        System.out.println("Class 1");
        super.add();
        System.out.println("Class 2");
    }

    public static void main(String[] args) {
        Test test=new Test();
        test.add();
    }
}

