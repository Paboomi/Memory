package com.mycompany.memory.frontend;

import com.mycompany.memory.backend.LogicaMemory;
import com.mycompany.memory.exceptions.ImagenException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author saien
 */
public class GenerarBotones {

    private LogicaMemory log = new LogicaMemory();
    //private static final String PATH_BACKGROUND = "/home/saien/Imágenes/Imagenes Memoria/background.jpg";
    private static final String PATH_BACKGROUND = "com/mycompany/memory/images/background.jpg";
    JButton[] buttons;

    public GenerarBotones(LogicaMemory logic) {
        this.log = logic;
    }

    public GenerarBotones() {
    }
    
    
    

    public JButton[] generarBotones(int cantidad, Frame pattern) throws ImagenException {
        buttons = new JButton[cantidad];
        for (int i = 0; i < buttons.length; i++) {
            JButton button = new JButton();
            button.setSize(130, 130);
            button.setPreferredSize(new Dimension(130, 130));
            try {
                button.setIcon(new ImageIcon(getClass().getClassLoader().getResource(PATH_BACKGROUND)));

            } catch (Exception e) {
                throw new ImagenException("No se ha podido cargar la imagen de fondo");
            }
            button.setBackground(Color.BLACK);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    try {
                        log.btnEnabled(button, buttons);
                        boolean termino = false;
                        termino = log.isTermino();
                        if (termino) {
                            JOptionPane.showMessageDialog(pattern, log.getResultado());
                            log.setTermino(false);
                        }
                    } catch (HeadlessException e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(pattern, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }

            });
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseExited(MouseEvent e) {
                    try {
                        log.compararImagenes();

                    } catch (Exception evt) {
                        evt.printStackTrace();
                    }
                }
            });
            buttons[i] = button;

        }
        return buttons;
    }

}
