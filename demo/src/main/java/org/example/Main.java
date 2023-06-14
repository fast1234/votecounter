package org.example;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");
        int[] arr = new int[] {1,2,4};
        System.out.println(Arrays.asList(arr));
        List<Integer> ar = Arrays.asList(1,2,3);
        System.out.println(ar.toArray());
    }
}