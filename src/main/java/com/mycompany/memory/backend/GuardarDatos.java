package com.mycompany.memory.backend;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

/**
 *
 * @author saien
 */
public class GuardarDatos {

    //private static final String PATH_ESTADISTICAS = "com/mycompany/archivos/data.txt";
    private FileWriter fw;
    private BufferedWriter bw;
    private PrintWriter pw;
    

    public void guardarArchivo(Partida partida) {

        try {
            File file = new File("data.txt");
               if (!file.exists()) {
                    file.createNewFile();
                }
//            URL url = getClass().getClassLoader().getResource(PATH_ESTADISTICAS);
//            if (url == null) {
//                System.err.println("No se pudo encontrar el archivo");
//                return;
//            }
            //String path = url.getPath();
                fw = new FileWriter(file, true);
                bw = new BufferedWriter(fw);
                pw = new PrintWriter(bw);
                pw.println(String.format("%s,%d,%s,%s", partida.getGanador(),
                        partida.getPuntaje(), partida.getPerdedor(), partida.getFechaHora()
                ));
//            pw.print(partida.getGanador());
//            pw.print("," + partida.getPuntaje());
//            pw.print("," + partida.getPerdedor());
//            pw.print("," + partida.getFechaHora());
                //pw.close();
            }catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (pw != null) {
                    pw.close();
                }
                if (bw != null) {
                    bw.close();
                }
                if (fw != null) {
                    fw.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        }
    }
