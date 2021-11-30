package com.amazon;

import java.util.Arrays;

public class sorting {

    /**
     * Sorts the array from smallest to largest, or puts strings in alphabetical order
     * @param <T> 
     * @param a Array passed in
     * @param n Length of the Array
     */
    public static <T extends Comparable<? super T>> void sortSmallToLarge(T[] a, int n) {
		for(int index = 0; index < n - 1; index++) {
			int indexOfNextSmallest = getIndexOfSmallest(a, index, n-1);
			swap(a, index, indexOfNextSmallest);
		}
	}

    /**
     * Sorts the array from largest to smallest
     * @param <T> 
     * @param a Array passed in
     * @param n Length of the Array
     */
    public static <T extends Comparable<? super T>> void sortLargeToSmall(T[] a, int n) {
		for(int index = 0; index < n - 1; index++) {
			int indexOfNextLargest = getIndexOfLargest(a, index, n-1);
			swap(a, index, indexOfNextLargest);
		}
	}

	private static void swap(Object[] a, int i, int j) {
		Object temp = a[i];
		a[i] = a[j];
		a[j] = temp; 
		
	}

	private static <T extends Comparable<? super T>> int getIndexOfSmallest(T[] a, int first, int last){
		T min = a[first];
		int indexOfMin = first;
		for (int index = first + 1; index <= last; index++){
			if (a[index].compareTo(min) < 0){
				min = a[index];
				indexOfMin = index;
			}
		}
		return indexOfMin;
	}

    private static <T extends Comparable<? super T>> int getIndexOfLargest(T[] a, int first, int last){
		T max = a[first];
		int indexOfMax = first;
		for (int index = first + 1; index <= last; index++){
			if (a[index].compareTo(max) > 0){
				max = a[index];
				indexOfMax = index;
			}
		}
		return indexOfMax;
	}

}
