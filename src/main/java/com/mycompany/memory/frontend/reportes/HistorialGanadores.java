package com.mycompany.memory.frontend.reportes;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author saien
 */
public class HistorialGanadores extends JFrame {
    
    private JTable table;

    public HistorialGanadores() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 500);
        setLayout(new BorderLayout());
        add(mostrarDatos(), BorderLayout.CENTER);
        setLocationRelativeTo(null);

    }
//Creamos la tabla para mostrar los datos
    private JPanel mostrarDatos() {
        //Fuente para las columnas
        Font font = new Font("Arial", Font.BOLD, 24);
        
        //Creamos nuestro modelo de tabla
        DefaultTableModel tableModel = new DefaultTableModel();
        //Agregamos las columnas
        tableModel.addColumn("Ganador");
        tableModel.addColumn("Puntaje");
        tableModel.addColumn("Perdedor");
        tableModel.addColumn("Fecha y hora");
        
        //Creamos nuestro Boton para ordenar los datos
        JButton btnOrdenar = new JButton();
        btnOrdenar.setText("Ordenar Por Mayor Puntaje");
        btnOrdenar.setFont(new Font("Arial", Font.BOLD, 20));
        
        //Intentamos leer el archivo para poder ingresar los datos del archivo a la tabla
        try (Scanner sc = new Scanner(new File("data.txt"))){
            while (sc.hasNextLine()) {
                String[] datos = sc.nextLine().split(",");
                tableModel.addRow(datos);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(HistorialGanadores.this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        btnOrdenar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    ordenarPunteos(tableModel);
                } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(HistorialGanadores.this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        //Creamos la instancia de nuestro JTable
        table = new JTable(tableModel);
        table.getTableHeader().setReorderingAllowed(false);
        table.setRowHeight(30);
        table.setFont(new Font("Verdana", Font.ROMAN_BASELINE, 20));
        
        //Cambiamos la fuente y la posicion del texto
        DefaultTableCellRenderer renderFont = new DefaultTableCellRenderer();
        renderFont.setFont(font);
        renderFont.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(renderFont);

        }
        //Creamos un ScrollPane que contendra nuestra JTable
        JScrollPane scrollPane = new JScrollPane(table);
        
        //Creamos un nuevo panel que contendra nuestro boton y la JTable
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(btnOrdenar, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    //Metodo para ordenar de mayor a menor
    private void ordenarPunteos(DefaultTableModel tableModel) {
        
        //Obtenemos la cantidad de filas y columnas
        int cantFilas = tableModel.getRowCount();
        int cantColumnas = tableModel.getColumnCount();
        
        //Creamos un arreglo de objetos que contendra los datos de la fila
        Object[][] data = new Object[cantFilas][4];

        //Iteramos primero sobre la fila
        for (int i = 0; i < cantFilas; i++) {
            //Iteramos para obtener los datos de la fila segun la columna
            for (int j = 0; j < cantColumnas; j++) {
                data[i][j] = tableModel.getValueAt(i, j);

            }

        }

        //Metodo por insercion
        int n = cantFilas;
        //Iteramos sobre el numero de Filas
        for (int i = 1; i < n; i++) {
            //Guardamos la fila a insertar
            Object[] key = data[i];
            //Obtenemos un nuevo indice
            int j = i-1;
            //Creamos los comparadores
            int comp1 = Integer.parseInt((String) data[j][1]);
            int comp2 = Integer.parseInt((String) key[1]);
            
            while (j>=0 && comp1 < comp2) {
                //Cambiamos de lugar la fila
                data[j+1] = data[j];
                j--;
                if (j >= 0) {
                    //Cambiamos de Fila y obtenemos el puntaje 
                    comp1 = Integer.parseInt((String) data[j][1]);
                }
            }
            //Insertamos la fila en el lugar correspondiente
            data[j+1]=key;
        }

        /*Actualizamos la tabla segun el puntaje
          Iteramos sobre la cantidad de filas*/

        for (int i = 0; i < cantFilas; i++) {
            //Iteramos sobre la cantidad de columnas
            for (int j = 0; j < cantColumnas; j++) {
                /*Ahora el contenido dentro del arreglo se almacenara en la fila y 
                en los campos de las columnas correspondientes*/
                tableModel.setValueAt(data[i][j], i, j);
            }
        }

    }

}
