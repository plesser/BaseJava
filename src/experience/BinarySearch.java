package experience;

import java.util.Arrays;

public class BinarySearch {
    public static void main(String[] args) {
        int[] array = new int[]{2, 3, 5, 6, 7, 8, 9};
        printArray(array);
        int index = Arrays.binarySearch(array, 1);
        System.out.println("1 ->" + index);
        index = Arrays.binarySearch(array, 4);
        System.out.println("4 ->" + index);
        index = Arrays.binarySearch(array, 11);
        System.out.println("11 ->" + index);
    }

    public static void printArray(int[] array){
        for (int i=0; i < array.length; i++){
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
