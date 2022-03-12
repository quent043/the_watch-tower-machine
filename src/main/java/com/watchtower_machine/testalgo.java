package com.watchtower_machine;

import java.util.Arrays;

public class testalgo {

    private static int[] insert(int[] a, int key, int index) {
        int[] result = new int[a.length + 1];

        for (int i = 0, j = 0; i < a.length; i++, j++)
        // i ---> Original Value (i < a.length, donc on s'arrête à "4" itérations, c'est i qui commande)
        // j ---> Array
        {
            if (j == index)
            {
                result[j] = key;
                j++;
            }
            result[j] = a[i];
        }

        return result;
    }

    public static void main(String[] args)
    {
        int[] a = { 1, 2, 4, 5 };
        int key =  3;
        int index = 2;

        a = insert(a, key, index);
        System.out.println(Arrays.toString(a));
    }
}
