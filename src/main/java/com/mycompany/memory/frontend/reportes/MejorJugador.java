package com.mycompany.memory.frontend.reportes;

import com.mycompany.memory.frontend.util.CustomDialog;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

/**
 *
 * @author saien
 */
public class MejorJugador extends CustomDialog {

    private String[][] data;

    public MejorJugador(JFrame parent) {
        super(parent, "Mejor Jugador");
    }

    @Override
    public JLabel formatoMensaje() {
        buscarMVP();
        if (data != null && data.length > 0) {

            //El jugador con mayor puntaje siempre estara en la primer fila
            String[] mvp = data[0];
            //Obtenemos los datos de interes de la fila
            String nombre = mvp[0];
            String puntaje = mvp[1];

            //Creamos el Label que mostrara al jugador
            JLabel MVP = new JLabel();
            MVP.setText(String.format("<html>Nombre: %s<br>Punteo: %s<html>", nombre, puntaje));
            MVP.setFont(new Font("Verdana", Font.BOLD, 20));
            MVP.setForeground(Color.MAGENTA);
            MVP.setHorizontalAlignment(SwingConstants.CENTER);

            return MVP;
        } else {
            JLabel noMVP = new JLabel("Sin MVP");
            return noMVP;
        }

    }

    private void buscarMVP() {
        //Leemos y almacenamos los datos del archivo en un arreglo
        data = leerDatos("data.txt");

        //Ordenamos el arreglo para que la busqueda sea mas sencilla
        ordenarDatos(data);

    }

    //Metodo para obtener los datos del archivo
    private String[][] leerDatos(String archivo) {
        String[][] data = null;

        //Intentamos leer el archivo
        try (Scanner sc = new Scanner(new File(archivo))) {
            int numFilas = 0;
            int numColumnas = 0;
            while (sc.hasNextLine()) {
                numFilas++;
                //Guardamos los elementos de la ultima fila leida
                String[] line = sc.nextLine().split(",");
                //Segun la cantidad de elementos asi sera la cantidad de columnas
                numColumnas = Math.max(numColumnas, line.length);
            }
            //cerramos el escaner
            sc.close();
            //Le damos las dimensiones al arreglo de datos
            data = new String[numFilas][numColumnas];
            //Creamos un nuevo Scanner para volver a leer el archivo
            Scanner scNuevo = new Scanner(new File(archivo));
            //Comenzamos desde la fila 0
            int fila = 0;
            while (scNuevo.hasNextLine()) {
                String[] line = scNuevo.nextLine().split(",");
                for (int col = 0; col < line.length; col++) {
                    //Almacenamos los elementos del arreglo line en el arreglo bidimensional
                    data[fila][col] = line[col];

                }
                fila++;
            }
            //Cerramos el Scanner
            scNuevo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return data;
    }

    //Metodo para ordenar el arreglo
    private void ordenarDatos(String[][] datos) {
        //Obtenemos el tamaño del arreglo
        int n = datos.length;
        //Metodo por insercion
        //iteramos sobre el tamaño del arreglo
        for (int i = 1; i < n; i++) {
            //Guardamos la fila a insertar
            String[] key = datos[i];
            //Movemos nuestro indice uno a la izquierda
            int j = i - 1;
            //Instanciamos los puntajes a comparar
            int comp1 = Integer.parseInt((String) datos[j][1]);
            int comp2 = Integer.parseInt((String) key[1]);

            while (j >= 0 && comp1 < comp2) {
                //Cambiamos de lugar la fila
                datos[j + 1] = datos[j];
                j--;
                if (j >= 0) {
                    //Cambiamos de fila para seguir con la comparacion
                    comp1 = Integer.parseInt((String) datos[j][1]);
                }
            }
            //Insertamos la fila guardada en la posicion correcta
            datos[j + 1] = key;
        }

    }

}
