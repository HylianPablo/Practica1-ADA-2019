//TO-DO
//Añadir contadores e impresores para calcular número de operaciones y tiempo.
//Crear gráficas
//Tras eso, modificar el algoritmo para que se detenga y haga subdivisiones
//Hacer una memoria de la práctica

import java.lang.Math;
import java.util.Arrays;

public class InsercionQS {
	public final static int MAX_NUMBER = 10000000;
	public final static int MAX_VECTOR = 10;
	public final static int TAM_INSERCION=50;
	private static long numComparaciones;
	private static long numAsignaciones;

	public static void main(String args[]) {
		int tam = Integer.parseInt(args[0]);
		int numVect = (int) (Math.random() * MAX_VECTOR);
		int[] array = new int[tam];

		for (int n = 0; n < numVect; n++) {

			numAsignaciones = 0;
			numComparaciones = 0;
			long tiempoInicio = System.currentTimeMillis();

			for (int i = 0; i < tam; i++) {
				array[i] = (int) (Math.random() * MAX_NUMBER);
			}
			quickSort(array, 0, tam - 1);

			long tiempoFinal = System.currentTimeMillis();

			for (int j = 0; j < tam; j++) {
				System.out.println(array[j]);
			}

			System.out.println("Tiempo transcurrido: " + (tiempoFinal - tiempoInicio));
			System.out.println("Número de comparaciones: " + numComparaciones);
			System.out.println("Número de asignaciones: " + numAsignaciones);
			System.out.println("");
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

		int pivot = pivote(arr);

		// make left < pivot and right > pivot
		int i = low, j = high;
		while (i <= j) {
			while (arr[i] < pivot) {
				numComparaciones++;
				i++;
			}
			numComparaciones++;

			while (arr[j] > pivot) {
				j--;
				numComparaciones++;
			}
			numComparaciones++;

			if (i <= j) {
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
				i++;
				j--;
				numAsignaciones += 3;
			}
		}

		// recursively sort two sub parts
		if (j - low > TAM_INSERCION && high - i > TAM_INSERCION) {

			if (low < j)
				quickSort(arr, low, j);

			if (high > i)
				quickSort(arr, i, high);
		}else{
			insertion(arr);
		}
	}

	// Java program for implementation of Insertion Sort
	/* Function to sort array using insertion sort */
	public static void insertion(int arr[]) {
		int n = arr.length;
		for (int i = 1; i < n; ++i) {
			int key = arr[i];
			int j = i - 1;

			/*
			 * Move elements of arr[0..i-1], that are greater than key, to one position
			 * ahead of their current position
			 */
			while (j >= 0 && arr[j] > key) {
				arr[j + 1] = arr[j];
				j = j - 1;
			}
			arr[j + 1] = key;
		}
	}
}
