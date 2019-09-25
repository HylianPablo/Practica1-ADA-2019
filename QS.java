//TO-DO
//Añadir contadores e impresores para calcular número de operaciones y tiempo.
//Crear gráficas
//Tras eso, modificar el algoritmo para que se detenga y haga subdivisiones
//Hacer una memoria de la práctica

import java.lang.Math;
import java.util.Arrays;

public class QS {
	public final static int MAX_NUMBER = 10000000;
	public final static int MAX_VECTOR = 10;
	private static long numAsignaciones;
	private static long numComparaciones;

	public static void main(String args[]) {

		int tam = Integer.parseInt(args[0]);
		int numVect = (int) (Math.random() * MAX_VECTOR);

		for (int n = 0; n < numVect; n++) {

			int[] array = new int[tam];
			numAsignaciones = 0;
			numComparaciones = 0;
			long tiempoInicio = System.currentTimeMillis();

			for (int i = 0; i < tam; i++) {
				array[i] = (int) (Math.random() * MAX_NUMBER);
			}
			quickSort(array, 0, tam - 1);

			long tiempoFinal = System.currentTimeMillis();

			/*for (int j = 0; j < tam; j++) {
				System.out.println(array[j]);
			}*/
			System.out.println("Tiempo transcurrido: " + ((tiempoFinal - tiempoInicio)/1000)+" segundos");
			System.out.println("Número de comparaciones: "+numComparaciones);
			System.out.println("Número de asignaciones: "+numAsignaciones);
			System.out.println("");
		}

	}

	public static int pivote(int[] arr, int low, int high) {
		int longitud = high - low;
		int a = (int) (Math.random() * longitud) + low;
		int b = (int) (Math.random() * longitud) + low;
		int c = (int) (Math.random() * longitud) + low;

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

		int pivot = pivote(arr, low, high);

		// make left < pivot and right > pivot
		int i = low, j = high;
		while (i <= j) {
			while (arr[i] < pivot) {
				numComparaciones++;
				i++;
			}
			numComparaciones++;

			while (arr[j] > pivot) {
				numComparaciones++;
				j--;
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
		if (low < j)
			quickSort(arr, low, j);

		if (high > i)
			quickSort(arr, i, high);
	}
}
