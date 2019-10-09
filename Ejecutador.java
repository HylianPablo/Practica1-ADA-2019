import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Ejecutador {

    public static void main(String args[]) {
        int potMin = Integer.parseInt(args[0]);
        int potMax = Integer.parseInt(args[1]);
        int base = Integer.parseInt(args[2]);
        int umbralMin = Integer.parseInt(args[3]);
        int umbralMax = Integer.parseInt(args[4]);
        int umbralDif = Integer.parseInt(args[5]);
        int numVecs = Integer.parseInt(args[6]);
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