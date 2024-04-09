package com.mycompany.memory.backend;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author saien
 */
public class GuardarDatos {

    private FileWriter fw;
    private BufferedWriter bw;
    private PrintWriter pw;
    

    public void guardarArchivo(Partida partida) {

        try {
            File file = new File("data.txt");
               if (!file.exists()) {
                    file.createNewFile();
                }
                fw = new FileWriter(file, true);
                bw = new BufferedWriter(fw);
                pw = new PrintWriter(bw);
                pw.println(String.format("%s,%d,%s,%s", partida.getGanador(),
                        partida.getPuntaje(), partida.getPerdedor(), partida.getFechaHora()
                ));
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
