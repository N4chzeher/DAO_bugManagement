package control;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by NACHZEHER on 09/11/2017.
 */
public class Servicio {


    public static ArrayList SearchMaintainerInfo(String code) {
        String packInfo = "packageMaintainer.txt";
        ArrayList<String> partes = new ArrayList<String>();
        ArrayList<String> emailData = new ArrayList<>();
        FileReader file2read = null;
        BufferedReader bufFile2read = null;
        String linea2 = "";
        try {
            file2read = new FileReader(packInfo);
            bufFile2read = new BufferedReader(file2read);

            while ((linea2 = bufFile2read.readLine()) != null) {
                String parts[] = linea2.split(";");

                if (code.equals(parts[0])) {

                    emailData.add(parts[0]);
                    emailData.add(parts[1]);
                    emailData.add(parts[2]);
                    break;
                }

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (emailData.size() == 0) {
            emailData.add("Error");
            return emailData;
        }

        return emailData;

    }
    public static String writeEmail(String code, ArrayList emailData) {

        ArrayList datos = emailData;
        String formato;
        if (datos.size() == 3) {

            formato = "From: owner@bugs.debian.org" +
                    System.lineSeparator() +
                    "To:" + datos.get(2).toString() +
                    System.lineSeparator() +
                    "Dear:" + datos.get(1).toString() +
                    System.lineSeparator() +
                    "You have a new bug:" +
                    System.lineSeparator() +
                    datos.get(0).toString() + " -RC bug number #=" + code +
                    System.lineSeparator() +
                    "Please, fix it as son as possible." +
                    System.lineSeparator() +
                    "Cheers";

        }else {
            formato = "Error: No se puede enviar Email \n" +
                    "\n" +
                    "No se ha encontrado informacion relacionada con el package ";
        }

        return formato;
    }
    public static void sendEmail(String form) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new StringReader(form));

            File mail = new File("emailMaintainer.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(mail));
            String linea = bufferedReader.readLine();

            while (linea != null) {

                bufferedWriter.write(linea);
                linea = bufferedReader.readLine();

                if (linea != null) {
                    bufferedWriter.newLine();
                }
            }

            bufferedWriter.flush();
            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
