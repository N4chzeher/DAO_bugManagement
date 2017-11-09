package control;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Created by NACHZEHER on 02/11/2017.
 */
public class Main {
    static final String bugsInfo = "rcBugPackage.txt";
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<String> paquetesAfectados = new ArrayList<String>();
        System.out.println("Por favor ingrese un codigo de error:");
        String bc = sc.nextLine();   // variable de codigo error

        FileReader file1read = null;
        BufferedReader bufFile1read = null;
        String part2 = "";
        String part1 = "";

        try {
            file1read = new FileReader(bugsInfo); // leer archivo
            bufFile1read = new BufferedReader(file1read);  // Hcaer bufer de lo leido
            String linea = "";             // String donde se guardara el contenido de la linea actual

            while ((linea = bufFile1read.readLine()) != null) {      // Mientras haya una linea que leer
                if (linea.equals(bc)) {                   // Si el String linea contiene la variable de codigo error
                    String[] parts = linea.split(";");    // Se usa Split para partir el array donde encuentre un ;
                    part1 = parts[0];               // Crea 2 subsString con cada parte resultante del Split
                    part2 = parts[1];
                    paquetesAfectados.add(part2);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<String> maintainers = Servicio.SearchMaintainerInfo(part2);
        Servicio.sendEmail(Servicio.writeEmail(part1, maintainers));
    }


}


