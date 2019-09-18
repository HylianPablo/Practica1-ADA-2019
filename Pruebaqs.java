import java.lang.Math;
import java.util.Arrays;

public class Pruebaqs {
    public final static int MAX_NUMBER = 10000000;

    public static void main(String args[]) {
        int tam = Integer.parseInt(args[0]);
        int[] array = new int[tam];

        for (int i = 0; i < tam; i++) {
            array[i] = (int) (Math.random() * MAX_NUMBER);
        }
        quickSort(array, 0, tam - 1);

        for (int j = 0; j < tam; j++) {
            System.out.println(array[j]);
        }

    }

    public static int pivote(int[] arr) {
        int a = (int) (Math.random() * arr.length);
        int b = (int) (Math.random() * arr.length);
        int c = (int) (Math.random() * arr.length);

        int array[] = new int[3];
        array[0] = arr[a];
        array[1] = arr[b];
        array[2] = arr[c];

        Arrays.sort(array);
        return array[1];
    }

    public static void quickSort(int[] arr, int low, int high) {
		if (arr == null || arr.length == 0)
			return;
 
		if (low >= high)
			return;
 
		// pick the pivot
		//int middle = low + (high - low) / 2;
        //int pivot = arr[middle];
        int pivot=pivote(arr);
 
		// make left < pivot and right > pivot
		int i = low, j = high;
		while (i <= j) {
			while (arr[i] < pivot) {
				i++;
			}
 
			while (arr[j] > pivot) {
				j--;
			}
 
			if (i <= j) {
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
				i++;
				j--;
			}
		}
 
		// recursively sort two sub parts
		if (low < j)
			quickSort(arr, low, j);
 
		if (high > i)
			quickSort(arr, i, high);
	}
}
