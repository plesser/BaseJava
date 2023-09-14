package experience;

public class ArrayCopy {
    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        printArray(array);
        System.arraycopy(array, 0, array, 1, 1);
        printArray(array);
    }

    public static void printArray(int[] array){
        for (int i=0; i < array.length; i++){
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

}
