package com.amazon;

import java.util.Arrays;

public class sorting {
    
    public static void sortPriceSmallToLarge(double[] x, int n){
        for(int i  = 0; i < n - 1; i++){
            int indexOfSmallest = getIndexOfSmallest(x, i, n - 1);
            swap(x, i, indexOfSmallest);
        }


    }

    private static void sortPriceLargeToSmall(double[] x, int n){
        for(int i = 0; i < n - 1; i++){
            int indexOfLargest = getIndexOfLargest(x, i, n - 1);
            swap(x, i, indexOfLargest);
        }
    }

    private static int getIndexOfLargest(double[] x, int first, int last){
        double largest = x[first];
        int indexOfLargest = first;
        for(int i = first + 1; i <= last; i++){
            if(x[i] > largest){
                largest = x[i];
                indexOfLargest = i;
            }
        }
        return indexOfLargest;
    }

    private static int getIndexOfSmallest(double[] x, int first, int last) {
        double min = x[first];
        int indexOfMin = first;
        for(int i = first + 1; i <= last; i++){
            if(x[i] < min){
                min = x[i];
                indexOfMin = i;
            }
        }
        return indexOfMin;
    }

    private static void swap(double[] x, int i, int j){
        double temp = x[i];
        x[i] = x[j];
        x[j] = temp;
    }

    public static void alpabeticalSort(String[] x){
        Arrays.sort(x);
    }


}
