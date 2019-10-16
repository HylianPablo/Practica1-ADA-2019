//TO-DO
//Grupo 3
//Crear gráficas
//Hacer una memoria de la práctica

import java.util.Arrays;
import java.lang.management.*;
import java.math.BigInteger;

public class InsercionQS {
	private static long numComparaciones;
	private static long numAsignaciones;
	private static int TAM_INSERCION;

	/*
	 * Argumento 1 -> numero de vectores a ordenar
	 * Argumento 2 -> tamaño de vector al que detener Quicksort y empezar insercion
	 * Argumento 3 -> tamaño total de los vectores
	 */
	public static void main(String args[]) {
		int numVect = Integer.parseInt(args[0]);
		TAM_INSERCION = Integer.parseInt(args[1]);
		int tam = Integer.parseInt(args[2]);
		
		BigInteger tiempoMedia = BigInteger.valueOf(0);
		long comparacionesMedia = 0;
		long asignacionesMedia = 0;
		int[] array;

		for (int n = 0; n < numVect; n++) {

			array = new int[tam];
			numAsignaciones = 0;
			numComparaciones = 0;
			long tiempoInicio = getTime();

			for (int i = 0; i < tam; i++) {
				array[i] = (int) (Math.random() * tam);
			}
			
			quickSort(array, 0, tam - 1);

			insertion(array);
			
			long tiempoFinal = getTime();

			tiempoMedia = tiempoMedia.add(
				BigInteger.valueOf(tiempoFinal-tiempoInicio));
			comparacionesMedia+=numComparaciones;
			asignacionesMedia+=numAsignaciones;

		}
		
		/*
		 * Impresion de estadisticas
		 */
		System.out.println(tam+","+numVect+","+TAM_INSERCION+","+tiempoMedia.longValue()/numVect+","+comparacionesMedia/numVect+","+asignacionesMedia/numVect);
	}

	/*
	 * Devuelve la mediana de tres elementos del vector elegidos al azar entre low y high
	 */
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

	/*
	 * Algortimo de ordenacion Quicksort
	 */
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
		if (low - j < TAM_INSERCION)
			quickSort(arr, low, j);

		if (high - i > TAM_INSERCION)
			quickSort(arr, i, high);
	}

	/*
	 * Algortimo de ordenacion por insercion
	 */
	public static void insertion(int arr[]) {
		int n = arr.length;
		for (int i = 1; i < n; ++i) {
			int key = arr[i];
			numAsignaciones++;
			int j = i - 1;

			/*
			 * Move elements of arr[0..i-1], that are greater than key, to one position
			 * ahead of their current position
			 */
			while (j >= 0 && arr[j] > key) {
				numComparaciones++;
				arr[j + 1] = arr[j];
				j = j - 1;
				numAsignaciones++;
			}
			arr[j + 1] = key;
			numAsignaciones++;
		}
	}

	public static long getTime(){
		ThreadMXBean bean = ManagementFactory.getThreadMXBean( );
		return bean.isCurrentThreadCpuTimeSupported( ) ?
		bean.getCurrentThreadCpuTime() : 0L;
	}
}
