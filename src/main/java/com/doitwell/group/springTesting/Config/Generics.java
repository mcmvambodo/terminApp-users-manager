package com.doitwell.group.springTesting.Config;

public class Generics <T,S> {

    private T element1;
    private S element2;

    public Generics(T element1, S element2) {
        this.element1 = element1;
        this.element2 = element2;
    }

    public void print(){
        System.out.println(this.element1);
        System.out.println(this.element2);
    }
}
