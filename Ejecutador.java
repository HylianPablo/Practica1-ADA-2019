import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

//Este programa ejecuta el programa InserciónQS.java imprimiendo las ejecuciones promedio.
//Para obtener el archivo con las ejecuciones individuales simplemente cambiamos el argumento de InsercionQS a InsercionQSMOD
//La salida se imprime por pantalla pero en nuestro caso lo redireccionamos a un archivo CSV por comandos de terminal. (>>)

public class Ejecutador {

    public static void main(String args[]) {
        int potMin = Integer.parseInt(args[0]); // Potencia mínima.
        int potMax = Integer.parseInt(args[1]); // Potencia máxima.
        int base = Integer.parseInt(args[2]); //Base de la potencia del tamaño del vector. En las ejecuciones se han utilizado siempre vectores de base 10.
        int umbralMin = Integer.parseInt(args[3]); //Mínimo umbral de inserción a comprobar.
        int umbralMax = Integer.parseInt(args[4]); //Máximo umbral de inserción a comprobar.
        int umbralDif = Integer.parseInt(args[5]); //Progresión de crecimiento entre umbrales. (De 1 en 1, de 2 en 2...etc)
        int numVecs = Integer.parseInt(args[6]); //Número de ejecuciones que realiza un mismo vector de tamaño X y umbral de inserción Y.
        int tamVector;
        System.out.println("Tamano,Num. Vectores,Umbral insercion,Tiempo medio (ms),Num medio comparaciones,Num medio asignaciones");
        for (int i = potMin; i <= potMax; i++) {
            tamVector = (int) Math.pow(base, i);
            for (int j = umbralMin; j <= umbralMax; j += umbralDif) {
                try {
                    Runtime rt = Runtime.getRuntime();
                    String[] commands={"bash","-c","java InsercionQS "+numVecs+" "+j+" "+tamVector};
                    Process p = rt.exec(commands);
                    BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
                    String result;
                    while((result=br.readLine())!=null){
                        System.out.println(result);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}