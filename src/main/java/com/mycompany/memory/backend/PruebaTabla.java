package com.mycompany.memory.backend;

import java.awt.BorderLayout;
import java.awt.Font;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JFrame;
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
public class PruebaTabla extends JFrame {

    private Partida partida;
    private JTable table;

    public PruebaTabla(Partida partida) {
        this.partida = partida;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLayout(new BorderLayout());
        add(mostrarDatos(), BorderLayout.CENTER);
        setLocationRelativeTo(null);
        
    }

    public PruebaTabla() {
        //this.partida = partida;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLayout(new BorderLayout());
        add(mostrarDatos(), BorderLayout.CENTER);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    private JScrollPane mostrarDatos() {
        DefaultTableModel tableModel = new DefaultTableModel();
        Font font = new Font("Arial", Font.BOLD, 24);

        tableModel.addColumn("Ganador");
        tableModel.addColumn("Puntaje");
        tableModel.addColumn("Perdedor");
        tableModel.addColumn("Fecha y hora");

        tableModel.addRow(new Object[]{partida.getGanador(), partida.getPuntaje(), partida.getPerdedor(), partida.getFechaHora()});

        table = new JTable(tableModel);
        table.setRowHeight(30);
        table.setFont(new Font("Verdana", Font.ROMAN_BASELINE, 20));

        DefaultTableCellRenderer renderFont = new DefaultTableCellRenderer();
        renderFont.setFont(font);
        renderFont.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(renderFont);

        }

        JScrollPane scrollPane = new JScrollPane(table);
        return scrollPane;
    }

//    public JPanel mostrarData() {
//        JPanel contenedor = new JPanel();
//
//        DefaultTableModel tableModel = new DefaultTableModel();
//        Font font = new Font("Arial", Font.BOLD, 24);
//
//        tableModel.addColumn("Ganador");
//        tableModel.addColumn("Puntaje");
//        tableModel.addColumn("Perdedor");
//        tableModel.addColumn("Fecha y hora");
//
//        tableModel.addRow(new Object[]{partida.getGanador(), partida.getPuntaje(), partida.getPerdedor(), partida.getFechaHora()});
//
//        table = new JTable(tableModel);
//        table.setRowHeight(30);
//        table.setFont(new Font("Verdana", Font.ROMAN_BASELINE, 20));
//
//        DefaultTableCellRenderer renderFont = new DefaultTableCellRenderer();
//        renderFont.setFont(font);
//        renderFont.setHorizontalAlignment(SwingConstants.CENTER);
//        for (int i = 0; i < table.getColumnCount(); i++) {
//            table.getColumnModel().getColumn(i).setCellRenderer(renderFont);
//
//        }
//
//        JScrollPane scrollPane = new JScrollPane(table);
//        contenedor.setSize(600, 500);
//        contenedor.setLayout(new BorderLayout());
//        contenedor.add(scrollPane, BorderLayout.CENTER);
//        
//        return contenedor;
//    }

    private void llenarDatos() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyy HH:mm");
        partida.setGanador("Jose");
        partida.setPuntaje(140);
        partida.setPerdedor("Dan");
        partida.setFechaHora(LocalDateTime.now().format(formatter));
    }

}
