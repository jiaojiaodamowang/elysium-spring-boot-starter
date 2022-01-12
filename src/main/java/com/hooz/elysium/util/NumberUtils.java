package com.hooz.elysium.util;

import java.util.Random;

/**
 * NumberUtils
 *
 * @author Kidd Zhou
 * @date 2022-01-01
 */
public final class NumberUtils {

    private NumberUtils(){}

    public static boolean isOddNumber(int num) {
        return (num & 1) == 1;
    }

    public static boolean isEvenNumber(int num) {
        return (num & 1) == 0;
    }

    public static void swap(int[] array, int index1, int index2) {
        if (index1 == index2) {
            return;
        }
        array[index1] = array[index1] ^ array[index2];
        array[index2] = array[index1] ^ array[index2];
        array[index1] = array[index1] ^ array[index2];
    }

    public static int[] randomArray(int size, int min, int max) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = new Random().nextInt(max - min + 1) + min;
        }
        return array;
    }
}
