package com.itheima.reggie.test;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Test01 {

    private static void accept(Integer a) {
        if(a%2==0){
            System.out.print("偶数");
        }else{
            System.out.print("奇数");
        }
    }

    @Test
    public void tets01(){
        List<Integer> list = Arrays.asList(5, 2, 3, 1, 4);
        //List<Integer> newList = list.stream().map(x -> x + 3).forEach(System.out::println);//collect(Collectors.toList());

        List<Integer> collect = list.stream().peek(Test01::accept).
                peek(System.out::print).map(a ->a+3).
                peek(System.out::print).collect(Collectors.toList());
        //;collect(Collectors.toList());


    }





}
