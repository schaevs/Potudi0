package com.company;

/**
   https://stackoverflow.com/questions/28556129/java-sort-one-array-based-on-values-of-another-array
 */
import java.util.*;

public class WeightSort {
    public static void main(String[] strs, int[] keys ) {
        String[] strings = strs; //new String[]{"string1", "string2", "string3"};
        final int[] weights = keys; //new int[]{40, 32, 34};
        final List<String> stringList = Arrays.asList(strings);
        List<String> sortedCopy = new ArrayList<String>(stringList);
        Collections.sort(sortedCopy, new Comparator<String>(){
            public int compare(String left, String right) {
                return weights[stringList.indexOf(left)] - weights[stringList.indexOf(right)];
            }
        });
        System.out.println("From most potato to least potato::::::");
        for (String element : sortedCopy) {
            System.out.println(element);
        }

        //System.out.println(sortedCopy);
    }
}