package com.doitwell.group.springTesting.Config;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Apprendre {

    public static void main(String[] args) {
        Generics<String,Float> generics = new Generics<>("Mc Mvambodo", 12.3f);
        generics.print();

        ArrayList<String> list = new ArrayList<>();
        list.add("Mc");
        list.add("Mvambodo");
        listBook(list);

        ArrayList<Integer> listNum = new ArrayList<>();
        listNum.add(1);
        listNum.add(2);
        listBook(listNum);
    }

    public static List<?> listBook(List<?> books){
        System.out.println(books);
        return books;
    }
}
