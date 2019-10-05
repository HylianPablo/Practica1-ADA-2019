//TO-DO
//Crear gráficas
//Tras eso, modificar el algoritmo para que se detenga y haga subdivisiones
//Hacer una memoria de la práctica

import java.util.Arrays;

public class InsercionQS2 {
	private static long numComparaciones;
	private static long numAsignaciones;

	/*
	 * Argumento 1 -> numero de vectores a ordenar
	 * Argumento 2 -> tamaño de vector al que detener Quicksort y empezar insercion
	 * Argumento 3 -> tamaño total de los vectores
	 */
	public static void main(String[] args) {
		int numVect = Integer.parseInt(args[0]);
		int tam = Integer.parseInt(args[2]);
		int tamInsercion = Integer.parseInt(args[1]);
		
		long tiempoTotal = 0;
		long comparacionesTotal = 0;
		long asignacionesTotal = 0;
		long tiempoInicio;
		long tiempoFinal;
		int[] array;

		for (int n = 0; n < numVect; n++) {
			numAsignaciones = 0;
			numComparaciones = 0;
			tiempoInicio = System.currentTimeMillis();
			
			array = getRandomArray(tam);
			
			quickSort(array, 0, tam - 1, tamInsercion);

			System.out.println("insDoin");
			insertion(array);
			System.out.println("insDone");
			
			tiempoFinal = System.currentTimeMillis();

			tiempoTotal += tiempoFinal-tiempoInicio;
			comparacionesTotal += numComparaciones;
			asignacionesTotal += numAsignaciones;

		}
		
		imprimirEstadisticas(tam, numVect, tiempoTotal, comparacionesTotal, asignacionesTotal);
	}
	
	/*
	 * Imprime las estadisticas medias de la ordenacion de n vectores
	 * segun los parametros especificados
	 */
	private static void imprimirEstadisticas(int tam, int n, long tiempoTotal, long comparacionesTotal, long asignacionesTotal) {
		System.out.println("Tamaño de los vectores: " + tam);
		System.out.println("Número de vectores: " + n);
		System.out.println("Tiempo medio transcurrido: " + tiempoTotal/n + " milisegundos.");
		System.out.println("Número medio de comparaciones: " + comparacionesTotal/n);
		System.out.println("Número medio de asignaciones: " + asignacionesTotal/n);
		System.out.println("--------------------------------------");
	}
	
	/*
	 * Devuelve un array del tamaño especificado con elementos al azar entre 0 y tam-1
	 */
	private static int[] getRandomArray(int tam) {
		int[] array = new int[tam];
		for (int i = 0; i < tam; i++) {
			array[i] = (int) (Math.random() * tam);
		}
		return array;
	}

	/*
	 * Devuelve la mediana de tres elementos del vector elegidos al azar entre low y high
	 */
	private static int pivote(int[] arr, int low, int high) {
		int longitud = high - low;
		int a = (int) (Math.random() * longitud) + low;
		int b = (int) (Math.random() * longitud) + low;
		int c = (int) (Math.random() * longitud) + low;

		int[] array = new int[3];
		array[0] = arr[a];
		array[1] = arr[b];
		array[2] = arr[c];

		Arrays.sort(array);
		return array[1];
	}

	/*
	 * Algortimo de ordenacion Quicksort
	 * Se ordena mientras los subvectores tengan un tamaño superior a tamMin
	 */
	private static void quickSort(int[] arr, int low, int high, int tamMin) {
		if (arr == null || arr.length == 0)
			return;

		if (low >= high)
			return;

		int pivot = pivote(arr, low, high);

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

		if (low - j < tamMin)
			quickSort(arr, low, j, tamMin);

		if (high - i > tamMin)
			quickSort(arr, i, high, tamMin);
	}

	/*
	 * Algortimo de ordenacion por insercion
	 */
	private static void insertion(int arr[]) {
		int n = arr.length;
		for (int i = 1; i < n; ++i) {
			int key = arr[i];
			numAsignaciones++;
			int j = i - 1;

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
}